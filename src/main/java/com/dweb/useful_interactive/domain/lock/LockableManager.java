package com.dweb.useful_interactive.domain.lock;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.items.KeyItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class LockableManager {
    
    public static boolean handleKeyUse(PlayerEntity player, ItemStack stack, ILockableManager lockable) {
        if (!stack.isOf(KeyItem.MY_ITEM)) return false;

        var key = stack.get(ModComponentType.KEY_F_CLOSE);
        if (key == null) {
            player.sendMessage(Text.translatable("message.useful_interactive.key_damaged"),  true);
            return true;
        }
        if (!lockable.hasKey())
            lockable.bindKey(key);
        
        if (!lockable.tryUnlock(key)){
            player.sendMessage(Text.translatable("message.useful_interactive.key_mismatch"), true);     
            return true;   
        }
        String lockState = lockable.isLocked() ? "message.useful_interactive.locked" : "message.useful_interactive.unlocked";
        player.sendMessage(Text.translatable(lockState), true);
        return true;
    }
}
