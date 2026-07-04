package com.dweb.useful_interactive.registry;

import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;
import com.dweb.useful_interactive.registry.items.KeyItem;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;



public class ModItemGroups {
    // Создаем ключ для группы ItemGroup
    @SuppressWarnings("null")
    public static final CreativeModeTab USEFUL_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath("useful_interactive", "useful_group"), 
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)// FabricItemGroup.builder()
                    // Иконка вкладки (например, ваша коробка)
                    .icon(() -> new ItemStack(ModStorageBlocks.BOX_BLOCK)) 
                    // Название вкладки (используйте перевод в lang файле)//displayName
                    .title(Component.translatable("itemGroup.useful_interactive.useful_group"))
                    // Добавляем предметы в группу
                    .displayItems((displayContext, entries) -> {
                        entries.accept(KeyItem.MY_ITEM);

                        entries.accept(ModStorageBlocks.BOX_BLOCK);
                        entries.accept(ModStorageBlocks.OAK_BOX_BLOCK);
                        entries.accept(ModStorageBlocks.BIRCH_BOX_BLOCK);
                        entries.accept(ModStorageBlocks.BIRCH_BOX_CABINET_BLOCK);

        
                        entries.accept(ModArchitecturalBlocks.METAL_DECOR_DOOR);
                        entries.accept(ModArchitecturalBlocks.OAK_DECOR_DOOR);

                        entries.accept(ModArchitecturalBlocks.OAK_GLASS_PANE);
                        entries.accept(ModArchitecturalBlocks.OAK_IRON_GLASS_PANE);
                        entries.accept(ModArchitecturalBlocks.IRON_GLASS_PANE);
                        entries.accept(ModArchitecturalBlocks.BIRCH_IRON_GLASS_PANE);

                        entries.accept(ModArchitecturalBlocks.VAMPIRE_WINDOW);
                        entries.accept(ModArchitecturalBlocks.DARK_WINDOW);
                        entries.accept(ModArchitecturalBlocks.SPIDER_WINDOW_1);
                        entries.accept(ModArchitecturalBlocks.SPIDER_WINDOW_2);

                        entries.accept(ModArchitecturalBlocks.BIRCH_DECOR_DOOR);
                        entries.accept(ModArchitecturalBlocks.GLASS_DECOR_DOOR);
                        entries.accept(ModArchitecturalBlocks.BIRCH_GARDEN_TRELLIS_BLOCK);
                        entries.accept(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_1_BLOCK);
                        entries.accept(ModArchitecturalBlocks.BIRCH_DECORATIVE_STRIP_2_BLOCK);
                        entries.accept(ModArchitecturalBlocks.OAK_GLASS_DECOR_DOOR);

                        entries.accept(ModUtilityBlocks.KEYBOX_BLOCK);
                       // entries.accept(ModUtilityBlocks.BATANIC_TABLE_BLOCK);

                        entries.accept(ModUtilityBlocks.CRIMSON_WALL_CLOCK_BLOCK);
                        entries.accept(ModUtilityBlocks.OCEANIC_WALL_CLOCK_BLOCK);
                        entries.accept(ModUtilityBlocks.WOODEN_WALL_CLOCK_BLOCK);
                        entries.accept(ModUtilityBlocks.OBSIDIAN_WALL_CLOCK_BLOCK);
                        entries.accept(ModUtilityBlocks.TIMBER_RING_CLOCK_BLOCK);
                        entries.accept(ModUtilityBlocks.BIRCH_ECO_CLOCK_BLOCK);

                        entries.accept(ModUtilityBlocks.TRASH_BLOCK);
                    })
                    .build());

    public static void registerItemGroups() {
        // Метод для инициализации в главном классе
    }
}
