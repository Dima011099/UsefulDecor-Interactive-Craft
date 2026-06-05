package com.dweb.useful_interactive.ui;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

/**
 * Handles the registration of all screen handlers (menu types) for the mod.
 * Connects container server-side logic with the client-side user interface.
 */
public class ModScreenHandlers {

    public static final MenuType<KeyBoxScreenHandler> KEY_CABINET = registerKeyCabinet();

    @SuppressWarnings("null")
    private static  MenuType<KeyBoxScreenHandler> registerKeyCabinet() {
        MenuType<KeyBoxScreenHandler> menuType = new MenuType<>(
                KeyBoxScreenHandler::new, 
                FeatureFlags.VANILLA_SET
        );

        return Registry.register(
                BuiltInRegistries.MENU, 
                Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_cabinet"), 
                menuType
        );
    }

    public static void registerScreenHandlers() {
        UsefulDecorMod.LOGGER.info("Registering Screen Handlers for " + UsefulDecorMod.MOD_ID);
    }
}