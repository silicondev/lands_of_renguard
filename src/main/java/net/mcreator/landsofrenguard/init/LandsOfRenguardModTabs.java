
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.landsofrenguard.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.landsofrenguard.LandsOfRenguardMod;

public class LandsOfRenguardModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LandsOfRenguardMod.MODID);
	public static final RegistryObject<CreativeModeTab> WEAPONS_TAB = REGISTRY.register("weapons_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.lands_of_renguard.weapons_tab")).icon(() -> new ItemStack(LandsOfRenguardModItems.EXCALIBUR.get())).displayItems((parameters, tabData) -> {
				tabData.accept(LandsOfRenguardModItems.EXCALIBUR.get());
			})

					.build());
	public static final RegistryObject<CreativeModeTab> MOBS_TAB = REGISTRY.register("mobs_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.lands_of_renguard.mobs_tab")).icon(() -> new ItemStack(Blocks.BUBBLE_COLUMN)).displayItems((parameters, tabData) -> {
				tabData.accept(LandsOfRenguardModItems.SHADE_SPAWN_EGG.get());
				tabData.accept(LandsOfRenguardModItems.HUMAN_SPAWN_EGG.get());
			})

					.build());
}
