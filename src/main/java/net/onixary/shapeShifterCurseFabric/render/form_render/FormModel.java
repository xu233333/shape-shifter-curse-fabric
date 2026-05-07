package net.onixary.shapeShifterCurseFabric.render.form_render;

import com.google.gson.JsonObject;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.onixary.shapeShifterCurseFabric.ShapeShifterCurseFabric;
import net.onixary.shapeShifterCurseFabric.player_form.skin.PlayerSkinComponent;
import net.onixary.shapeShifterCurseFabric.player_form.skin.RegPlayerSkinComponent;
import net.onixary.shapeShifterCurseFabric.util.FormTextureUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormModel extends GeoModel<FormAnimatable> {
    public static List<FormModel> loadedModel = new ArrayList<>();
    public static HashMap<PlayerEntity, Boolean> SlimMap = new HashMap<>();

    public static final String MissingGeoString = ShapeShifterCurseFabric.MOD_ID + ":geo/missing.geo.json";
    public static final String MissingTextureString = ShapeShifterCurseFabric.MOD_ID + ":textures/missing.png";
    public static final String MissingAnimationString = ShapeShifterCurseFabric.MOD_ID + ":animations/missing.animation.json";

    public PlayerEntity entity;

    public JsonObject modelJson;

    public int ApplyPriority = 0;

    public String Name = "";  // 用于皮肤系统 先留一下API
    public Identifier Layer = null;  // 用于皮肤系统 先留一下API
    public Identifier Form = null;  // 用于皮肤系统 先留一下API

    public boolean SlimOnly = false;
    public boolean WideOnly = false;
    public boolean UseMultiplyMask = false;
    public Identifier ModelResource = ShapeShifterCurseFabric.identifier("geo/missing.geo.json");
    public Identifier ModelResource_Slim = ShapeShifterCurseFabric.identifier("geo/missing.geo.json");

    public Identifier TextureResource = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier TextureMaskResource = null;
    public Identifier TextureResource_Slim = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier TextureMaskResource_Slim = null;

    public Identifier OverlayTextureResource = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier OverlayTextureMaskResource = null;
    public Identifier OverlayTextureResource_Slim = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier OverlayTextureMaskResource_Slim = null;

    public Identifier EmissiveTextureResource = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier EmissiveTextureMaskResource = null;
    public Identifier EmissiveTextureResource_Slim = ShapeShifterCurseFabric.identifier("textures/missing.png");
    public Identifier EmissiveTextureMaskResource_Slim = null;

    public Identifier Animation = ShapeShifterCurseFabric.identifier("animations/missing.animation.json");

    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_Textures = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_Textures_Slim = new HashMap<>();

    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_OverlayTexture = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_OverlayTexture_Slim = new HashMap<>();

    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_EmissiveTexture = new HashMap<>();
    public HashMap<FormTextureUtils.ColorSetting, Identifier> ColorMask_Baked_EmissiveTexture_Slim = new HashMap<>();

    public FormModel(JsonObject json) {
        this.modelJson = json;
        this.CompileModel();
    }

    public void CompileModel() {
        this.ColorMask_Baked_Textures.clear();
        this.ColorMask_Baked_Textures_Slim.clear();
        this.ColorMask_Baked_OverlayTexture.clear();
        this.ColorMask_Baked_OverlayTexture_Slim.clear();
        this.ColorMask_Baked_EmissiveTexture.clear();
        this.ColorMask_Baked_EmissiveTexture_Slim.clear();

        this.ApplyPriority = JsonHelper.getInt(this.modelJson, "apply_priority", 0);
        this.Name = JsonHelper.getString(this.modelJson, "name", "");
        if (this.modelJson.has("layer")) {
            this.Layer = Identifier.tryParse(JsonHelper.getString(this.modelJson, "layer", ""));
        } else {
            this.Layer = null;
        }
        if (this.modelJson.has("form")) {
            this.Form = Identifier.tryParse(JsonHelper.getString(this.modelJson, "form", ""));
        } else {
            this.Form = null;
        }
        this.SlimOnly = JsonHelper.getBoolean(this.modelJson, "slim_only", false);
        this.WideOnly = JsonHelper.getBoolean(this.modelJson, "wide_only", false);
        this.UseMultiplyMask = JsonHelper.getBoolean(this.modelJson, "use_multiply_mask", false);

        this.ModelResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "model", MissingGeoString));
        this.ModelResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "model_slim", MissingGeoString));

        this.TextureResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "texture", MissingTextureString));
        this.TextureResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "texture_slim", MissingTextureString));
        if (this.modelJson.has("texture_mask")) {
            this.TextureMaskResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "texture_mask", MissingTextureString));
        } else {
            this.TextureMaskResource = null;
        }
        if (this.modelJson.has("texture_mask_slim")) {
            this.TextureMaskResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "texture_mask_slim", MissingTextureString));
        } else {
            this.TextureMaskResource_Slim = null;
        }

        this.OverlayTextureResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "overlay", MissingTextureString));
        this.OverlayTextureResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "overlay_slim", MissingTextureString));
        if (this.modelJson.has("overlay_mask")) {
            this.OverlayTextureMaskResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "overlay_mask", MissingTextureString));
        } else {
            this.OverlayTextureMaskResource = null;
        }
        if (this.modelJson.has("overlay_mask_slim")) {
            this.OverlayTextureMaskResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "overlay_mask_slim", MissingTextureString));
        } else {
            this.OverlayTextureMaskResource_Slim = null;
        }

        this.EmissiveTextureResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "emissive_overlay", MissingTextureString));
        this.EmissiveTextureResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "emissive_overlay_slim", MissingTextureString));
        if (this.modelJson.has("emissive_overlay_mask")) {
            this.EmissiveTextureMaskResource = Identifier.tryParse(JsonHelper.getString(this.modelJson, "emissive_overlay_mask", MissingTextureString));
        } else {
            this.EmissiveTextureMaskResource = null;
        }
        if (this.modelJson.has("emissive_overlay_mask_slim")) {
            this.EmissiveTextureMaskResource_Slim = Identifier.tryParse(JsonHelper.getString(this.modelJson, "emissive_overlay_mask_slim", MissingTextureString));
        } else {
            this.EmissiveTextureMaskResource_Slim = null;
        }

        this.Animation = Identifier.tryParse(JsonHelper.getString(this.modelJson, "animations", MissingAnimationString));

        // hidden
        // animation_system + animation_system_config
        // builtin_controller_data
    }

    public void setPlayer(PlayerEntity player, boolean slim) {
        this.entity = player;
        SlimMap.put(player, slim);
    }

    public boolean useSlim(boolean slim) {
        if (SlimOnly) {
            return true;
        }
        if (WideOnly) {
            return false;
        }
        return slim;
    }

    public Identifier getModelResource(boolean slim) {
        return useSlim(slim) ? ModelResource_Slim : ModelResource;
    }

    private Identifier readCacheOrBake(HashMap<FormTextureUtils.ColorSetting, Identifier> Cache, Identifier Resource, Identifier ResourceMask, FormTextureUtils.ColorSetting colorSetting) {
        Identifier CachedTexture = Cache.get(colorSetting);
        if (CachedTexture != null) {
            return CachedTexture;
        }
        CachedTexture = FormTextureUtils.BakeTexture(Resource, ResourceMask, colorSetting, UseMultiplyMask);
        if (CachedTexture == null) {
            CachedTexture = Resource;
        }
        Cache.put(colorSetting, CachedTexture);
        return CachedTexture;
    }

    public Identifier getTextureResource(boolean slim) {
        boolean uslim = useSlim(slim);
        PlayerSkinComponent component = null;
        try {
            component = RegPlayerSkinComponent.SKIN_SETTINGS.get(entity);
        }
        catch (NullPointerException ignored) {
        }
        Identifier Resource = uslim ? this.TextureResource_Slim : this.TextureResource;
        Identifier ResourceMask = uslim ? this.TextureMaskResource_Slim : this.TextureMaskResource;
        if (component != null && component.isEnableFormColor() && ResourceMask != null) {
            FormTextureUtils.ColorSetting colorSetting = component.getFormColor();
            HashMap<FormTextureUtils.ColorSetting, Identifier> Cache = uslim ? ColorMask_Baked_Textures_Slim : ColorMask_Baked_Textures;
            return readCacheOrBake(Cache, Resource, ResourceMask, colorSetting);
        }
        return Resource;
    }

    public Identifier getOverlayTextureResource(boolean slim) {
        boolean uslim = useSlim(slim);
        PlayerSkinComponent component = null;
        try {
            component = RegPlayerSkinComponent.SKIN_SETTINGS.get(entity);
        }
        catch (NullPointerException ignored) {
        }
        Identifier Resource = uslim ? this.OverlayTextureResource_Slim : this.OverlayTextureResource;
        Identifier ResourceMask = uslim ? this.OverlayTextureMaskResource_Slim : this.OverlayTextureMaskResource;
        if (component != null && component.isEnableFormColor() && ResourceMask != null) {
            FormTextureUtils.ColorSetting colorSetting = component.getFormColor();
            HashMap<FormTextureUtils.ColorSetting, Identifier> Cache = uslim ? ColorMask_Baked_OverlayTexture_Slim : ColorMask_Baked_OverlayTexture;
            return readCacheOrBake(Cache, Resource, ResourceMask, colorSetting);
        }
        return Resource;
    }

    public Identifier getEmissiveTextureResource(boolean slim) {
        boolean uslim = useSlim(slim);
        PlayerSkinComponent component = null;
        try {
            component = RegPlayerSkinComponent.SKIN_SETTINGS.get(entity);
        }
        catch (NullPointerException ignored) {
        }
        Identifier Resource = uslim ? this.EmissiveTextureResource_Slim : this.EmissiveTextureResource;
        Identifier ResourceMask = uslim ? this.EmissiveTextureMaskResource_Slim : this.EmissiveTextureMaskResource;
        if (component != null && component.isEnableFormColor() && ResourceMask != null) {
            FormTextureUtils.ColorSetting colorSetting = component.getFormColor();
            HashMap<FormTextureUtils.ColorSetting, Identifier> Cache = uslim ? ColorMask_Baked_EmissiveTexture_Slim : ColorMask_Baked_EmissiveTexture;
            return readCacheOrBake(Cache, Resource, ResourceMask, colorSetting);
        }
        return Resource;
    }

    @Override
    public Identifier getModelResource(FormAnimatable animatable) {
        PlayerEntity player = animatable.e;
        return getModelResource(SlimMap.getOrDefault(player, false));
    }

    @Override
    public Identifier getTextureResource(FormAnimatable animatable) {
        PlayerEntity player = animatable.e;
        return getModelResource(SlimMap.getOrDefault(player, false));
    }

    @Override
    public Identifier getAnimationResource(FormAnimatable animatable) {
        return this.Animation;
    }
}
