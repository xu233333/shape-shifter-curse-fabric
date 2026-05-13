package net.onixary.shapeShifterCurseFabric.items.accessory;

import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

public class AccessoryUtils {
    public static final boolean LOADED_Trinkets = FabricLoader.getInstance().isModLoaded("trinkets");
    public static final boolean LOADED_Curios = FabricLoader.getInstance().isModLoaded("curios");

    public static String calcAutoMod(String mod) {
        return net.onixary.shapeShifterCurseFabric.util.Accessory.AccessoryUtils.nowAccessoryModID;
    }
}
