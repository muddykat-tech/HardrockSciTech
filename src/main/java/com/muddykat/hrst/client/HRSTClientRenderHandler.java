/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.client;

import com.muddykat.hrst.common.block.helper.HRSTBlockType;
import com.muddykat.hrst.common.item.helper.HRSTFlagItem;
import com.muddykat.hrst.core.registration.HRSTMultiblockProvider;
import com.muddykat.hrst.core.registration.HRSTRegistrationHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HRSTClientRenderHandler implements ItemColor, BlockColor {
    // Handles, IG Block and Item Tint Colors, Render Layering and Special Renders

    @OnlyIn(Dist.CLIENT)
    private static Map<RenderTypeSkeleton, RenderType> renderTypes;

    private static final Map<Block, RenderTypeSkeleton> mapping = new HashMap<>();
    private static final Map<Block, Block> inheritances = new HashMap<>();

    public static HRSTClientRenderHandler INSTANCE = new HRSTClientRenderHandler();

    // Register This as the color handler for all IG Items and Blocks
    public static void register(){
        for(Supplier<Item> holder : HRSTRegistrationHolder.getItemRegistryMap().values()){
            Item i = holder.get();
            if(i instanceof HRSTFlagItem){
                Minecraft.getInstance().getItemColors().register(INSTANCE, i);
            }
        }

        setRenderType(HRSTMultiblockProvider.HRHTPortal.block().get(), RenderTypeSkeleton.CUTOUT_MIPPED);
    }

    // Initialize the keys and mappings for render layers
    @OnlyIn(Dist.CLIENT)
    public static void init(FMLClientSetupEvent event) {
        for(Block b : inheritances.keySet()) {
            Block inherit = inheritances.get(b);
            if(mapping.containsKey(inherit))
                mapping.put(b, mapping.get(inherit));
        }

        for(Block b : mapping.keySet()) {
            ItemBlockRenderTypes.setRenderLayer(b, renderTypes.get(mapping.get(b)));
        }

        inheritances.clear();
        mapping.clear();
    }

    // Color Function for IG Blocks
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter getter, @Nullable BlockPos pos, int index) {
        if(state.getBlock() instanceof HRSTBlockType type)
            return type.getColor(index);
        return 0xffffff;
    }

    // Color Function for IG Items
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if(stack.getItem() instanceof HRSTFlagItem type)
            return type.getColor(tintIndex);
        return 0xffffff;
    }

    @OnlyIn(Dist.CLIENT)
    public static void setRenderType(Block block, RenderTypeSkeleton skeleton) {
        setRenderTypeClient(block, skeleton);
    }

    @OnlyIn(Dist.CLIENT)
    private static void setRenderTypeClient(Block block, RenderTypeSkeleton skeleton) {
        resolveRenderTypes();
        mapping.put(block, skeleton);
    }

    @OnlyIn(Dist.CLIENT)
    private static void resolveRenderTypes() {
        if(renderTypes == null) {
            renderTypes = new HashMap<>();

            renderTypes.put(RenderTypeSkeleton.SOLID, RenderType.solid());
            renderTypes.put(RenderTypeSkeleton.CUTOUT, RenderType.cutout());
            renderTypes.put(RenderTypeSkeleton.CUTOUT_MIPPED, RenderType.cutoutMipped());
            renderTypes.put(RenderTypeSkeleton.TRANSLUCENT, RenderType.translucent());
        }
    }

    public enum RenderTypeSkeleton {
        SOLID,
        CUTOUT,
        CUTOUT_MIPPED,
        TRANSLUCENT;
    }
}
