package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.block.cabinet.BoxCabinetBlock;
import com.dweb.useful_interactive.block.chest.BoxBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
* Registry class for all storage and container blocks in the mod.
* Manages configuration keys and physical block instances for boxes and cabinets.
*/
@SuppressWarnings("null")
public class ModStorageBlocks {
    
    // --- Block Resource Keys ---

    public static final ResourceKey<Block> BOX_KEY = ModBlocks.createBlockKey("box");
    public static final ResourceKey<Block> OAK_BOX_KEY = ModBlocks.createBlockKey("oak_box");
    public static final ResourceKey<Block> BIRCH_BOX_KEY = ModBlocks.createBlockKey("birch_box");

    public static final ResourceKey<Block> VAMPIRE_CHEST_KEY = ModBlocks.createBlockKey("vampire_chest");
    public static final ResourceKey<Block> DARK_CHEST_KEY = ModBlocks.createBlockKey("dark_chest");
    public static final ResourceKey<Block> SPIDER_CHEST_KEY = ModBlocks.createBlockKey("spider_chest");

    public static final ResourceKey<Block> BIRCH_BOX_CABINET_KEY = ModBlocks.createBlockKey("birch_box_cabinet");
    public static final ResourceKey<Block> BROWN_CABINET_KEY = ModBlocks.createBlockKey("brown_cabinet");


    // --- Block Instances ---

    public static final Block BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(BOX_KEY).strength(2.0f, 50.0f)), 
            BOX_KEY
    );
   
    public static final Block OAK_BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(OAK_BOX_KEY).strength(4.0f, 50.0f)), 
            OAK_BOX_KEY
    );

    public static final Block  BIRCH_BOX_BLOCK = ModBlocks.registerBlock(
        new BoxBlock(BlockBehaviour.Properties.of().setId(BIRCH_BOX_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_KEY
    );



    public static final Block VAMPIRE_CHEST_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(VAMPIRE_CHEST_KEY).strength(2.0f, 50.0f)), 
            VAMPIRE_CHEST_KEY
    );
    public static final Block DARK_CHEST_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(DARK_CHEST_KEY).strength(2.0f, 50.0f)), 
            DARK_CHEST_KEY
    );
    public static final Block SPIDER_CHEST_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(SPIDER_CHEST_KEY).strength(2.0f, 50.0f)), 
            SPIDER_CHEST_KEY
    );




    public static final Block BIRCH_BOX_CABINET_BLOCK = ModBlocks.registerBlock(
        new BoxCabinetBlock(BlockBehaviour.Properties.of().setId(BIRCH_BOX_CABINET_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_CABINET_KEY
    );

    public static final Block BROWN_CABINET_BLOCK = ModBlocks.registerBlock(
        new BoxCabinetBlock(BlockBehaviour.Properties.of().setId(BROWN_CABINET_KEY).strength(3.0f, 50.0f)), 
        BROWN_CABINET_KEY
    );
    /**
    * Initializes the storage blocks registry class.
    * Invoked by the main ModBlocks class to guarantee correct loading order.
    */
    public static void register() {}
}
