package com.muddykat.hrst.common.data;

import com.muddykat.hrst.common.data.generators.*;
import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = HRSTLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IGDataProvider {
    public static Logger log = LogManager.getLogger(HRSTLib.MODID + "/DataGenerator");

    @SubscribeEvent
    public static void generate(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput out = generator.getPackOutput();
        final var lookup = event.getLookupProvider();
        
        log.info("-===== Starting Data Generation for Hardrock Scitech =====-");

        if(event.includeServer()){
            IGBlockStateProvider blockStateProvider = new IGBlockStateProvider(generator, helper);
            generator.addProvider(true, blockStateProvider);
            generator.addProvider(true, new IGItemModelProvider(generator, helper));
            generator.addProvider(true, new IGComplexItemModelProvider(out, helper));
            BlockTagsProvider blockTags = new IGBlockTags(out, lookup, helper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new IGFluidTags(out, lookup, helper));
            generator.addProvider(true, new IGItemTags(out, lookup, blockTags.contentsGetter(), helper));
            generator.addProvider(true, new IGDynamicModelProvider(blockStateProvider, out, helper));
            generator.addProvider(true, new IGRecipes(out));
            generator.addProvider(true, new LootTableProvider(out, Collections.emptySet(), List.of(new SubProviderEntry(IGBlockLootProvider::new, LootContextParamSets.BLOCK))));
        }
    }

}
