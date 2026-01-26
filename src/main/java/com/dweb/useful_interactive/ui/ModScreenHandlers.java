package com.dweb.useful_interactive.ui;

import net.minecraft.registry.Registry;

import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<KeyBoxScreenHandler> KEY_CABINET = Registry.register(
            Registries.SCREEN_HANDLER, 
            Identifier.of("useful_interactive", "key_cabinet"), 
            new ScreenHandlerType<>(KeyBoxScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    public static void registerScreenHandlers() {
        // Метод для инициализации класса в ModInitializer
    }
}
