/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.block.multiblocks.recipe.builder;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.api.crafting.builders.IEFinishedRecipe;
import com.muddykat.hrst.common.block.multiblocks.recipe.TechPortalRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public class TechPortalRecipeBuilder extends IEFinishedRecipe<TechPortalRecipeBuilder>
{
	protected TechPortalRecipeBuilder()
	{
		super(TechPortalRecipe.SERIALIZER.get());
	}

	public static TechPortalRecipeBuilder builder(Item result)
	{
		return new TechPortalRecipeBuilder().addResult(result);
	}

	public static TechPortalRecipeBuilder builder(ItemStack result)
	{
		return new TechPortalRecipeBuilder().addResult(result);
	}

	public static TechPortalRecipeBuilder builder(TagKey<Item> result, int count)
	{
		return new TechPortalRecipeBuilder().addResult(new IngredientWithSize(result, count));
	}

	public TechPortalRecipeBuilder addInput(FluidTagInput fluidTag)
	{
		return addFluidTag(generateSafeInputKey(), fluidTag);
	}

	public TechPortalRecipeBuilder addInput(TagKey<Fluid> fluidTag, int amount)
	{
		return addFluidTag(generateSafeInputKey(), fluidTag, amount);
	}

}
