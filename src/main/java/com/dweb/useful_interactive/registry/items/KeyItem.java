package com.dweb.useful_interactive.registry.items;



import com.dweb.useful_interactive.UsefulDecorMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class KeyItem {
    
    // 1. Сначала создаем ключ (RegistryKey)
    public static final ResourceKey<Item> MY_ITEM_KEY = ResourceKey.create(
            Registries.ITEM, 
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_item")
    );

    // 2. Передаем ключ в Settings через .registryKey(MY_ITEM_KEY)
    @SuppressWarnings("null")
    public static final Item MY_ITEM = registerItem(
            new MKeyItem(new Item.Properties().setId(MY_ITEM_KEY)), MY_ITEM_KEY
    );

    // Исправленный метод регистрации
    @SuppressWarnings("null")
    private static Item registerItem(Item item, ResourceKey<Item> key) {
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация предметов для " + UsefulDecorMod.MOD_ID);
/* 
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
        content.add(MY_ITEM);
    });*/
    }
}
