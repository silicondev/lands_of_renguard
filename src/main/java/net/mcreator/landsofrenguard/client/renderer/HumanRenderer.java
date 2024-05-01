
package net.mcreator.landsofrenguard.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import net.mcreator.landsofrenguard.entity.HumanEntity;

public class HumanRenderer extends HumanoidMobRenderer<HumanEntity, HumanoidModel<HumanEntity>> {
	public HumanRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(HumanEntity entity) {
		if (entity.GetGender() == 0) {
			return new ResourceLocation("lands_of_renguard:textures/entities/human_male_1.png");
		} else {
			return new ResourceLocation("lands_of_renguard:textures/entities/human_female_1.png");
		}
	}
}
