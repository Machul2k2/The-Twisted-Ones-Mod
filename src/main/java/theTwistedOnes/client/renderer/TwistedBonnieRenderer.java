package theTwistedOnes.client.renderer;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import theTwistedOnes.client.model.TwistedBonnieModel;
import theTwistedOnes.entities.TwistedBonnie;

public class TwistedBonnieRenderer extends GeoEntityRenderer<TwistedBonnie> {
	
	@Override
	public RenderType getRenderType(TwistedBonnie animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation)
	{
		return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
	}
	
	public TwistedBonnieRenderer(EntityRendererProvider.Context context) {
		super(context, new TwistedBonnieModel());
		this.shadowRadius = 0.80f;
	}
	

}
