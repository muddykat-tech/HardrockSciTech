/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.data.generators;

import blusunrize.immersiveengineering.api.IETags;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTRegistrationHolder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;


public class IGItemTags extends ItemTagsProvider
{
	public IGItemTags(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blocks, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, blocks, HRSTLib.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider)
	{
		tag(IETags.toolboxTools).add(HRSTRegistrationHolder.getItem.apply("advanced_tech_tools"));
	}
}
