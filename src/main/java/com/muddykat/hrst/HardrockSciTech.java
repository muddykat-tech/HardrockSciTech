package com.muddykat.hrst;

import com.muddykat.hrst.client.IGClientRenderHandler;
import com.muddykat.hrst.core.lib.HRSTLib;
import com.muddykat.hrst.core.registration.HRSTContent;
import com.muddykat.hrst.core.registration.IGRecipeSerializers;
import com.muddykat.hrst.core.registration.HRSTRegistrationHolder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HRSTLib.MODID)
public class HardrockSciTech {

    public HardrockSciTech() {
        IEventBus modEventBus =  FMLJavaModLoadingContext.get().getModEventBus();
        HRSTLib.HRST_LOGGER.info("HRST Starting");
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        IGRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        HRSTRegistrationHolder.addRegistersToEventBus(modEventBus);
        HRSTContent.modContruction(modEventBus);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        IGClientRenderHandler.register();
        IGClientRenderHandler.init(event);
        HRSTContent.initializeManualEntries();
    }

    public void setup(final FMLCommonSetupEvent event)
    {
    }
}
