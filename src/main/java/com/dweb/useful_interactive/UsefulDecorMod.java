package com.dweb.useful_interactive;

import net.fabricmc.api.ModInitializer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dweb.useful_interactive.block.Box;
import com.dweb.useful_interactive.items.KeyItem;
import com.dweb.useful_interactive.util.ModComponentType;
import com.dweb.useful_interactive.util.ModItemGroups;

public class UsefulDecorMod implements ModInitializer {
	public static final String MOD_ID = "useful_interactive";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




	@Override
	public void onInitialize() {
		ModComponentType.register();

		KeyItem.register();
		Box.register();

		ModItemGroups.registerItemGroups();
	}
}