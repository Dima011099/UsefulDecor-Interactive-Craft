package com.dweb.useful_interactive;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.ui.KeyCabinetScreen;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

@SuppressWarnings("null")
public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
			MenuScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);
			BlockEntityRendererRegistry.register(ModBlockEntites.WALL_CLOCK, WallClockRenderer::new);
	}
}