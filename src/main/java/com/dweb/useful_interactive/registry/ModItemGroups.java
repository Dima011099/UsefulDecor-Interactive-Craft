package com.dweb.useful_interactive.registry;

import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.items.ModItems;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;



public class ModItemGroups {
    // Создаем ключ для группы ItemGroup
    public static final CreativeModeTab USEFUL_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath("useful_interactive", "useful_group"), 
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)// FabricItemGroup.builder()
                    // Иконка вкладки (например, ваша коробка)
                    .icon(() -> new ItemStack(ModItems.BOX_ITEM)) 
                    // Название вкладки (используйте перевод в lang файле)//displayName
                    .title(Component.translatable("itemGroup.useful_interactive.useful_group"))
                    // Добавляем предметы в группу
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.BOX_ITEM);
                        entries.accept(ModItems.OAK_BOX_ITEM);
                        entries.accept(ModItems.OAK_GLASS_PANE_ITEM);
                        entries.accept(ModItems.METAL_DECOR_DOOR_ITEM);
                        entries.accept(ModItems.OAK_DECOR_DOOR_ITEM);
                        entries.accept(KeyItem.MY_ITEM);
                        entries.accept(ModItems.BIRCH_BOX_ITEM);
                        entries.accept(ModItems.OAK_IRON_GLASS_PANE_ITEM);
                        entries.accept(ModItems.IRON_GLASS_PANE_ITEM);
                        entries.accept(ModItems.BIRCH_IRON_GLASS_PANE_ITEM);
                        entries.accept(ModItems.BIRCH_DECOR_DOOR_ITEM);
                        entries.accept(ModItems.KEYBOX_ITEM);
                        entries.accept(ModItems.BIRCH_BOX_CABINET_ITEM);
                       // entries.accept(ModItems.BATANIC_TABLE_ITEM);
                        entries.accept(ModItems.GLASS_DECOR_DOOR_ITEM);
                        entries.accept(ModItems.BIRCH_GARDEN_TRELLIS_ITEM);
                        entries.accept(ModItems.BIRCH_DECORATIVE_STRIP_1_ITEM);
                        entries.accept(ModItems.BIRCH_DECORATIVE_STRIP_2_ITEM);
                    })
                    .build());

    public static void registerItemGroups() {
        // Метод для инициализации в главном классе
    }
}
