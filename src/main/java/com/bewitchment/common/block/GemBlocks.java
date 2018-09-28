package com.bewitchment.common.block;



import net.minecraft.block.material.Material;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;



public class GemBlocks extends BlockMod implements IInfusionStabiliser
{

    public GemBlocks(String id, Material material) {
        super(id, material);






    }

    @Override
    public boolean canStabaliseInfusion(World world, BlockPos blockPos) {
        final boolean b = false;
        return
                true;
    }
}
