package theTwistedOnes.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import theTwistedOnes.TheTwistedOnes;

public class Items {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheTwistedOnes.MODID);
	
	public static final RegistryObject<ForgeSpawnEggItem> TWISTED_FREDDY_SPAWN_EGG = ITEMS.register("twisted_freddy_spawn_egg", () -> new ForgeSpawnEggItem(Entities.TWISTED_FREDDY, 0x833724, 0x121111, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<ForgeSpawnEggItem> TWISTED_BONNIE_SPAWN_EGG = ITEMS.register("twisted_bonnie_spawn_egg", () -> new ForgeSpawnEggItem(Entities.TWISTED_BONNIE, 0xA190BA, 0x2B2239, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<ForgeSpawnEggItem> TWISTED_FOXY_SPAWN_EGG = ITEMS.register("twisted_foxy_spawn_egg", () -> new ForgeSpawnEggItem(Entities.TWISTED_FOXY, 0x5E2525, 0xBCA18D, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<ForgeSpawnEggItem> TWISTED_WOLF_SPAWN_EGG = ITEMS.register("twisted_wolf_spawn_egg", () -> new ForgeSpawnEggItem(Entities.TWISTED_WOLF, 0xA0A0A0, 0x404040, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
