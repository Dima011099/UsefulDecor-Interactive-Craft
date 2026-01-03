package com.dweb.useful_interactive.util;

import com.dweb.useful_interactive.items.KeyItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class LockableManager {
    
    public static boolean handleKeyUse(PlayerEntity player, ItemStack stack, ILockableManager lockable) {
        if (!stack.isOf(KeyItem.MY_ITEM)) return false;

        var key = stack.get(ModComponentType.KEY_F_CLOSE);
        if (key == null) {
            player.sendMessage(Text.literal("Ключ повреждён"), true);
            return true;
        }
        if (!lockable.hasKey())
            lockable.bindKey(key);
        
        if (!lockable.tryUnlock(key)){
            player.sendMessage(Text.literal("Ключ не подходит"), true);     
            return true;   
        }
        player.sendMessage(Text.literal(lockable.isLocked() ? "Закрыто" : "Открыто"), true);
        return true;
    }
}
