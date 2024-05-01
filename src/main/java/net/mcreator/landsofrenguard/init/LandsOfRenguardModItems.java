
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.landsofrenguard.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcreator.landsofrenguard.item.ExcaliburItem;
import net.mcreator.landsofrenguard.LandsOfRenguardMod;

public class LandsOfRenguardModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, LandsOfRenguardMod.MODID);
	public static final RegistryObject<Item> EXCALIBUR = REGISTRY.register("excalibur", () -> new ExcaliburItem());
	public static final RegistryObject<Item> SHADE_SPAWN_EGG = REGISTRY.register("shade_spawn_egg", () -> new ForgeSpawnEggItem(LandsOfRenguardModEntities.SHADE, -1, -1, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
