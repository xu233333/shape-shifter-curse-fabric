package net.onixary.shapeShifterCurseFabric.util;

import net.minecraft.server.MinecraftServer;
import net.onixary.shapeShifterCurseFabric.ShapeShifterCurseFabric;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/*
 *   因为(azurelib 的 animations geo texture) 和 (Origin Furs 的 furs origin_layer) 和 (origins 的 origins badges) 和 (apoil 的 power) 需要大量同步 所以使用资源包+数据包同步
 *   服务器每2小时检查一次更新 (计划) (可调整)
 *   玩家为进入服务器同步更新 (计划)
 */

public class PatronBenefit {
    public static final URL DataPackURL;
    public static final URL ResourcePackURL;

    static {
        try {
            DataPackURL = new URL("");
            ResourcePackURL = new URL("");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean Server_NeedUpdate() {
        return false;
    }

    public static void Server_Update(MinecraftServer server) {
        if (Server_NeedUpdate()) {
            // TODO: Update
        }
    }

    public static byte[] ResourcePack_Download() {
        return downloadFormURL(ResourcePackURL);
    }

    public static byte[] DataPack_Download() {
        return downloadFormURL(DataPackURL);
    }

    public static byte[] downloadFormURL(URL url) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = url.openStream();
            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }
        } catch (IOException e) {
            ShapeShifterCurseFabric.LOGGER.error("Failed to download file from " + url, e);
            return null;
        }
        return outputStream.toByteArray();
    }
}
