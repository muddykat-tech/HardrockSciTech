/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.core.registration;

import blusunrize.immersiveengineering.api.crafting.IERecipeTypes.TypeWithClass;
import com.muddykat.hrst.common.block.multiblocks.recipe.*;
import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class IGRecipeTypes
{
	private static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE, HRSTLib.MODID);

	public static final TypeWithClass<TechPortalRecipe> TECHPORTAL = register("techportal", TechPortalRecipe.class);

	private static <T extends Recipe<?>>
	TypeWithClass<T> register(String name, Class<T> type)
	{
		RegistryObject<RecipeType<T>> regObj = REGISTER.register(name, () -> new RecipeType<>()
		{
		});
		return new TypeWithClass<>(regObj, type);
	}

	public static void init()
	{
		REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
