package net.onixary.shapeShifterCurseFabric.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class EntityAttributeRegister {
    public static final Map<EntityType<? extends LivingEntity>, Supplier<DefaultAttributeContainer.Builder>> extraAttributes = new java.util.HashMap<>();

    public static boolean ShouldUseThisSystem() {
        return FabricLoader.getInstance().isModLoaded("connectormod") && FabricLoader.getInstance().isModLoaded("changed");
    }

    public static void register(EntityType<? extends LivingEntity> entityType, Supplier<DefaultAttributeContainer.Builder> builder) {
        if (ShouldUseThisSystem()) {
            extraAttributes.put(entityType, builder);
        }
        else {
            FabricDefaultAttributeRegistry.register(entityType, builder.get());
        }
    }
}
