/*
 * Muddykat
 * Copyright (c) 2024
 *
 * This code is licensed under "GNU LESSER GENERAL PUBLIC LICENSE"
 * Details can be found in the license file in the root folder of this project
 */

package com.muddykat.hrst.core.lib;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class HRSTLib {
    public static final String MODID = "hrst";
    public static final String VERSION = "1.0.0";

    public static final Logger HRST_LOGGER = LogUtils.getLogger();


    public static Logger getNewLogger()
    {
        return  LogUtils.getLogger();
    }
}
