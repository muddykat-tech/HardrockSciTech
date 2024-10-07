/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.block.multiblocks;

import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import com.muddykat.hrst.common.block.multiblocks.helper.HRSTClientMultiblockProperties;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTMultiblockProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class HRHTPortalMultiblock extends HRSTTemplateMultiblock
{
	public static final HRHTPortalMultiblock INSTANCE = new HRHTPortalMultiblock();
	public HRHTPortalMultiblock()
	{
		super(new ResourceLocation(HRSTLib.MODID, "multiblocks/techportal"), new BlockPos(1,0,1), new BlockPos(2,1,3), new BlockPos(15,9,10), HRSTMultiblockProvider.HRHTPortal);
	}

	@Override
	public float getManualScale()
	{
		return 12;
	}

	@Override
	public void initializeClient(Consumer<ClientMultiblocks.MultiblockManualData> consumer) {
		consumer.accept(new HRSTClientMultiblockProperties(this, 2.5, 0.5, 2.5));
	}
}
