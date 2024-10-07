/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.data.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class IGRecipes extends RecipeProvider
{
	public IGRecipes(PackOutput pOutput)
	{
		super(pOutput);
	}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer)
	{
		multiblockRecipes(consumer);
		itemRecipes(consumer);
	}

	private void itemRecipes(Consumer<FinishedRecipe> consumer)
	{

	}


	private void multiblockRecipes(Consumer<FinishedRecipe> consumer)
	{
	}
}
