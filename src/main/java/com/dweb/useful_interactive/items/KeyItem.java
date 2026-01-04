package com.dweb.useful_interactive.items;



import com.dweb.useful_interactive.UsefulDecorMod;


import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class KeyItem {
    
    // 1. Сначала создаем ключ (RegistryKey)
    public static final RegistryKey<Item> MY_ITEM_KEY = RegistryKey.of(
            RegistryKeys.ITEM, 
            Identifier.of(UsefulDecorMod.MOD_ID, "key_item")
    );

    // 2. Передаем ключ в Settings через .registryKey(MY_ITEM_KEY)
    public static final Item MY_ITEM = registerItem(
            new MKeyItem(new Item.Settings().registryKey(MY_ITEM_KEY)), MY_ITEM_KEY
    );

    // Исправленный метод регистрации
    private static Item registerItem(Item item, RegistryKey<Item> key) {
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация предметов для " + UsefulDecorMod.MOD_ID);
/* 
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
        content.add(MY_ITEM);
    });*/
    }
}
