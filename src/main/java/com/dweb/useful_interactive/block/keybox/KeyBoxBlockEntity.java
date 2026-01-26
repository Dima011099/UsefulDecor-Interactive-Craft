package com.dweb.useful_interactive.block.keybox;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class KeyBoxBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public KeyBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.KEY_BOX_ENTITY_TYPE, pos, state);
    }

    @Override
    public Text getDisplayName() { return Text.literal("Мой Блок"); }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new KeyBoxScreenHandler(syncId, inv);
    }
}