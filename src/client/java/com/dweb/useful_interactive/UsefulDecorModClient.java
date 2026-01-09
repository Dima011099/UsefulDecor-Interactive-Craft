package com.dweb.useful_interactive;

import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.ui.ModScreenHandlers;
import com.dweb.useful_interactive.ui.KeyCabinetScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;


public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		    BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); 
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);

			HandledScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);

	}
}