package net.onixary.shapeShifterCurseFabric.items.accessory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.onixary.shapeShifterCurseFabric.util.Accessory.AccessoryUtils;

import java.util.List;
import java.util.Map;

public class CurioUtils {
    public static boolean isEquipped(LivingEntity entity, Item item) {
        return net.onixary.shapeShifterCurseFabric.util.Accessory.CurioUtils.isEquipped(entity, item);
    }

    public static Map<String, List<ItemStack>> getEntitySlots(LivingEntity entity) {
        return net.onixary.shapeShifterCurseFabric.util.Accessory.CurioUtils.getEntitySlots(entity);
    }

    public static List<ItemStack> getEntitySlot(LivingEntity entity, String SlotName) {
        return net.onixary.shapeShifterCurseFabric.util.Accessory.CurioUtils.getEntitySlot(entity, SlotName);
    }

    public static void setEntitySlot(LivingEntity entity, String SlotName, int Index, ItemStack stack) {
        net.onixary.shapeShifterCurseFabric.util.Accessory.CurioUtils.setEntitySlot(entity, SlotName, Index, stack);
    }

    public static void onPlayerEquip(PlayerEntity player, Identifier itemID) {
        AccessoryUtils.onPlayerEquip(player, itemID, "curios");
    }

    public static void onPlayerUnEquip(PlayerEntity player, Identifier itemID) {
        AccessoryUtils.onPlayerUnEquip(player, itemID, "curios");
    }

    public static boolean CanAutoExecute(Identifier itemID) {
        return AccessoryUtils.CanAutoExecute(itemID, "curios");
    }
}
