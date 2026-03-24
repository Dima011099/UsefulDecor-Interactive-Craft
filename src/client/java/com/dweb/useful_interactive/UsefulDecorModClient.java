package com.dweb.useful_interactive;

import com.dweb.useful_interactive.ui.KeyCabinetScreen;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {/*
		    BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.GLASS_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); 
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.OAK_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);

			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_GARDEN_TRELLIS_BLOCK, BlockRenderLayer.CUTOUT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_1_BLOCK, BlockRenderLayer.CUTOUT);
			BlockRenderLayerMap.putBlock(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_2_BLOCK, BlockRenderLayer.CUTOUT);

			HandledScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);*/
			// В твоем Client-инициализаторе
			MenuScreens.register(ModScreenHandlers.KEY_CABINET, KeyCabinetScreen::new);


	}
}