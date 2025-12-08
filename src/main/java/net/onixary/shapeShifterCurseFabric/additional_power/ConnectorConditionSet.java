package net.onixary.shapeShifterCurseFabric.additional_power;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.apoli.util.Comparison;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.onixary.shapeShifterCurseFabric.ShapeShifterCurseFabric;

import java.util.function.Consumer;

public class ConnectorConditionSet {
    public static void registerAll(Consumer<ConditionFactory<Entity>> registryFunction) {
        registryFunction.accept(
                new ConditionFactory<>(ShapeShifterCurseFabric.identifier("fluid_height"),
                        (new SerializableData())
                                .add("fluid", SerializableDataTypes.FLUID_TAG)
                                .add("comparison", ApoliDataTypes.COMPARISON)
                                .add("compare_to", SerializableDataTypes.DOUBLE)
                        , (data, entity) -> {
                            return ((Comparison)data.get("comparison")).compare(entity.getFluidHeight(data.get("fluid")), data.getDouble("compare_to"));
                        }
                )
        );
    }
}
