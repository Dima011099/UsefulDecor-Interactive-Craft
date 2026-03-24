package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.door.DoorDecor;
import com.dweb.useful_interactive.block.window.HorizontalDecorativePaneBlock;
import com.dweb.useful_interactive.block.window.HorizontalPaneBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;


public class ModArchitecturalBlocks {
     // Для дверей Ключи (Keys)
    public static final ResourceKey<Block> OAK_DECOR_DOOR_KEY = ResourceKey.create(//ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_decor_door")//of
    );

    public static final ResourceKey<Block> METAL_DECOR_DOOR_KEY = ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "metal_decor_door")//of
    );

    public static final ResourceKey<Block> BIRCH_DECOR_DOOR_KEY = ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decor_door")//of
    );

    public static final ResourceKey<Block> GLASS_DECOR_DOOR_KEY = ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "glass_decor_door")//of
    );

    // Ключи стеклянной панели
    public static final ResourceKey<Block> OAK_GLASS_PANE_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_glass_pane")//of
    );

    public static final ResourceKey<Block> OAK_IRON_GLASS_PANE_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_iron_glass_pane")//of
    );

    public static final ResourceKey<Block> IRON_GLASS_PANE_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "iron_glass_pane")//of
    );

    public static final ResourceKey<Block> BIRCH_IRON_GLASS_PANE_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_iron_glass_pane")//of
    );

    public static final ResourceKey<Block> BIRCH_GARDEN_TRELLIS_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_garden_trellis")//of
    );

    public static final ResourceKey<Block> BIRCH_DECORATIVE_STRIP_1_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decorative_strip_1")//of
    );

    public static final ResourceKey<Block> BIRCH_DECORATIVE_STRIP_2_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decorative_strip_2")//of
    );




    // Блок дверей
    @SuppressWarnings("null")
    public static final Block OAK_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .setId(OAK_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .noOcclusion() // Двери имеют прозрачные части
        ),
        OAK_DECOR_DOOR_KEY
    );
@SuppressWarnings("null")
    public static final Block METAL_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                BlockBehaviour.Properties.of() // BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .setId(METAL_DECOR_DOOR_KEY) //setId
                        .strength(2.0f)
                        .noOcclusion() // Двери имеют прозрачные части noOcclusion
        ),
        METAL_DECOR_DOOR_KEY
    );
@SuppressWarnings("null")
    public static final Block BIRCH_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.BIRCH, 
                BlockBehaviour.Properties.of()
                        .setId(BIRCH_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .noOcclusion() // Двери имеют прозрачные части
        ),
        BIRCH_DECOR_DOOR_KEY
    );
@SuppressWarnings("null")
    public static final Block GLASS_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.BIRCH, 
                BlockBehaviour.Properties.of()
                        .setId(GLASS_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .noOcclusion() // Двери имеют прозрачные части
        ),
        GLASS_DECOR_DOOR_KEY
    );



    // Блок стеклянной панели
    // Регистрация блока панели

    @SuppressWarnings("null")
    public static final Block OAK_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(BlockBehaviour.Properties.of().setId(OAK_GLASS_PANE_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.GLASS)), //SoundType
        OAK_GLASS_PANE_KEY
    );
@SuppressWarnings("null")
    public static final Block OAK_IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(BlockBehaviour.Properties.of().setId(OAK_IRON_GLASS_PANE_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        // SoundType -> SoundType
        .sound(SoundType.GLASS)),
        OAK_IRON_GLASS_PANE_KEY
    );
@SuppressWarnings("null")
    public static final Block IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(BlockBehaviour.Properties.of().setId(IRON_GLASS_PANE_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.GLASS)),
        IRON_GLASS_PANE_KEY
    );

@SuppressWarnings("null")
    public static final Block BIRCH_IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(BlockBehaviour.Properties.of().setId(BIRCH_IRON_GLASS_PANE_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.GLASS)),
        BIRCH_IRON_GLASS_PANE_KEY
    );


    //birch_garden_trellis
    @SuppressWarnings("null")
    public static final Block BIRCH_GARDEN_TRELLIS_BLOCK = ModBlocks.registerBlock(
        new HorizontalDecorativePaneBlock(BlockBehaviour.Properties.of().setId(BIRCH_GARDEN_TRELLIS_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.WOOD)),
        BIRCH_GARDEN_TRELLIS_KEY
    );
@SuppressWarnings("null")
    public static final Block BIRCH_DECORATIVE_STRIP_1_BLOCK = ModBlocks.registerBlock(
        new HorizontalDecorativePaneBlock(BlockBehaviour.Properties.of().setId(BIRCH_DECORATIVE_STRIP_1_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.WOOD)),
        BIRCH_DECORATIVE_STRIP_1_KEY
    );
@SuppressWarnings("null")
    public static final Block BIRCH_DECORATIVE_STRIP_2_BLOCK = ModBlocks.registerBlock(
        new HorizontalDecorativePaneBlock(BlockBehaviour.Properties.of().setId(BIRCH_DECORATIVE_STRIP_2_KEY)
        .noOcclusion() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sound(SoundType.WOOD)),
        BIRCH_DECORATIVE_STRIP_2_KEY
    );

    public static void register() {}
}
