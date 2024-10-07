/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTRegistrationHolder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class IGBlockLootProvider extends BlockLootSubProvider
{
	private final Set<Block> generatedLootTables = new HashSet<>();
	public IGBlockLootProvider()
	{
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate()
	{

	}

	@Override
	protected void add(@NotNull Block block, LootTable.Builder builder) {
		this.generatedLootTables.add(block);
		this.map.put(block.getLootTable(), builder);
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		return generatedLootTables;
	}
}
