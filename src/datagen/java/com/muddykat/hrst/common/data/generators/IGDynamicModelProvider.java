/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.common.data.generators.IGDynamicModelProvider.SimpleModelBuilder;
import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.loaders.ObjModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.Map.Entry;

public class IGDynamicModelProvider extends ModelProvider<SimpleModelBuilder>
{
	private final IGBlockStateProvider multiblocks;

	public IGDynamicModelProvider(IGBlockStateProvider multiblocks, PackOutput output, ExistingFileHelper existingFileHelper)
	{
		super(output, HRSTLib.MODID, "dynamic", rl -> new SimpleModelBuilder(rl, existingFileHelper), existingFileHelper);
		this.multiblocks = multiblocks;
	}

	@Override
	protected void registerModels()
	{
		for(Entry<Block, ModelFile> multiblock : multiblocks.unsplitModels.entrySet())
			withExistingParent(BuiltInRegistries.BLOCK.getKey(multiblock.getKey()).getPath(), multiblock.getValue().getLocation());
	}

	public static ResourceLocation rl(String path) {
		return new ResourceLocation(HRSTLib.MODID, path);
	}

	@Nonnull
	@Override
	public String getName()
	{
		return "IG Dynamic models";
	}

	public static class SimpleModelBuilder extends ModelBuilder<SimpleModelBuilder>
	{

		public SimpleModelBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper)
		{
			super(outputLocation, existingFileHelper);
		}
	}
}
