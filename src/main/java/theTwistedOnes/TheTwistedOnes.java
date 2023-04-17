package theTwistedOnes;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.GeckoLib;
import theTwistedOnes.client.renderer.TwistedBonnieRenderer;
import theTwistedOnes.client.renderer.TwistedFoxyRenderer;
import theTwistedOnes.client.renderer.TwistedFreddyRenderer;
import theTwistedOnes.client.renderer.TwistedWolfRenderer;
import theTwistedOnes.init.Entities;
import theTwistedOnes.init.Items;
import theTwistedOnes.init.Sounds;

import org.slf4j.Logger;

@Mod(TheTwistedOnes.MODID)
public class TheTwistedOnes
{
    public static final String MODID = "thetwistedones";

    private static final Logger LOGGER = LogUtils.getLogger();

    public TheTwistedOnes()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        Entities.ENTITIES.register(modEventBus);
        Items.ITEMS.register(modEventBus);
        Sounds.SOUND_EVENTS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        GeckoLib.initialize();
    }

    @SuppressWarnings("deprecation")
	private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        
        event.enqueueWork(() -> SpawnPlacements.register(Entities.TWISTED_FREDDY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
        event.enqueueWork(() -> SpawnPlacements.register(Entities.TWISTED_BONNIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
        event.enqueueWork(() -> SpawnPlacements.register(Entities.TWISTED_FOXY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
        event.enqueueWork(() -> SpawnPlacements.register(Entities.TWISTED_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(Entities.TWISTED_FREDDY.get(), TwistedFreddyRenderer::new);
            EntityRenderers.register(Entities.TWISTED_BONNIE.get(), TwistedBonnieRenderer::new);
            EntityRenderers.register(Entities.TWISTED_FOXY.get(), TwistedFoxyRenderer::new);
            EntityRenderers.register(Entities.TWISTED_WOLF.get(), TwistedWolfRenderer::new);
        }
    }
}
