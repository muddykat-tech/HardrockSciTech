/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */
package com.muddykat.hrst.core.registration;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.TemplateMultiblock;
import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistration;
import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistrationBuilder;
import blusunrize.immersiveengineering.api.multiblocks.blocks.component.ComparatorManager;
import blusunrize.immersiveengineering.api.multiblocks.blocks.component.IMultiblockComponent;
import blusunrize.immersiveengineering.api.multiblocks.blocks.component.RedstoneControl;
import blusunrize.immersiveengineering.api.multiblocks.blocks.logic.IMultiblockLogic;
import blusunrize.immersiveengineering.api.multiblocks.blocks.logic.IMultiblockState;
import com.muddykat.hrst.common.block.multiblocks.*;
import com.muddykat.hrst.common.block.multiblocks.HRHTPortalMultiblock;
import com.muddykat.hrst.common.item.*;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.lib.ResourceUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HRSTRegistrationHolder {
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Registries.BLOCK, HRSTLib.MODID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(Registries.ITEM, HRSTLib.MODID);
    private static final DeferredRegister<Fluid> FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.FLUIDS, HRSTLib.MODID);
    private static final DeferredRegister<FluidType> FLUIDTYPE_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HRSTLib.MODID);

    private static final DeferredRegister<BlockEntityType<?>> TE_REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HRSTLib.MODID);
    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HRSTLib.MODID);

    private static final HashMap<String, RegistryObject<Block>> BLOCK_REGISTRY_MAP = new HashMap<>();
    private static final HashMap<String, RegistryObject<Item>> ITEM_REGISTRY_MAP = new HashMap<>();
    private static final HashMap<String, RegistryObject<Fluid>> FLUID_REGISTRY_MAP = new HashMap<>();
    private static final HashMap<String, RegistryObject<FluidType>> FLUID_TYPE_REGISTRY_MAP = new HashMap<>();

    public static HashMap<String, MultiblockRegistration<?>> MB_REGISTRY_MAP = new HashMap<>();
    public static final HashMap<String, TemplateMultiblock> MB_TEMPLATE_MAP = new HashMap<>();

    private static <T extends MultiblockHandler.IMultiblock>
    T registerMultiblock(T multiblock) {
        MultiblockHandler.registerMultiblock(multiblock);
        return multiblock;
    }

    public static Function<String, Item> getItem = (key) -> ITEM_REGISTRY_MAP.get(key).get();
    public static Function<String, Block> getBlock = (key) -> BLOCK_REGISTRY_MAP.get(key).get();

    public static Function<String, TemplateMultiblock> getMBTemplate = MB_TEMPLATE_MAP::get;
    public static Function<String, Fluid> getFluid = (key) -> FLUID_REGISTRY_MAP.get(key).get();

    private static final List<Consumer<IEventBus>> MOD_BUS_CALLBACKS = new ArrayList<>();


    public static void initialize()
    {
        initializeMultiblocks();
        registerItem("advanced_tech_tools", () -> new HRSTMBFormationItem(HRHTPortalMultiblock.class));
    }

    public static MultiblockRegistration<?> getMB(String key){
        return MB_REGISTRY_MAP.get(key);
    }

    private static void initializeMultiblocks()
    {
        registerMB("techportal", HRHTPortalMultiblock.INSTANCE, HRSTMultiblockProvider.HRHTPortal);
    }

    private static void registerMB(String registry_name, HRSTTemplateMultiblock block, MultiblockRegistration<?> registration){
        registerMultiblockTemplate(registry_name, block);
        MB_REGISTRY_MAP.put(registry_name, registration);
    }

    public static Supplier<List<? extends Item>> supplyDeferredItems(){
        return () -> ITEM_REGISTER.getEntries().stream().map(RegistryObject::get).toList();
    }

    public static Supplier<List<? extends Block>> supplyDeferredBlocks(){
        return () -> BLOCK_REGISTER.getEntries().stream().map(RegistryObject::get).toList();
    }

    public static Supplier<List<? extends Fluid>> supplyDeferredFluids(){
        return () -> FLUID_REGISTER.getEntries().stream().map(RegistryObject::get).toList();
    }

    public static void registerMultiblockTemplate(String registry_name, TemplateMultiblock template)
    {
        MB_TEMPLATE_MAP.put(registry_name, registerMultiblock(template));
    }

    public static void registerItem(String registry_name,  Supplier<Item> itemSupplier){
        ITEM_REGISTRY_MAP.put(registry_name, ITEM_REGISTER.register(registry_name, itemSupplier));
    }

    public static void registerBlock(String registry_name,  Supplier<Block> blockSupplier){
        BLOCK_REGISTRY_MAP.put(registry_name, BLOCK_REGISTER.register(registry_name, blockSupplier));
    }

    public static void registerFluid(String registry_name,  Supplier<Fluid> fluidSupplier){
        FLUID_REGISTRY_MAP.put(registry_name, FLUID_REGISTER.register(registry_name, fluidSupplier));
    }

    public static void registerFluidType(String registry_name, Supplier<FluidType> fluidTypeSupplier)
    {
        FLUID_TYPE_REGISTRY_MAP.put(registry_name, FLUIDTYPE_REGISTER.register(registry_name, fluidTypeSupplier));
    }

    public static void addRegistersToEventBus(final IEventBus eventBus){
        BLOCK_REGISTER.register(eventBus);
        ITEM_REGISTER.register(eventBus);
        FLUID_REGISTER.register(eventBus);
        FLUIDTYPE_REGISTER.register(eventBus);
        TE_REGISTER.register(eventBus);
        TAB_REGISTER.register(eventBus);

        MOD_BUS_CALLBACKS.forEach(e -> e.accept(eventBus));
    }

    public static List<Item> getIGItems()
    {
        return ITEM_REGISTER.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    public static <S extends IMultiblockState> MultiblockRegistration<S> registerMetalMultiblock(String name, IMultiblockLogic<S> logic, Supplier<TemplateMultiblock> structure){
        return registerMetalMultiblock(name, logic, structure, null);
    }

    public static <S extends IMultiblockState> MultiblockRegistration<S> registerMetalMultiblock(String name, IMultiblockLogic<S> logic, Supplier<TemplateMultiblock> structure, @Nullable Consumer<MultiblockBuilder<S>> extras){
        BlockBehaviour.Properties prop = BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL)
                .strength(3, 15)
                .requiresCorrectToolForDrops()
                .isViewBlocking((state, blockReader, pos) -> false)
                .noOcclusion()
                .dynamicShape()
                .pushReaction(PushReaction.BLOCK);

        return registerMultiblock(name, logic, structure, extras, prop);
    }

    public static <S extends IMultiblockState> MultiblockRegistration<S> registerMultiblock(String name, IMultiblockLogic<S> logic, Supplier<TemplateMultiblock> structure, @Nullable Consumer<MultiblockBuilder<S>> extras, BlockBehaviour.Properties prop){
        MultiblockBuilder<S> builder = new MultiblockBuilder<>(logic, name)
                .structure(structure)
                .defaultBEs(TE_REGISTER)
                .defaultBlock(BLOCK_REGISTER, ITEM_REGISTER, prop);

        if(extras != null){
            extras.accept(builder);
        }

        return builder.build();
    }

    public static HashMap<String, RegistryObject<Item>> getItemRegistryMap() {
        return ITEM_REGISTRY_MAP;
    }

    public static HashMap<String, RegistryObject<Fluid>> getFluidRegistryMap()
    {
        return FLUID_REGISTRY_MAP;
    }

    public static HashMap<String, RegistryObject<Block>> getBlockRegistryMap() {
        return BLOCK_REGISTRY_MAP;
    }

    protected static class MultiblockBuilder<S extends IMultiblockState> extends MultiblockRegistrationBuilder<S, MultiblockBuilder<S>>{
        public MultiblockBuilder(IMultiblockLogic<S> logic, String name){
            super(logic, ResourceUtils.hrst(name));
        }

        public MultiblockBuilder<S> redstone(IMultiblockComponent.StateWrapper<S, RedstoneControl.RSState> getState, BlockPos... positions){
            redstoneAware();
            return selfWrappingComponent(new RedstoneControl<>(getState, positions));
        }

        public MultiblockBuilder<S> comparator(ComparatorManager<S> comparator){
            withComparator();
            return super.selfWrappingComponent(comparator);
        }

        @Override
        protected MultiblockBuilder<S> self(){
            return this;
        }
    }
    public static ResourceLocation getRegistryNameOf(Block block){
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
