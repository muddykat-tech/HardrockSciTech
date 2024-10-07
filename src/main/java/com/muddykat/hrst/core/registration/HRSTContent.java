package com.muddykat.hrst.core.registration;

import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.lib.manual.ManualEntry;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.Tree.InnerNode;
import com.muddykat.hrst.common.tag.HRSTTags;
import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

public class HRSTContent {

    public static void modContruction(IEventBus event){
        HRSTLib.HRST_LOGGER.info("Registering Multiblocks to Immersive Engineering");
        HRSTMultiblockProvider.forceClassLoad();
        HRSTRegistrationHolder.initialize();
        HRSTTags.initialize();
        HRSTRecipeTypes.init();
    }

    public static void initializeManualEntries()
    {
        ManualInstance instance = ManualHelper.getManual();
        InnerNode<ResourceLocation, ManualEntry> parent_category = instance.getRoot().getOrCreateSubnode(new ResourceLocation(HRSTLib.MODID, "main"), 99);

        ManualEntry.ManualEntryBuilder builder = new ManualEntry.ManualEntryBuilder(ManualHelper.getManual());
        builder.readFromFile(new ResourceLocation(HRSTLib.MODID, "intro"));
        instance.addEntry(parent_category, builder.create());

        InnerNode<ResourceLocation, ManualEntry> multiblock_category = parent_category.getOrCreateSubnode(new ResourceLocation(HRSTLib.MODID, "hrst_multiblocks"), 0);
        multiblockEntry(instance, multiblock_category, "techportal");
    }

    private static void multiblockEntry(ManualInstance instance, InnerNode<ResourceLocation, ManualEntry> category, String id){
        ManualEntry.ManualEntryBuilder multiblock = new ManualEntry.ManualEntryBuilder(ManualHelper.getManual());
        multiblock.readFromFile(new ResourceLocation(HRSTLib.MODID, id));
        instance.addEntry(category, multiblock.create());
    }

    public static void initialize(ParallelDispatchEvent event){

    }
}
