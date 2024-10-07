/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.core.lib;

import blusunrize.immersiveengineering.api.Lib;
import net.minecraft.resources.ResourceLocation;

public class ResourceUtils {
    public static ResourceLocation hrst(String path){
        return new ResourceLocation(HRSTLib.MODID, path);
    }

    public static ResourceLocation ie(String path){
        return new ResourceLocation(Lib.MODID, path);
    }
}
