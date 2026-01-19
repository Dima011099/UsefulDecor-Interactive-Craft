package com.dweb.useful_interactive.registry.ui;

import net.minecraft.registry.Registry;

import com.dweb.useful_interactive.common.KeyCabinetScreenHandler;

import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<KeyCabinetScreenHandler> KEY_CABINET = Registry.register(
            Registries.SCREEN_HANDLER, 
            Identifier.of("useful_interactive", "key_cabinet"), 
            new ScreenHandlerType<>(KeyCabinetScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    public static void registerScreenHandlers() {
        // Метод для инициализации класса в ModInitializer
    }
}
