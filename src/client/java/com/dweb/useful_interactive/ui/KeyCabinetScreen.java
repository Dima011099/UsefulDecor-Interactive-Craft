package com.dweb.useful_interactive.ui;

import net.minecraft.network.chat.Component;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class KeyCabinetScreen extends AbstractContainerScreen<KeyBoxScreenHandler>{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "textures/gui/keybox.png");


    public KeyCabinetScreen(KeyBoxScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }


    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
                int x = this.leftPos;
        int y = this.topPos;

        graphics.blit(
            RenderPipelines.GUI_TEXTURED,
            TEXTURE,
            x, y, 
            0.0f, 0.0f, 
            this.imageWidth, 
            this.imageHeight, 
    256, 256
        );
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        extractBackground(graphics, mouseX, mouseY, a);
        super.extractRenderState(graphics, mouseX, mouseY, a);
        extractTooltip(graphics, mouseX, mouseY);
    }
   
}