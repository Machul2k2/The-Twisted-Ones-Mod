package theTwistedOnes.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import theTwistedOnes.TheTwistedOnes;
import theTwistedOnes.entities.TwistedBonnie;

public class TwistedBonnieModel extends AnimatedGeoModel<TwistedBonnie> {

	@Override
	public ResourceLocation getAnimationResource(TwistedBonnie animatable) {
		return new ResourceLocation(TheTwistedOnes.MODID, "animations/entities/twistedbonnie.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TwistedBonnie object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "geo/entities/twistedbonniemodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TwistedBonnie object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "textures/entities/twistedbonnietexture.png");
	}

	@Override
	public void setCustomAnimations(TwistedBonnie animatable, int instanceId, AnimationEvent customPredicate) {
		super.setCustomAnimations(animatable, instanceId, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(head != null)
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
