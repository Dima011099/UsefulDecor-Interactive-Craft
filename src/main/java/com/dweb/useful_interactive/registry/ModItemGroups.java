package com.dweb.useful_interactive.registry;

import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.items.ModItems;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    // Создаем ключ для группы
    public static final ItemGroup USEFUL_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of("useful_interactive", "useful_group"), 
            FabricItemGroup.builder()
                    // Иконка вкладки (например, ваша коробка)
                    .icon(() -> new ItemStack(ModItems.BOX_ITEM)) 
                    // Название вкладки (используйте перевод в lang файле)
                    .displayName(Text.translatable("itemGroup.useful_interactive.useful_group"))
                    // Добавляем предметы в группу
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.BOX_ITEM);
                        entries.add(ModItems.OAK_BOX_ITEM);
                        entries.add(ModItems.OAK_GLASS_PANE_ITEM);
                        entries.add(ModItems.METAL_DECOR_DOOR_ITEM);
                        entries.add(ModItems.OAK_DECOR_DOOR_ITEM);
                        entries.add(KeyItem.MY_ITEM);
                        entries.add(ModItems.BIRCH_BOX_ITEM);
                        entries.add(ModItems.OAK_IRON_GLASS_PANE_ITEM);
                        entries.add(ModItems.IRON_GLASS_PANE_ITEM);
                        entries.add(ModItems.BIRCH_IRON_GLASS_PANE_ITEM);
                        entries.add(ModItems.BIRCH_DECOR_DOOR_ITEM);
                        entries.add(ModItems.HOUSEKEEPER_ITEM);
                        /*entries.add(ModItems.BATANIC_TABLE_ITEM);*/
                    })
                    .build());

    public static void registerItemGroups() {
        // Метод для инициализации в главном классе
    }
}
