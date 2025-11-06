package net.onixary.shapeShifterCurseFabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.onixary.shapeShifterCurseFabric.ShapeShifterCurseFabric;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

import static net.onixary.shapeShifterCurseFabric.util.CustomEdibleUtils.getPowerFoodComponent;

@Mixin(LivingEntity.class)
public class CustomEdiblePlayerAMixin {
    @Shadow
    protected ItemStack activeItemStack;

    /*

    @ModifyExpressionValue(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isFood()Z"))
    private boolean eatFood$isFood(boolean original, World world, ItemStack stack) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B1");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            return getPowerFoodComponent(playerEntity, stack) != null || original;
        }
        return original;
    }



    @ModifyExpressionValue(method = "applyFoodEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;isFood()Z"))
    private boolean applyFoodEffects$isFood(boolean original, ItemStack stack, World world, LivingEntity targetEntity) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B2");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            return getPowerFoodComponent(playerEntity, stack) != null || original;
        }
        return original;
    }

    @ModifyExpressionValue(method = "applyFoodEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getFoodComponent()Lnet/minecraft/item/FoodComponent;"))
    private FoodComponent applyFoodEffects$getFoodComponent(FoodComponent original, ItemStack stack, World world, LivingEntity targetEntity) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B3");
        if (targetEntity instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, stack);
            if (fc == null) {
                return original;
            }
            return fc;
        }
        return original;
    }

    @ModifyExpressionValue(method = "shouldSpawnConsumptionEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I"))
    private int shouldSpawnConsumptionEffects$getMaxUseTime(int original) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B4");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, activeItemStack);
            if (fc == null) {
                return original;
            }
            return fc.isSnack() ? 16 : 32;
        }
        return original;
    }

    @ModifyExpressionValue(method = "onTrackedDataSet", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I"))
    private int onTrackedDataSet$getMaxUseTime(int original) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B5");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, activeItemStack);
            if (fc == null) {
                return original;
            }
            return fc.isSnack() ? 16 : 32;
        }
        return original;
    }

    @ModifyExpressionValue(method = "setCurrentHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I"))
    private int setCurrentHand$getMaxUseTime(int original) {
        // ShapeShifterCurseFabric.LOGGER.info("SSC-B6");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, activeItemStack);
            if (fc == null) {
                return original;
            }
            return fc.isSnack() ? 16 : 32;
        }
        return original;
    }

    @ModifyExpressionValue(method = "shouldSpawnConsumptionEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getFoodComponent()Lnet/minecraft/item/FoodComponent;"))
    private FoodComponent shouldSpawnConsumptionEffects$getFoodComponent(FoodComponent original) {
        ShapeShifterCurseFabric.LOGGER.info("SSC-B7");
        if ((Object) this instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, activeItemStack);
            if (fc == null) {
                return original;
            }
            return fc;
        }
        return original;
    }

    @ModifyExpressionValue(method = "spawnConsumptionEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/util/UseAction;"))
    private UseAction applyFoodEffects$isFood(UseAction original, ItemStack stack, int particleCount) {
        ShapeShifterCurseFabric.LOGGER.info("SSC-B8");
        if ((Object)this instanceof PlayerEntity playerEntity) {
            FoodComponent fc = getPowerFoodComponent(playerEntity, activeItemStack);
            if (fc == null) {
                return original;
            }
            return UseAction.EAT;
        }
        return original;
    }

    */

    @Shadow
    private void applyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity) {}

    @Inject(method = "eatFood", at = @At(value = "HEAD"), cancellable = true)
    private void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if ((Object)this instanceof PlayerEntity playerEntity) {
            if (getPowerFoodComponent(playerEntity, stack) != null) {
                world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), playerEntity.getEatSound(stack), SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
                this.applyFoodEffects(stack, world, playerEntity);
                if (!playerEntity.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                playerEntity.emitGameEvent(GameEvent.EAT);
                cir.setReturnValue(stack);
            }
        }
    }

    @Inject(method = "applyFoodEffects", at = @At(value = "HEAD"), cancellable = true)
    private void applyFoodEffectsMixin(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci) {
        Item item = stack.getItem();
        FoodComponent fc = getPowerFoodComponent((PlayerEntity)targetEntity, stack);
        if (fc != null)  {
            List<Pair<StatusEffectInstance, Float>> list = fc.getStatusEffects();
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                Pair<StatusEffectInstance, Float> pair = (Pair)var6.next();
                if (!world.isClient && pair.getFirst() != null && world.random.nextFloat() < (Float)pair.getSecond()) {
                    targetEntity.addStatusEffect(new StatusEffectInstance((StatusEffectInstance)pair.getFirst()));
                }
            }
            ci.cancel();
        }
    }

    @Inject(method = "shouldSpawnConsumptionEffects", at = @At(value = "HEAD"), cancellable = true)
    private void shouldSpawnConsumptionEffects(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PlayerEntity playerEntity) {
            int i = playerEntity.getItemUseTimeLeft();
            FoodComponent foodComponent = getPowerFoodComponent(playerEntity, this.activeItemStack);
            if (foodComponent != null) {
                boolean bl = foodComponent != null && foodComponent.isSnack();
                int MaxUseTime = foodComponent.isSnack() ? 16 : 32;
                bl |= i <= MaxUseTime - 7;
                cir.setReturnValue(bl && i % 4 == 0);
            }
        }
    }

    @Final
    @Shadow
    protected static TrackedData<Byte> LIVING_FLAGS;

    @Shadow
    protected int itemUseTimeLeft;

    @Inject(method = "onTrackedDataSet", at = @At(value = "RETURN"), cancellable = true)
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntity playerEntity) {
            if (LIVING_FLAGS.equals(data) && playerEntity.getWorld().isClient && playerEntity.isUsingItem()) {
                if (!this.activeItemStack.isEmpty()) {
                    FoodComponent foodComponent = getPowerFoodComponent(playerEntity, this.activeItemStack);
                    if (foodComponent == null) {
                        return;
                    }
                    this.itemUseTimeLeft = foodComponent.isSnack() ? 16 : 32;
                }
            }
        }
    }

    @Shadow
    protected void setLivingFlag(int mask, boolean value) {}

    @Inject(method = "setCurrentHand", at = @At(value = "HEAD"), cancellable = true)
    private void setCurrentHand(Hand hand, CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntity playerEntity) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            this.activeItemStack = itemStack;
            FoodComponent foodComponent = getPowerFoodComponent(playerEntity, this.activeItemStack);
            if (foodComponent == null) {
                return;
            }
            this.itemUseTimeLeft = foodComponent.isSnack() ? 16 : 32;
            if (!playerEntity.getWorld().isClient) {
                this.setLivingFlag(1, true);
                this.setLivingFlag(2, hand == Hand.OFF_HAND);
                playerEntity.emitGameEvent(GameEvent.ITEM_INTERACT_START);
            }
        }
    }

    @Shadow
    private void spawnItemParticles(ItemStack stack, int count) {}

    @Unique
    private final Random randomNew = Random.create();

    @Inject(method = "spawnConsumptionEffects", at = @At(value = "HEAD"), cancellable = true)
    private void spawnConsumptionEffects(ItemStack stack, int particleCount, CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntity playerEntity) {
            if (!stack.isEmpty() && playerEntity.isUsingItem()) {
                FoodComponent foodComponent = getPowerFoodComponent(playerEntity, stack);
                if (foodComponent != null) {
                    this.spawnItemParticles(stack, particleCount);
                    playerEntity.playSound(playerEntity.getEatSound(stack), 0.5F + 0.5F * (float) this.randomNew.nextInt(2), (this.randomNew.nextFloat() - this.randomNew.nextFloat()) * 0.2F + 1.0F);
                    ci.cancel();
                }
            }
        }
    }
}
