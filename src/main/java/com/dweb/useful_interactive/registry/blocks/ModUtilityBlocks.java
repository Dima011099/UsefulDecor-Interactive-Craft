package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.keybox.KeyBoxBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;



public class ModUtilityBlocks {
        //Ключи функциональных блоков
        
    public static final ResourceKey<Block> BATANIC_TABLE_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "batanic_table")
    );
    
    public static final ResourceKey<Block> KEYBOX_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "keybox")
    );



    
    //функциональные блоки
    public static final Block BATANIC_TABLE_BLOCK = ModBlocks.registerBlock(
            new KeyBoxBlock(BlockBehaviour.Properties.of().setId(BATANIC_TABLE_KEY).strength(2.0f, 50.0f)), 
            BATANIC_TABLE_KEY
    );

    public static final Block KEYBOX_BLOCK = ModBlocks.registerBlock(
            new KeyBoxBlock(BlockBehaviour.Properties.of().setId(KEYBOX_KEY).strength(4.0f, 50.0f)), 
            KEYBOX_KEY
    );

    public static void register() {}
}
