package net.onixary.shapeShifterCurseFabric.render.form_render;

import com.google.gson.JsonObject;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.onixary.shapeShifterCurseFabric.util.FormTextureUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormModel extends GeoModel<FormAnimatable> {
    public static List<FormModel> loadedModel = new ArrayList<>();

    public JsonObject modelJson;

    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_Textures = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_OverlayTexture = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_OverlayTexture_Slim = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_EmissiveTexture = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_EmissiveTexture_Slim = new HashMap<>();

    public FormModel(JsonObject json) {
        this.modelJson = json;
    }

    @Override
    public Identifier getModelResource(FormAnimatable animatable) {
        return null;
    }

    @Override
    public Identifier getTextureResource(FormAnimatable animatable) {
        return null;
    }

    @Override
    public Identifier getAnimationResource(FormAnimatable animatable) {
        return null;
    }
}
