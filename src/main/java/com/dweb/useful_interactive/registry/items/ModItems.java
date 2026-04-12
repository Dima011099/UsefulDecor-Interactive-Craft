package com.dweb.useful_interactive.registry.items;


import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModItems {
     // Регистрируем ключи предметов блока
    public static final ResourceKey<Item> BOX_ITEM_KEY = ResourceKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "box")
    );

    public static final ResourceKey<Item> OAK_BOX_ITEM_KEY = ResourceKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_box")
    );

    public static final ResourceKey<Item> BIRCH_BOX_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_box")
    );

    // Для дверей предметы блока
    public static final ResourceKey<Item> OAK_DECOR_DOOR_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_decor_door")
    );

    public static final ResourceKey<Item> METAL_DECOR_DOOR_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "metal_decor_door")
    );

    public static final ResourceKey<Item> BIRCH_DECOR_DOOR_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decor_door")
    );

    public static final ResourceKey<Item> GLASS_DECOR_DOOR_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "glass_decor_door")
    );

    //Для стеклянной понели
    public static final ResourceKey<Item> OAK_GLASS_PANE_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_glass_pane")
    );

    public static final ResourceKey<Item> OAK_IRON_GLASS_PANE_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_iron_glass_pane")
    );

    public static final ResourceKey<Item> IRON_GLASS_PANE_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "iron_glass_pane")
    );

    public static final ResourceKey<Item> BIRCH_IRON_GLASS_PANE_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_iron_glass_pane")
    );

    public static final ResourceKey<Item> BIRCH_BOX_CABINET_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_box_cabinet")
    );

    public static final ResourceKey<Item> BIRCH_GARDEN_TRELLIS_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_garden_trellis")
    );

    public static final ResourceKey<Item> BIRCH_DECORATIVE_STRIP_1_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decorative_strip_1")
    );

    public static final ResourceKey<Item> BIRCH_DECORATIVE_STRIP_2_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_decorative_strip_2")
    );


    //Для функциональных блоков
    
    public static final ResourceKey<Item> BATANIC_TABLE_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "batanic_table")
    );

    public static final ResourceKey<Item> KEYBOX_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "keybox")
    );

    //часы oak_clock
    public static final ResourceKey<Item> OAK_CLOCK_ITEM_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_clock")
    );


    // Регистрация Предмета для блоков
    public static final Item BOX_ITEM = registerBlockItem(ModStorageBlocks.BOX_BLOCK, BOX_ITEM_KEY);
    public static final Item OAK_BOX_ITEM = registerBlockItem(ModStorageBlocks.OAK_BOX_BLOCK, OAK_BOX_ITEM_KEY);
    public static final Item BIRCH_BOX_ITEM = registerBlockItem(ModStorageBlocks.BIRCH_BOX_BLOCK, BIRCH_BOX_ITEM_KEY);

    public static final Item BIRCH_BOX_CABINET_ITEM = registerBlockItem(ModStorageBlocks.BIRCH_BOX_CABINET_BLOCK, BIRCH_BOX_CABINET_ITEM_KEY);
    //Двери
    public static final Item OAK_DECOR_DOOR_ITEM = registerBlockItem(ModArchitecturalBlocks.OAK_DECOR_DOOR, OAK_DECOR_DOOR_ITEM_KEY);
    public static final Item METAL_DECOR_DOOR_ITEM = registerBlockItem(ModArchitecturalBlocks.METAL_DECOR_DOOR, METAL_DECOR_DOOR_ITEM_KEY);
    public static final Item BIRCH_DECOR_DOOR_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_DECOR_DOOR, BIRCH_DECOR_DOOR_ITEM_KEY);
    public static final Item GLASS_DECOR_DOOR_ITEM = registerBlockItem(ModArchitecturalBlocks.GLASS_DECOR_DOOR, GLASS_DECOR_DOOR_ITEM_KEY);

    //окна
    public static final Item OAK_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.OAK_GLASS_PANE, OAK_GLASS_PANE_ITEM_KEY);
    public static final Item OAK_IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.OAK_IRON_GLASS_PANE, OAK_IRON_GLASS_PANE_ITEM_KEY);
    public static final Item IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.IRON_GLASS_PANE, IRON_GLASS_PANE_ITEM_KEY);
    public static final Item BIRCH_IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_IRON_GLASS_PANE, BIRCH_IRON_GLASS_PANE_ITEM_KEY);

    public static final Item BIRCH_GARDEN_TRELLIS_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_GARDEN_TRELLIS_BLOCK, BIRCH_GARDEN_TRELLIS_ITEM_KEY);
    public static final Item BIRCH_DECORATIVE_STRIP_1_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_1_BLOCK, BIRCH_DECORATIVE_STRIP_1_ITEM_KEY);
    public static final Item BIRCH_DECORATIVE_STRIP_2_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_2_BLOCK, BIRCH_DECORATIVE_STRIP_2_ITEM_KEY);


    //часы
    public static final Item OAK_CLOCK_ITEM = registerBlockItem(ModUtilityBlocks.OAK_CLOCK_BLOCK, OAK_CLOCK_ITEM_KEY);

    // функциональные блоки

    
  //  public static final Item BATANIC_TABLE_ITEM = registerBlockItem(ModUtilityBlocks.BATANIC_TABLE_BLOCK, BATANIC_TABLE_ITEM_KEY);
    public static final Item KEYBOX_ITEM = registerBlockItem(ModUtilityBlocks.KEYBOX_BLOCK, KEYBOX_ITEM_KEY);
    public static final Item BATANIC_TABLE_ITEM = registerBlockItem(ModUtilityBlocks.BATANIC_TABLE_BLOCK, BATANIC_TABLE_ITEM_KEY);

@SuppressWarnings("null")
    private static Item registerBlockItem(Block block, ResourceKey<Item> key) {
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().setId(key)));
    }

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);
    }
}
