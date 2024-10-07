package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

public class IGItemModelProvider extends ItemModelProvider {


    private final Logger logger = HRSTLib.getNewLogger();
    public IGItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), HRSTLib.MODID, existingFileHelper);
    }

    private void generateBlockItem(String item_name, String parent_loc)
    {
        String itemLocation = new ResourceLocation(HRSTLib.MODID, "item/"+ item_name).getPath();
        ResourceLocation parentLocation = new ResourceLocation(HRSTLib.MODID, "block/"+parent_loc);

        withExistingParent(itemLocation, parentLocation);
    }

    @Override
    protected void registerModels() {
        generateBlockItem("unknown_engineering_block", "static_block/unknown_engineering_block");
    }
}
