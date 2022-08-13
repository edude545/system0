package net.ethobat.system0.api.energy;

import net.ethobat.system0.registry.S0EnergyTypes;
import net.minecraft.util.math.BlockPos;

public final class MageVeins {

    public static EnergyType getEnergyType(BlockPos pos) {
        return S0EnergyTypes.SKEINTILLATING;
    }

    public static long getPotentialEnergy(BlockPos pos, long limit) {
        return limit;
    }

}
