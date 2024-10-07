/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.core.lib.HRSTLib;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;


public class IGFluidTags extends FluidTagsProvider
{
	public IGFluidTags(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, HRSTLib.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider)
	{
	}
}
