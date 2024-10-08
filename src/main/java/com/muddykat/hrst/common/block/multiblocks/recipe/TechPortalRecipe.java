/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.block.multiblocks.recipe;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.api.crafting.MultiblockRecipe;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class TechPortalRecipe extends MultiblockRecipe
{
	public static RegistryObject<IERecipeSerializer<TechPortalRecipe>> SERIALIZER;
	public static final CachedRecipeList<TechPortalRecipe> RECIPES = new CachedRecipeList<>(HRSTRecipeTypes.TECHPORTAL);
	public final Lazy<ItemStack> itemOutput;
	public final Lazy<FluidStack> fluidOutput;
	public final Set<FluidTagInput> fluidIn;
	public final IngredientWithSize itemInput;
	Lazy<Integer> totalProcessEnergy;
	Lazy<Integer> totalProcessTime;

	public <T extends Recipe<?>> TechPortalRecipe(ResourceLocation id, IngredientWithSize inputItem, Set<FluidTagInput> fluidInputSet, Lazy<ItemStack> itemOutput, Lazy<FluidStack> fluidOutput, int energy, int time)
	{
		super(LAZY_EMPTY, HRSTRecipeTypes.TECHPORTAL, id);
		this.itemOutput = itemOutput;
		this.fluidOutput = fluidOutput;
		this.fluidIn = fluidInputSet;
		this.itemInput = inputItem;
		totalProcessEnergy = Lazy.of(() -> energy);
		totalProcessTime = Lazy.of(() -> time);
		this.outputList = Lazy.of(() -> NonNullList.of(ItemStack.EMPTY, this.itemOutput.get()));

		if(this.fluidIn.isEmpty() || this.fluidIn.size() > 3) HRSTLib.HRST_LOGGER.error("Chemical Recipe {} has either NO or more than 3 Fluid Tag inputs in the set.", id);
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return SERIALIZER.get();
	}

	@Override
	public int getTotalProcessEnergy()
	{
		return totalProcessEnergy.get();
	}

	@Override
	public int getTotalProcessTime()
	{
		return totalProcessTime.get();
	}

	public static TechPortalRecipe findRecipe(Level level, FluidStack inputA, FluidStack inputB, FluidStack inputC, ItemStack itemInput)
	{
		// TODO: Ensure this is tested a LOT.
		List<FluidStack> inputFluids = List.of(inputA, inputB, inputC);
		for(TechPortalRecipe recipe : RECIPES.getRecipes(level))
		{
			for(FluidStack inputF : inputFluids)
			{
				if(inputFluids.size() > recipe.fluidIn.size() && inputF.isEmpty()) continue;
				if(recipe.fluidIn.stream().noneMatch((f) -> f.test(inputF))) return null;
			}
			if(recipe.itemInput.test(itemInput)) return recipe;
		}
		return null;
	}

	@Override
	protected IERecipeSerializer<?> getIESerializer()
	{
		return SERIALIZER.get();
	}

	@Override
	public int getMultipleProcessTicks()
	{
		return 4;
	}
}
