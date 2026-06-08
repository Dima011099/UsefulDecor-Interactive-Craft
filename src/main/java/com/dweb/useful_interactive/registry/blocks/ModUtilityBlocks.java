package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.clock.WallClockBlock;
import com.dweb.useful_interactive.block.keybox.KeyBoxBlock;
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

        public static final ResourceKey<Block> BOTANIC_TABLE_KEY = ModBlocks.createBlockKey("botanic_table"); // Исправлена опечатка batanic -> botanic
        public static final ResourceKey<Block> KEYBOX_KEY = ModBlocks.createBlockKey("keybox");

        public static final ResourceKey<Block> OAK_CLOCK_KEY = ModBlocks.createBlockKey("oak_clock");
        public static final ResourceKey<Block> METAL_CLOCK_KEY = ModBlocks.createBlockKey("metal_clock");
        public static final ResourceKey<Block> BIRCH_CLOCK_KEY = ModBlocks.createBlockKey("birch_clock");
        public static final ResourceKey<Block> OBSIDIAN_CLOCK_KEY = ModBlocks.createBlockKey("obsidian_clock");

        // --- Block Instances ---

        public static final Block BATANIC_TABLE_BLOCK = ModBlocks.registerBlock(
                new KeyBoxBlock(BlockBehaviour.Properties.of().setId(BOTANIC_TABLE_KEY).strength(2.0f, 50.0f)), 
                BOTANIC_TABLE_KEY
        );


        public static final Block KEYBOX_BLOCK = ModBlocks.registerBlock(
                new KeyBoxBlock(BlockBehaviour.Properties.of().setId(KEYBOX_KEY).strength(4.0f, 50.0f)), 
                KEYBOX_KEY
        );

        public static final Block OAK_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(OAK_CLOCK_KEY).strength(4.0f, 50.0f)), 
                OAK_CLOCK_KEY
        );

        public static final Block METAL_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(METAL_CLOCK_KEY).strength(4.0f, 50.0f)), 
                METAL_CLOCK_KEY
        );

        public static final Block BIRCH_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(BIRCH_CLOCK_KEY).strength(4.0f, 50.0f)), 
                BIRCH_CLOCK_KEY
        );

        public static final Block OBSIDIAN_CLOCK_BLOCK = ModBlocks.registerBlock(
                new WallClockBlock(BlockBehaviour.Properties.of().setId(OBSIDIAN_CLOCK_KEY).strength(4.0f, 50.0f)), 
                OBSIDIAN_CLOCK_KEY
        );


        /**
        * Initializes the utility blocks registry class.
        * Called during mod initialization to force class loading and static block registration.
        */
        public static void register() {
                UsefulDecorMod.LOGGER.info("Registering Utility Blocks for " + UsefulDecorMod.MOD_ID);
        }
}
