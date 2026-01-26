package com.dweb.useful_interactive.registry.items;


import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
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

    public static final RegistryKey<Item> BIRCH_BOX_CABINET_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_box_cabinet")
    );


    //Для функциональных блоков
    /*
    public static final RegistryKey<Item> BATANIC_TABLE_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "batanic_table")
    );
*/
    public static final RegistryKey<Item> KEYBOX_ITEM_KEY = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(UsefulDecorMod.MOD_ID, "keybox")
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
    //окна
    public static final Item OAK_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.OAK_GLASS_PANE, OAK_GLASS_PANE_ITEM_KEY);
    public static final Item OAK_IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.OAK_IRON_GLASS_PANE, OAK_IRON_GLASS_PANE_ITEM_KEY);
    public static final Item IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.IRON_GLASS_PANE, IRON_GLASS_PANE_ITEM_KEY);
    public static final Item BIRCH_IRON_GLASS_PANE_ITEM = registerBlockItem(ModArchitecturalBlocks.BIRCH_IRON_GLASS_PANE, BIRCH_IRON_GLASS_PANE_ITEM_KEY);

   
    // функциональные блоки

    
  //  public static final Item BATANIC_TABLE_ITEM = registerBlockItem(ModUtilityBlocks.BATANIC_TABLE_BLOCK, BATANIC_TABLE_ITEM_KEY);
    public static final Item KEYBOX_ITEM = registerBlockItem(ModUtilityBlocks.KEYBOX_BLOCK, KEYBOX_ITEM_KEY);


    private static Item registerBlockItem(Block block, RegistryKey<Item> key) {
        return Registry.register(Registries.ITEM, key, new BlockItem(block, new Item.Settings().registryKey(key)));
    }

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блоков для " + UsefulDecorMod.MOD_ID);
    }
}
