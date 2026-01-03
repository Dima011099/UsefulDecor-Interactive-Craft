package com.dweb.useful_interactive;

import com.dweb.useful_interactive.block.Box;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;


public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		    BlockRenderLayerMap.putBlock(Box.OAK_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); //BlockRenderLayer.CUTOUT
			BlockRenderLayerMap.putBlock(Box.BIRCH_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); 
			BlockRenderLayerMap.putBlock(Box.OAK_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(Box.OAK_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(Box.IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(Box.BIRCH_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
	}
}