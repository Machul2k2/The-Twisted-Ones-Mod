package theTwistedOnes.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import theTwistedOnes.TheTwistedOnes;
import theTwistedOnes.entities.TwistedFreddy;

public class TwistedFreddyModel extends AnimatedGeoModel<TwistedFreddy> {

	@Override
	public ResourceLocation getAnimationResource(TwistedFreddy animatable) {
		return new ResourceLocation(TheTwistedOnes.MODID, "animations/entities/twistedfreddy.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TwistedFreddy object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "geo/entities/twistedfreddymodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TwistedFreddy object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "textures/entities/twistedfreddytexture.png");
	}
	
	@Override
	public void setCustomAnimations(TwistedFreddy animatable, int instanceId, AnimationEvent customPredicate) {
		super.setCustomAnimations(animatable, instanceId, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if(head != null)
		{
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
		
//		IBone upperHalfBody = this.getAnimationProcessor().getBone("upperHalfBody");
//		if(animatable.hurtTime > 0)
//		{
//			upperHalfBody.setRotationX(0.01F);
//			upperHalfBody.setPositionZ(0.05F);
//		}
	}
}
