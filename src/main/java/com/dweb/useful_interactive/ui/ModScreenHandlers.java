package com.dweb.useful_interactive.ui;

import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;

import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    @SuppressWarnings("null")
    public static final MenuType<KeyBoxScreenHandler> KEY_CABINET = Registry.register(
            BuiltInRegistries.MENU, 
            Identifier.fromNamespaceAndPath("useful_interactive", "key_cabinet"), //Identifier.of
            new MenuType<>(KeyBoxScreenHandler::new, FeatureFlags.VANILLA_SET)
    );

    public static void registerScreenHandlers() {}
}