package com.dweb.useful_interactive.ui;


import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;



public class KeyCabinetScreen extends HandledScreen<KeyBoxScreenHandler>{
    private static final Identifier TEXTURE = Identifier.of(UsefulDecorMod.MOD_ID, "textures/gui/keybox.png");


    public KeyCabinetScreen(KeyBoxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(
            RenderPipelines.GUI_TEXTURED,
            TEXTURE,
            x, y, 
            0.0f, 0.0f, 
            this.backgroundWidth, 
            this.backgroundHeight, 
    256, 256
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}