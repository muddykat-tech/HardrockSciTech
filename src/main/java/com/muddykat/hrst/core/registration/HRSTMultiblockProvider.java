package com.muddykat.hrst.core.registration;

import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistration;
import com.muddykat.hrst.common.block.multiblocks.logic.TechPortalLogic;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class HRSTMultiblockProvider {

    public static final MultiblockRegistration<TechPortalLogic.State> HRHTPortal = HRSTRegistrationHolder.registerMultiblock("techportal", new TechPortalLogic(), () -> HRSTRegistrationHolder.getMBTemplate.apply("techportal"), builder -> {}, Properties.copy(Blocks.IRON_BLOCK));

    public static void forceClassLoad(){};
}
