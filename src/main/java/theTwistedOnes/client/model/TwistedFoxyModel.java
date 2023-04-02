package theTwistedOnes.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import theTwistedOnes.TheTwistedOnes;
import theTwistedOnes.entities.TwistedFoxy;

public class TwistedFoxyModel extends AnimatedGeoModel<TwistedFoxy> {

	@Override
	public ResourceLocation getAnimationResource(TwistedFoxy animatable) {
		return new ResourceLocation(TheTwistedOnes.MODID, "animations/entities/twistedfoxy.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TwistedFoxy object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "geo/entities/twistedfoxymodel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TwistedFoxy object) {
		return new ResourceLocation(TheTwistedOnes.MODID, "textures/entities/twistedfoxytexture.png");
	}
	
	@Override
	public void setCustomAnimations(TwistedFoxy animatable, int instanceId, AnimationEvent customPredicate) {
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
