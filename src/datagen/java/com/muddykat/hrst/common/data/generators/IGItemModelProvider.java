package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

public class IGItemModelProvider extends ItemModelProvider {


    private final Logger logger = HRSTLib.getNewLogger();
    public IGItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), HRSTLib.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
