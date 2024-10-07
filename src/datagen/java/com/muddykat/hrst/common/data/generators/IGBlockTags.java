package com.muddykat.hrst.common.data.generators;

import com.muddykat.hrst.core.lib.HRSTLib;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;


public class IGBlockTags extends BlockTagsProvider
{
	public IGBlockTags(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, HRSTLib.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider)
	{
		HRSTLib.HRST_LOGGER.info("HRST Block Tags");
	}
}
