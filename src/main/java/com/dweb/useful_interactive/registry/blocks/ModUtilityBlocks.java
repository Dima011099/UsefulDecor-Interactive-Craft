package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.clock.WallClockBlock;
import com.dweb.useful_interactive.block.keybox.KeyBoxBlock;
import com.dweb.useful_interactive.block.trash.Trash;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Registry class for functional and utility blocks in the mod.
 * Handles configuration keys and block instances with proper physics.
 */
@SuppressWarnings("null")
public class ModUtilityBlocks {

        // --- Block Resource Keys ---

        public static final ResourceKey<Block> BOTANIC_TABLE_KEY = ModBlocks.createBlockKey("botanic_table");
        public static final ResourceKey<Block> KEYBOX_KEY = ModBlocks.createBlockKey("keybox");

        public static final ResourceKey<Block> CRIMSON_WALL_CLOCK_KEY = ModBlocks.createBlockKey("crimson_wall_clock");
        public static final ResourceKey<Block> OCEANIC_WALL_CLOCK_KEY = ModBlocks.createBlockKey("oceanic_wall_clock");
        public static final ResourceKey<Block> WOODEN_WALL_CLOCK_KEY = ModBlocks.createBlockKey("wooden_wall_clock");
        public static final ResourceKey<Block> OBSIDIAN_WALL_CLOCK_KEY = ModBlocks.createBlockKey("obsidian_wall_clock");
        public static final ResourceKey<Block> TIMBER_RING_CLOCK_KEY = ModBlocks.createBlockKey("timber_ring_clock");
        public static final ResourceKey<Block> BIRCH_ECO_CLOCK_KEY = ModBlocks.createBlockKey("birch_eco_clock");

        public static final ResourceKey<Block> CLASSIC_WALL_CLOCK_KEY = ModBlocks.createBlockKey("classic_wall_clock");
        public static final ResourceKey<Block> DARK_WALL_CLOCK_KEY = ModBlocks.createBlockKey("dark_wall_clock");
        public static final ResourceKey<Block> DAY_WALL_CLOCK_KEY = ModBlocks.createBlockKey("day_wall_clock");
        public static final ResourceKey<Block> DESERT_WALL_CLOCK_KEY = ModBlocks.createBlockKey("desert_wall_clock");
        public static final ResourceKey<Block> FOREST_WALL_CLOCK_KEY = ModBlocks.createBlockKey("forest_wall_clock");
        public static final ResourceKey<Block> NIGHT_WALL_CLOCK_KEY = ModBlocks.createBlockKey("night_wall_clock");
        public static final ResourceKey<Block> SPACE_WALL_CLOCK_KEY = ModBlocks.createBlockKey("space_wall_clock");
        public static final ResourceKey<Block> SPIDER_WALL_CLOCK_KEY = ModBlocks.createBlockKey("spider_wall_clock");
        public static final ResourceKey<Block> VAMPIRE_WALL_CLOCK_KEY = ModBlocks.createBlockKey("vampire_wall_clock");
        public static final ResourceKey<Block> VINTAGE_WALL_CLOCK_KEY = ModBlocks.createBlockKey("vintage_wall_clock");

        public static final ResourceKey<Block> TRASH_KEY = ModBlocks.createBlockKey("trash");
        public static final ResourceKey<Block> DARK_TRASHCAN_KEY = ModBlocks.createBlockKey("dark_trashcan");
        public static final ResourceKey<Block> SPIDER_TRASHCAN_KEY = ModBlocks.createBlockKey("spider_trashcan");
        public static final ResourceKey<Block> VAMPIRE_TRASHCAN_KEY = ModBlocks.createBlockKey("vampire_trashcan");

        // --- Block Instances ---

        public static final Block BATANIC_TABLE_BLOCK = ModBlocks.registerBlock(
                new KeyBoxBlock(BlockBehaviour.Properties.of().setId(BOTANIC_TABLE_KEY).strength(2.0f, 50.0f)), 
                BOTANIC_TABLE_KEY
        );


        public static final Block KEYBOX_BLOCK = ModBlocks.registerBlock(
                new KeyBoxBlock(BlockBehaviour.Properties.of().setId(KEYBOX_KEY).strength(4.0f, 50.0f)), 
                KEYBOX_KEY
        );

        public static final Block CRIMSON_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(CRIMSON_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                CRIMSON_WALL_CLOCK_KEY
        );

        public static final Block OCEANIC_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(OCEANIC_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                OCEANIC_WALL_CLOCK_KEY
        );

        public static final Block WOODEN_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(WOODEN_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                WOODEN_WALL_CLOCK_KEY
        );

        public static final Block OBSIDIAN_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(OBSIDIAN_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                OBSIDIAN_WALL_CLOCK_KEY
        );
        
        public static final Block BIRCH_ECO_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(BIRCH_ECO_CLOCK_KEY).strength(4.0f, 50.0f)), 
                BIRCH_ECO_CLOCK_KEY
        );

        public static final Block TIMBER_RING_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(TIMBER_RING_CLOCK_KEY).strength(4.0f, 50.0f)), 
                TIMBER_RING_CLOCK_KEY
        );




        public static final Block CLASSIC_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(CLASSIC_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                CLASSIC_WALL_CLOCK_KEY
        );
        public static final Block DARK_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(DARK_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                DARK_WALL_CLOCK_KEY
        );
        public static final Block DAY_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(DAY_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                DAY_WALL_CLOCK_KEY
        );
        public static final Block DESERT_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(DESERT_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                DESERT_WALL_CLOCK_KEY
        );
        public static final Block FOREST_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(FOREST_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                FOREST_WALL_CLOCK_KEY
        );
        public static final Block NIGHT_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(NIGHT_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                NIGHT_WALL_CLOCK_KEY
        );
        public static final Block SPACE_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(SPACE_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                SPACE_WALL_CLOCK_KEY
        );
        public static final Block SPIDER_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(SPIDER_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                SPIDER_WALL_CLOCK_KEY
        );
        public static final Block VAMPIRE_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(VAMPIRE_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                VAMPIRE_WALL_CLOCK_KEY
        );
                public static final Block VINTAGE_WALL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(VINTAGE_WALL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                VINTAGE_WALL_CLOCK_KEY
        );



        public static final Block TRASH_BLOCK = ModBlocks.registerBlock(
                new Trash(BlockBehaviour.Properties.of().setId(TRASH_KEY).strength(4.0f, 50.0f)), 
                TRASH_KEY
        );


        public static final Block DARK_TRASHCAN_BLOCK = ModBlocks.registerBlock(
                new Trash(BlockBehaviour.Properties.of().setId(DARK_TRASHCAN_KEY).strength(4.0f, 50.0f)), 
                DARK_TRASHCAN_KEY
        );
        public static final Block SPIDER_TRASHCAN_BLOCK = ModBlocks.registerBlock(
                new Trash(BlockBehaviour.Properties.of().setId(SPIDER_TRASHCAN_KEY).strength(4.0f, 50.0f)), 
                SPIDER_TRASHCAN_KEY
        );
        public static final Block VAMPIRE_TRASHCAN_BLOCK = ModBlocks.registerBlock(
                new Trash(BlockBehaviour.Properties.of().setId(VAMPIRE_TRASHCAN_KEY).strength(4.0f, 50.0f)), 
                VAMPIRE_TRASHCAN_KEY
        );


        /**
        * Initializes the utility blocks registry class.
        * Called during mod initialization to force class loading and static block registration.
        */
        public static void register() {
                UsefulDecorMod.LOGGER.info("Registering Utility Blocks for " + UsefulDecorMod.MOD_ID);
        }
}
