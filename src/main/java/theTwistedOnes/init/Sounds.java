package theTwistedOnes.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import theTwistedOnes.TheTwistedOnes;

public class Sounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheTwistedOnes.MODID);

	public static final RegistryObject<SoundEvent> TWISTED_FREDDY_LAUGH = registerSoundEvent("twisted_freddy_laugh");
	public static final RegistryObject<SoundEvent> TWISTED_ANIMATRONIC_STEP = registerSoundEvent("twisted_animatronic_step");
	public static final RegistryObject<SoundEvent> TWISTED_ANIMATRONIC_DEATH = registerSoundEvent("twisted_animatronic_death");

	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(TheTwistedOnes.MODID, name)));
	}
	
}
