package com.dweb.useful_interactive.ui;

import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;

/*import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;*/
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static final MenuType<KeyBoxScreenHandler> KEY_CABINET = Registry.register(
            BuiltInRegistries.MENU, 
            Identifier.fromNamespaceAndPath("useful_interactive", "key_cabinet"), //Identifier.of
            new MenuType<>(KeyBoxScreenHandler::new, FeatureFlags.VANILLA_SET)
    );

    public static void registerScreenHandlers() {}
}