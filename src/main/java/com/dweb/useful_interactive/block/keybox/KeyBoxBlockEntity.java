package com.dweb.useful_interactive.block.keybox;

import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KeyBoxBlockEntity extends BlockEntity implements MenuProvider {//import net.minecraft.screen.NamedScreenHandlerFactory;
    public KeyBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntites.KEY_BOX_ENTITY_TYPE, pos, state);
    }

    @Override
    public Component getDisplayName() { return Component.literal("Мой Блок"); }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {//ScreenHandler
        return new KeyBoxScreenHandler(syncId, inv);
    }
}