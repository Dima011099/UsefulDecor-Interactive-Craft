package com.dweb.useful_interactive;

import com.dweb.useful_interactive.ui.KeyCabinetScreen;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

@SuppressWarnings("null")
public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
			MenuScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);
	}
}