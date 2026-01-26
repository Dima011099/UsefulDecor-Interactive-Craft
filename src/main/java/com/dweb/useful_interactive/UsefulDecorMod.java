package com.dweb.useful_interactive;

import net.fabricmc.api.ModInitializer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.ModItemGroups;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.registry.blocks.ModBlocks;
import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.items.ModItems;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

public class UsefulDecorMod implements ModInitializer {
	public static final String MOD_ID = "useful_interactive";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




	@Override
	public void onInitialize() {
		ModComponentType.register();
	ModRecipeTypes.register();

		KeyItem.register();
		ModBlocks.register();
		ModItems.register();


		ModBlockEntites.register();


		ModScreenHandlers.registerScreenHandlers();

		ModItemGroups.registerItemGroups();

	}
}