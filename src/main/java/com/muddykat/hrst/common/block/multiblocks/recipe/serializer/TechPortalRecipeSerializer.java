/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.block.multiblocks.recipe.serializer;

import blusunrize.immersiveengineering.api.crafting.IERecipeSerializer;
import com.google.gson.JsonObject;
import com.muddykat.hrst.common.block.multiblocks.recipe.TechPortalRecipe;
import com.muddykat.hrst.core.registration.HRSTMultiblockProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;
import org.jetbrains.annotations.Nullable;

public class TechPortalRecipeSerializer extends IERecipeSerializer<TechPortalRecipe>
{
	@Override
	public ItemStack getIcon()
	{
		return HRSTMultiblockProvider.HRHTPortal.iconStack();
	}

	@Override
	public TechPortalRecipe readFromJson(ResourceLocation resourceLocation, JsonObject json, IContext iContext)
	{
		return null;
	}

	@Override
	public @Nullable TechPortalRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buffer)
	{
		return null;
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, TechPortalRecipe recipe)
	{
	}
}
