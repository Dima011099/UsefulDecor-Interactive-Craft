package com.dweb.useful_interactive;

import com.dweb.useful_interactive.block.ModBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;


public class UsefulDecorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		    BlockRenderLayerMap.putBlock(ModBlocks.OAK_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); //BlockRenderLayer.CUTOUT
			BlockRenderLayerMap.putBlock(ModBlocks.BIRCH_DECOR_DOOR, BlockRenderLayer.TRANSLUCENT); 
			BlockRenderLayerMap.putBlock(ModBlocks.OAK_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModBlocks.OAK_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModBlocks.IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
			BlockRenderLayerMap.putBlock(ModBlocks.BIRCH_IRON_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
	}
}