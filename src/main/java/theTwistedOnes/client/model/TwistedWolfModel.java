package theTwistedOnes.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import theTwistedOnes.TheTwistedOnes;
import theTwistedOnes.entities.TwistedWolf;

public class TwistedWolfModel extends AnimatedGeoModel<TwistedWolf> {

	@Override
	public ResourceLocation getAnimationResource(TwistedWolf animatable) {
		return new ResourceLocation(TheTwistedOnes.MODID, "animations/entities/twistedwolf.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TwistedWolf object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "geo/entities/twistedwolfmodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TwistedWolf object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "textures/entities/twistedwolftexture.png");
	}
	
	@Override
	public void setCustomAnimations(TwistedWolf animatable, int instanceId, AnimationEvent customPredicate) {
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
