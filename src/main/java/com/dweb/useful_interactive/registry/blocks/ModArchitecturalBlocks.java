package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.door.DoorDecor;
import com.dweb.useful_interactive.block.window.HorizontalPaneBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModArchitecturalBlocks {
     // Для дверей Ключи (Keys)
    public static final RegistryKey<Block> OAK_DECOR_DOOR_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "oak_decor_door")
    );

    public static final RegistryKey<Block> METAL_DECOR_DOOR_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "metal_decor_door")
    );

    public static final RegistryKey<Block> BIRCH_DECOR_DOOR_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_decor_door")
    );

    public static final RegistryKey<Block> GLASS_DECOR_DOOR_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "glass_decor_door")
    );

    // Ключи стеклянной панели
    public static final RegistryKey<Block> OAK_GLASS_PANE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "oak_glass_pane")
    );

    public static final RegistryKey<Block> OAK_IRON_GLASS_PANE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "oak_iron_glass_pane")
    );

    public static final RegistryKey<Block> IRON_GLASS_PANE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "iron_glass_pane")
    );

    public static final RegistryKey<Block> BIRCH_IRON_GLASS_PANE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "birch_iron_glass_pane")
    );

    public static final RegistryKey<Block> BIRCH_GARDEN_TRELLIS_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "birch_garden_trellis")
    );



    // Блок дверей
    public static final Block OAK_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                AbstractBlock.Settings.create()
                        .registryKey(OAK_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        OAK_DECOR_DOOR_KEY
    );

    public static final Block METAL_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                AbstractBlock.Settings.create()
                        .registryKey(METAL_DECOR_DOOR_KEY)
                        .strength(2.0f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        METAL_DECOR_DOOR_KEY
    );

    public static final Block BIRCH_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.BIRCH, 
                AbstractBlock.Settings.create()
                        .registryKey(BIRCH_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        BIRCH_DECOR_DOOR_KEY
    );

    public static final Block GLASS_DECOR_DOOR = ModBlocks.registerBlock(
        new DoorDecor(
                BlockSetType.BIRCH, 
                AbstractBlock.Settings.create()
                        .registryKey(GLASS_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        GLASS_DECOR_DOOR_KEY
    );



    // Блок стеклянной панели
    // Регистрация блока панели
    public static final Block OAK_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(OAK_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        OAK_GLASS_PANE_KEY
    );

    public static final Block OAK_IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(OAK_IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        OAK_IRON_GLASS_PANE_KEY
    );

    public static final Block IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        IRON_GLASS_PANE_KEY
    );

    public static final Block BIRCH_IRON_GLASS_PANE = ModBlocks.registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(BIRCH_IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        BIRCH_IRON_GLASS_PANE_KEY
    );


    //birch_garden_trellis
    public static final Block BIRCH_GARDEN_TRELLIS_BLOCK = ModBlocks.registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(BIRCH_GARDEN_TRELLIS_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.WOOD)),
        BIRCH_GARDEN_TRELLIS_KEY
    );

    public static void register() {}
}
