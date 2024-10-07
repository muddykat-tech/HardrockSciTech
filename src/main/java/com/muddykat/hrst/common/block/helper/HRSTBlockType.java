/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.common.block.helper;

import net.minecraft.world.level.block.Block;

public interface HRSTBlockType {
    Block getBlock();

    int getColor(int index);
}
