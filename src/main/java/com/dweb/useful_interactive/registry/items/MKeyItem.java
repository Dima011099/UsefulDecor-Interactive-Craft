package com.dweb.useful_interactive.registry.items;

import java.util.UUID;

import com.dweb.useful_interactive.registry.ModComponentType;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MKeyItem extends Item {

    MKeyItem(Properties settings)
    {
        super(settings);
    }
    @Override
    public void onCraftedBy(ItemStack stack, Player player) {
        if(!player.level().isClientSide()){
            stack.set(ModComponentType.KEY_F_CLOSE, UUID.randomUUID().toString().replace("-", ""));
            super.onCraftedBy(stack, player);
        }
    }



}