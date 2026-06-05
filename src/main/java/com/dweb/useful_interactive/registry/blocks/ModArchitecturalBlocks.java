package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.block.door.DoorDecor;
import com.dweb.useful_interactive.block.window.HorizontalDecorativePaneBlock;
import com.dweb.useful_interactive.block.window.HorizontalPaneBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;


/**
 * Registry class for decorative architectural components such as doors, glass panes, and trellises.
 * Configures distinct mechanical behaviors, transparency settings, and physical boundaries.
 */
@SuppressWarnings("null")
public class ModArchitecturalBlocks {

    // =========================================================================
    // RESOURCE KEYS
    // =========================================================================

    public static final ResourceKey<Block> OAK_DECOR_DOOR_KEY = ModBlocks.createBlockKey("oak_decor_door");
    public static final ResourceKey<Block> OAK_GLASS_DECOR_DOOR_KEY = ModBlocks.createBlockKey("oak_glass_decor_door");
    public static final ResourceKey<Block> METAL_DECOR_DOOR_KEY = ModBlocks.createBlockKey("metal_decor_door");
    public static final ResourceKey<Block> BIRCH_DECOR_DOOR_KEY = ModBlocks.createBlockKey("birch_decor_door");
    public static final ResourceKey<Block> GLASS_DECOR_DOOR_KEY = ModBlocks.createBlockKey("glass_decor_door");

    public static final ResourceKey<Block> OAK_GLASS_PANE_KEY = ModBlocks.createBlockKey("oak_glass_pane");
    public static final ResourceKey<Block> OAK_IRON_GLASS_PANE_KEY = ModBlocks.createBlockKey("oak_iron_glass_pane");
    public static final ResourceKey<Block> IRON_GLASS_PANE_KEY = ModBlocks.createBlockKey("iron_glass_pane");
    public static final ResourceKey<Block> BIRCH_IRON_GLASS_PANE_KEY = ModBlocks.createBlockKey("birch_iron_glass_pane");

    public static final ResourceKey<Block> BIRCH_GARDEN_TRELLIS_KEY = ModBlocks.createBlockKey("birch_garden_trellis");
    public static final ResourceKey<Block> BIRCH_DECORATIVE_STRIP_1_KEY = ModBlocks.createBlockKey("birch_decorative_strip_1");
    public static final ResourceKey<Block> BIRCH_DECORATIVE_STRIP_2_KEY = ModBlocks.createBlockKey("birch_decorative_strip_2");


    // =========================================================================
    // BLOCK INSTANCES
    // =========================================================================

    // --- Doors ---

    public static final Block OAK_DECOR_DOOR = ModBlocks.registerBlock(
            new DoorDecor(BlockSetType.OAK, createWoodenDoorProps(OAK_DECOR_DOOR_KEY)), OAK_DECOR_DOOR_KEY
    );

    public static final Block OAK_GLASS_DECOR_DOOR = ModBlocks.registerBlock(
            new DoorDecor(BlockSetType.OAK, createWoodenDoorProps(OAK_GLASS_DECOR_DOOR_KEY)), OAK_GLASS_DECOR_DOOR_KEY
    );

    public static final Block METAL_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                BlockBehaviour.Properties.of() // BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .setId(METAL_DECOR_DOOR_KEY) //setId
                        .strength(2.0f)
                        .noOcclusion() // Двери имеют прозрачные части noOcclusion
        ),
        METAL_DECOR_DOOR_KEY
    );
    public static final Block BIRCH_DECOR_DOOR = ModBlocks.registerBlock(
            new DoorDecor(BlockSetType.BIRCH, createWoodenDoorProps(BIRCH_DECOR_DOOR_KEY).mapColor(MapColor.SAND)), BIRCH_DECOR_DOOR_KEY
    );

    public static final Block GLASS_DECOR_DOOR = ModBlocks.registerBlock(
            new DoorDecor(BlockSetType.BIRCH, createWoodenDoorProps(GLASS_DECOR_DOOR_KEY).mapColor(MapColor.SAND)), GLASS_DECOR_DOOR_KEY
    );

    // --- Glass Panes ---

    public static final Block OAK_GLASS_PANE = ModBlocks.registerBlock(
            new HorizontalPaneBlock(createGlassPaneProps(OAK_GLASS_PANE_KEY)), OAK_GLASS_PANE_KEY
    );

    public static final Block OAK_IRON_GLASS_PANE = ModBlocks.registerBlock(
            new HorizontalPaneBlock(createGlassPaneProps(OAK_IRON_GLASS_PANE_KEY)), OAK_IRON_GLASS_PANE_KEY
    );

    public static final Block IRON_GLASS_PANE = ModBlocks.registerBlock(
            new HorizontalPaneBlock(createGlassPaneProps(IRON_GLASS_PANE_KEY)), IRON_GLASS_PANE_KEY
    );

    public static final Block BIRCH_IRON_GLASS_PANE = ModBlocks.registerBlock(
            new HorizontalPaneBlock(createGlassPaneProps(BIRCH_IRON_GLASS_PANE_KEY)), BIRCH_IRON_GLASS_PANE_KEY
    );

    // --- Decorative Strips & Trellises ---

    public static final Block BIRCH_GARDEN_TRELLIS_BLOCK = ModBlocks.registerBlock(
            new HorizontalDecorativePaneBlock(createWoodenPaneProps(BIRCH_GARDEN_TRELLIS_KEY)), BIRCH_GARDEN_TRELLIS_KEY
    );

    public static final Block BIRCH_DECORATIVE_STRIP_1_BLOCK = ModBlocks.registerBlock(
            new HorizontalDecorativePaneBlock(createWoodenPaneProps(BIRCH_DECORATIVE_STRIP_1_KEY)), BIRCH_DECORATIVE_STRIP_1_KEY
    );

    public static final Block BIRCH_DECORATIVE_STRIP_2_BLOCK = ModBlocks.registerBlock(
            new HorizontalDecorativePaneBlock(createWoodenPaneProps(BIRCH_DECORATIVE_STRIP_2_KEY)), BIRCH_DECORATIVE_STRIP_2_KEY
    );

    // =========================================================================
    // PROPERTY FACTORIES
    // =========================================================================


    private static BlockBehaviour.Properties createWoodenDoorProps(ResourceKey<Block> key) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .setId(key)
                .strength(1.5f)
                .sound(SoundType.WOOD)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties createGlassPaneProps(ResourceKey<Block> key) {
        return BlockBehaviour.Properties.of()
                .setId(key)
                .noOcclusion()
                .strength(2.0f)
                .sound(SoundType.GLASS);
    }

    private static BlockBehaviour.Properties createWoodenPaneProps(ResourceKey<Block> key) {
        return BlockBehaviour.Properties.of()
                .setId(key)
                .noOcclusion()
                .strength(2.0f)
                .sound(SoundType.WOOD);
    }

    public static void register() {}
}
