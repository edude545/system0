package net.ethobat.system0.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class WorldReader {

    public static BlockState[] getBlockLine(World world, BlockPos pos, Direction direction, int range) {
        BlockState[] line = new BlockState[range];
        for (int i=1; i<=range; i++) {
            line[i-1] = world.getBlockState(pos.offset(direction, i));
        }
        return line;
    }

    public static boolean isInBlockLine(Block block, World world, BlockPos pos, Direction direction, int range) {
        BlockState[] line = getBlockLine(world, pos, direction, range);
        for (BlockState state : line) {
            if (state.getBlock().equals(block)) {
                return true;
            }
        }
        return false;
    }

    public static BlockState[] getBlocksBetween(BlockPos pos1, BlockPos pos2) {
        // TODO
        return new BlockState[0];
    }

    // TODO
    // Get product of attenuation coefficients of blocks between given positions.
    public static double getAttenuationCoefficient(BlockPos pos1, BlockPos pos2) {
        return 0;
    }

}
