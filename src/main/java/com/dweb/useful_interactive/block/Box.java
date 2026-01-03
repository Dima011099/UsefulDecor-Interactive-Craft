package com.dweb.useful_interactive.block;

import net.minecraft.block.entity.BlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import com.dweb.useful_interactive.UsefulDecorMod;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;



public class Box {
    // Регистрируем ключи блоков
    public static final RegistryKey<Block> BOX_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "box")
    );

    public static final RegistryKey<Block> OAK_BOX_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "oak_box")
    );

    public static final RegistryKey<Block> BIRCH_BOX_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_box")
    );

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

    //Ключи функциональных блоков
    public static final RegistryKey<Block> BATANIC_TABLE_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "batanic_table")
    );
    public static final RegistryKey<Block> HOUSEKEEPER_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "housekeeper")
    );

    // Регистрируем ключи предметов блока
    public static final RegistryKey<Item> BOX_ITEM_KEY = RegistryKey.of(
            RegistryKeys.ITEM,
            Identifier.of(UsefulDecorMod.MOD_ID, "box")
    );

    public static final RegistryKey<Item> OAK_BOX_ITEM_KEY = RegistryKey.of(
            RegistryKeys.ITEM,
            Identifier.of(UsefulDecorMod.MOD_ID, "oak_box")
    );

    public static final RegistryKey<Item> BIRCH_BOX_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_box")
    );

    // Для дверей предметы блока
    public static final RegistryKey<Item> OAK_DECOR_DOOR_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "oak_decor_door")
    );

    public static final RegistryKey<Item> METAL_DECOR_DOOR_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "metal_decor_door")
    );

    public static final RegistryKey<Item> BIRCH_DECOR_DOOR_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_decor_door")
    );


    //Для стеклянной понели
    public static final RegistryKey<Item> OAK_GLASS_PANE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "oak_glass_pane")
    );

    public static final RegistryKey<Item> OAK_IRON_GLASS_PANE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "oak_iron_glass_pane")
    );

    public static final RegistryKey<Item> IRON_GLASS_PANE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "iron_glass_pane")
    );

    public static final RegistryKey<Item> BIRCH_IRON_GLASS_PANE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_iron_glass_pane")
    );

    //Для функциональных блоков
    public static final RegistryKey<Item> BATANIC_TABLE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "batanic_table")
    );

    public static final RegistryKey<Item> HOUSEKEEPER_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "housekeeper")
    );


    // 3. Регистрация самого Блока
    public static final Block BOX_BLOCK = registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(BOX_KEY).strength(2.0f, 50.0f)), 
            BOX_KEY
    );

    public static final Block OAK_BOX_BLOCK = registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(OAK_BOX_KEY).strength(4.0f, 50.0f)), 
            OAK_BOX_KEY
    );

    public static final Block  BIRCH_BOX_BLOCK = registerBlock(
        new BoxBlock(AbstractBlock.Settings.create().registryKey(BIRCH_BOX_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_KEY
    );

    // Блок дверей
    public static final Block OAK_DECOR_DOOR = registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                AbstractBlock.Settings.create()
                        .registryKey(OAK_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        OAK_DECOR_DOOR_KEY
    );

    public static final Block METAL_DECOR_DOOR = registerBlock(
        new DoorDecor(
                BlockSetType.OAK, 
                AbstractBlock.Settings.create()
                        .registryKey(METAL_DECOR_DOOR_KEY)
                        .strength(2.0f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        METAL_DECOR_DOOR_KEY
    );

    public static final Block BIRCH_DECOR_DOOR = registerBlock(
        new DoorDecor(
                BlockSetType.BIRCH, 
                AbstractBlock.Settings.create()
                        .registryKey(BIRCH_DECOR_DOOR_KEY)
                        .strength(1.5f)
                        .nonOpaque() // Двери имеют прозрачные части
        ),
        BIRCH_DECOR_DOOR_KEY
    );


    // Блок стеклянной панели
    // Регистрация блока панели
    public static final Block OAK_GLASS_PANE = registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(OAK_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        OAK_GLASS_PANE_KEY
    );

    public static final Block OAK_IRON_GLASS_PANE = registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(OAK_IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        OAK_IRON_GLASS_PANE_KEY
    );

    public static final Block IRON_GLASS_PANE = registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        IRON_GLASS_PANE_KEY
    );

    public static final Block BIRCH_IRON_GLASS_PANE = registerBlock(
        new HorizontalPaneBlock(AbstractBlock.Settings.create().registryKey(BIRCH_IRON_GLASS_PANE_KEY)
        .nonOpaque() // Делает блок прозрачным
        .strength(2.0f) // Прочность как у стекла
        .sounds(BlockSoundGroup.GLASS)),
        BIRCH_IRON_GLASS_PANE_KEY
    );

    //функциональные блоки
    public static final Block BATANIC_TABLE_BLOCK = registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(BATANIC_TABLE_KEY).strength(2.0f, 50.0f)), 
            BATANIC_TABLE_KEY
    );

    public static final Block HOUSEKEEPER_BLOCK = registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(HOUSEKEEPER_KEY).strength(4.0f, 50.0f)), 
            HOUSEKEEPER_KEY
    );

  // 4. Регистрация Предмета для блоков
    public static final Item BOX_ITEM = registerBlockItem(BOX_BLOCK, BOX_ITEM_KEY);
    public static final Item OAK_BOX_ITEM = registerBlockItem(OAK_BOX_BLOCK, OAK_BOX_ITEM_KEY);
    public static final Item BIRCH_BOX_ITEM = registerBlockItem(BIRCH_BOX_BLOCK, BIRCH_BOX_ITEM_KEY);
    //Двери
    public static final Item OAK_DECOR_DOOR_ITEM = registerBlockItem(OAK_DECOR_DOOR, OAK_DECOR_DOOR_ITEM_KEY);
    public static final Item METAL_DECOR_DOOR_ITEM = registerBlockItem(METAL_DECOR_DOOR, METAL_DECOR_DOOR_ITEM_KEY);
    public static final Item BIRCH_DECOR_DOOR_ITEM = registerBlockItem(BIRCH_DECOR_DOOR, BIRCH_DECOR_DOOR_ITEM_KEY);
    //окна
    public static final Item OAK_GLASS_PANE_ITEM = registerBlockItem(OAK_GLASS_PANE, OAK_GLASS_PANE_ITEM_KEY);
    public static final Item OAK_IRON_GLASS_PANE_ITEM = registerBlockItem(OAK_IRON_GLASS_PANE, OAK_IRON_GLASS_PANE_ITEM_KEY);
    public static final Item IRON_GLASS_PANE_ITEM = registerBlockItem(IRON_GLASS_PANE, IRON_GLASS_PANE_ITEM_KEY);
    public static final Item BIRCH_IRON_GLASS_PANE_ITEM = registerBlockItem(BIRCH_IRON_GLASS_PANE, BIRCH_IRON_GLASS_PANE_ITEM_KEY);

    // функциональные блоки
    public static final Item BATANIC_TABLE_ITEM = registerBlockItem(BATANIC_TABLE_BLOCK, BATANIC_TABLE_ITEM_KEY);
    public static final Item HOUSEKEEPER_ITEM = registerBlockItem(HOUSEKEEPER_BLOCK, HOUSEKEEPER_ITEM_KEY);


    private static Block registerBlock(Block block, RegistryKey<Block> key) {
        return Registry.register(Registries.BLOCK, key, block);
    }

    private static Item registerBlockItem(Block block, RegistryKey<Item> key) {
        return Registry.register(Registries.ITEM, key, new BlockItem(block, new Item.Settings().registryKey(key)));
    }

    

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);

     /*   ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.add(BOX_ITEM); // Добавляем именно предмет
            content.add(OAK_BOX_ITEM);
            content.add(OAK_DECOR_DOOR_ITEM);
            content.add(METAL_DECOR_DOOR_ITEM);
            content.add(OAK_GLASS_PANE_ITEM);
        });*/
    }

public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "box_be"),//AOK_BOX_BLOCK
    // Это актуальный способ для последних версий Fabric
    FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, BOX_BLOCK, OAK_BOX_BLOCK, BIRCH_BOX_BLOCK, HOUSEKEEPER_BLOCK, BATANIC_TABLE_BLOCK).build()
);

public static final BlockEntityType<DoorDecorEntity> DOOR_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "door_be"),//AOK_BOX_BLOCK
    // Это актуальный способ для последних версий Fabric
    FabricBlockEntityTypeBuilder.create(DoorDecorEntity::new, OAK_DECOR_DOOR, METAL_DECOR_DOOR, BIRCH_DECOR_DOOR).build()
);

}
