/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.client;

import com.muddykat.hrst.client.models.HRSTDynamicModel;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTMultiblockProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(modid = HRSTLib.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBusEventHandlers {

	@SubscribeEvent
	public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event)
	{

	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		registerBlockEntityRenderers(event);
	}

	public static void registerBlockEntityRenderers(RegisterRenderers event)
	{
	}

	private static <T extends BlockEntity>
	void registerBERenderNoContext(RegisterRenderers event, Supplier<BlockEntityType<? extends T>> type, Supplier<BlockEntityRenderer<T>> render)
	{
		ClientModBusEventHandlers.registerBERenderNoContext(event, type.get(), render);
	}

	private static <T extends BlockEntity>
	void registerBERenderNoContext(RegisterRenderers event, BlockEntityType<? extends T> type, Supplier<BlockEntityRenderer<T>> render)
	{
		event.registerBlockEntityRenderer(type, $ -> render.get());
	}
}
