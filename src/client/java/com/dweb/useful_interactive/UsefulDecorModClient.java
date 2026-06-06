package com.dweb.useful_interactive;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.ui.KeyCabinetScreen;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@SuppressWarnings("null")
public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
			MenuScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);
			BlockEntityRenderers.register(
				ModBlockEntites.WALL_CLOCK, 
				context -> new WallClockRenderer(context)
		);
	}
}