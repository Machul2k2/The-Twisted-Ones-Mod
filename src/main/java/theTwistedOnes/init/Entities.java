package theTwistedOnes.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import theTwistedOnes.TheTwistedOnes;
import theTwistedOnes.entities.TwistedBonnie;
import theTwistedOnes.entities.TwistedFoxy;
import theTwistedOnes.entities.TwistedFreddy;
import theTwistedOnes.entities.TwistedWolf;

@Mod.EventBusSubscriber(modid = TheTwistedOnes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheTwistedOnes.MODID);
	
	public static final RegistryObject<EntityType<TwistedFreddy>> TWISTED_FREDDY = ENTITIES.register("twisted_freddy", () -> EntityType.Builder.of(TwistedFreddy::new, MobCategory.MONSTER).clientTrackingRange(8).immuneTo(Blocks.SWEET_BERRY_BUSH).fireImmune().sized(1.1f, 2.8f).build(new ResourceLocation(TheTwistedOnes.MODID, "twistedfreddy").toString()));
	public static final RegistryObject<EntityType<TwistedBonnie>> TWISTED_BONNIE = ENTITIES.register("twisted_bonnie", () -> EntityType.Builder.of(TwistedBonnie::new, MobCategory.MONSTER).clientTrackingRange(8).immuneTo(Blocks.SWEET_BERRY_BUSH).fireImmune().sized(1.05f, 2.8f).build(new ResourceLocation(TheTwistedOnes.MODID, "twistedbonnie").toString()));
	public static final RegistryObject<EntityType<TwistedFoxy>> TWISTED_FOXY = ENTITIES.register("twisted_foxy", () -> EntityType.Builder.of(TwistedFoxy::new, MobCategory.MONSTER).clientTrackingRange(8).immuneTo(Blocks.SWEET_BERRY_BUSH).fireImmune().sized(1.05f, 2.8f).build(new ResourceLocation(TheTwistedOnes.MODID, "twistedfoxy").toString()));
	public static final RegistryObject<EntityType<TwistedWolf>> TWISTED_WOLF = ENTITIES.register("twisted_wolf", () -> EntityType.Builder.of(TwistedWolf::new, MobCategory.MONSTER).clientTrackingRange(8).immuneTo(Blocks.SWEET_BERRY_BUSH).fireImmune().sized(1.05f, 2.8f).build(new ResourceLocation(TheTwistedOnes.MODID, "twistedwolf").toString()));

	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(Entities.TWISTED_FREDDY.get(), TwistedFreddy.registerAttributes().build());
		event.put(Entities.TWISTED_BONNIE.get(), TwistedBonnie.registerAttributes().build());
		event.put(Entities.TWISTED_FOXY.get(), TwistedFoxy.registerAttributes().build());
		event.put(Entities.TWISTED_WOLF.get(), TwistedWolf.registerAttributes().build());
	}
}
