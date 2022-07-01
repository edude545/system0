package net.ethobat.system0;


import net.ethobat.system0.registry.S0Blocks;
import net.ethobat.system0.registry.S0Items;
import net.ethobat.system0.registry.S0Registrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Random;
import java.util.function.Supplier;

public class System0 implements ModInitializer {

	public static final String MOD_ID = "system0";

	public static final ItemGroup ITEM_GROUP_ARMOR = gr("armors", ()->new ItemStack(S0Items.Armor.INDIGONITE_CHESTPLATE));
	public static final ItemGroup ITEM_GROUP_COMPONENTS = gr("components", ()->new ItemStack(S0Items.Components.ORGIMEX_DUAL_CELL));
	public static final ItemGroup ITEM_GROUP_CRAFTING = gr("crafting", ()->new ItemStack(S0Items.Crafting.INDIGONITE_COMPOSITE));
	public static final ItemGroup ITEM_GROUP_DECORATION = gr("decoration", ()->new ItemStack(S0Blocks.INDIGONITE_TRIM));
	public static final ItemGroup ITEM_GROUP_NETWORK = gr("network", ()->new ItemStack(S0Blocks.FLASHTIN_DIPOLE));
	public static final ItemGroup ITEM_GROUP_PSIONICS = gr("psionics", ()->new ItemStack(S0Items.Crafting.NEXXINE_SYMBOL));
	public static final ItemGroup ITEM_GROUP_TOOLS = gr("tools", ()->new ItemStack(S0Items.Tools.CLANGER));

	public static final Random random = new Random();

	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		S0Registrar.init();
		System.out.println("System0 initialized");
	}

//	public void onInitializeClient() {
//		S0Registrar.initClient();
//		System.out.println("System0 client initialized");
//	}

	private static ItemGroup gr(String name,  Supplier<ItemStack> stackSupplier) {
		return FabricItemGroupBuilder.build(new Identifier(MOD_ID, name), stackSupplier);
	}

}
