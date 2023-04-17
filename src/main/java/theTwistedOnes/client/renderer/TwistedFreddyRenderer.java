package theTwistedOnes.client.renderer;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import theTwistedOnes.client.model.TwistedFreddyModel;
import theTwistedOnes.entities.TwistedFreddy;

public class TwistedFreddyRenderer extends GeoEntityRenderer<TwistedFreddy> {
	
	@Override
	public RenderType getRenderType(TwistedFreddy animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation)
	{
		return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
	}
	
	public TwistedFreddyRenderer(EntityRendererProvider.Context context) {
		super(context, new TwistedFreddyModel());
		this.shadowRadius = 0.80f;
	}
	
	
}
