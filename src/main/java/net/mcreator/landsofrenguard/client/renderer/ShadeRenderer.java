
package net.mcreator.landsofrenguard.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import net.mcreator.landsofrenguard.entity.ShadeEntity;

public class ShadeRenderer extends HumanoidMobRenderer<ShadeEntity, HumanoidModel<ShadeEntity>> {
	public ShadeRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(ShadeEntity entity) {
		return new ResourceLocation("lands_of_renguard:textures/entities/shade.png");
	}

	@Override
	protected boolean isShaking(ShadeEntity entity) {
		return true;
	}
}
