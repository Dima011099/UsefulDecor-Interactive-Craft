package com.dweb.useful_interactive.registry.items;

import java.util.UUID;

import com.dweb.useful_interactive.common.ModComponentType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MKeyItem extends Item {

    MKeyItem(Settings settings)
    {
        super(settings);
    }
    @Override
    public void onCraft(ItemStack stack, World world) {
        if(!world.isClient()){
            stack.set(ModComponentType.KEY_F_CLOSE, UUID.randomUUID().toString().replace("-", ""));
            super.onCraft(stack, world);
        }
    }



}