package com.dweb.useful_interactive.domain.lock;

import java.util.UUID;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.items.KeyItem;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

 @SuppressWarnings("null")
public class LockableManager {
   
    public static boolean handleKeyUse(Player player, ItemStack stack, ILockableManager lockable) {
        if (!stack.is(KeyItem.MY_ITEM)) return false;
        ensureKeyHasUuid(stack);

        var key = stack.get(ModComponentType.KEY_F_CLOSE);
        if (key == null) {
            player.sendOverlayMessage(Component.translatable("message.useful_interactive.key_damaged"));
            return true;
        }
        if (!lockable.hasKey())
            lockable.bindKey(key);
        
        if (!lockable.tryUnlock(key)){
            player.sendOverlayMessage(Component.translatable("message.useful_interactive.key_mismatch"));     
            return true;   
        }
        String lockState = lockable.isLocked() ? "message.useful_interactive.locked" : "message.useful_interactive.unlocked";
        //sendMessage -> sendSystemMessage - Text.translatable -> Component.translatable
        player.sendOverlayMessage(Component.translatable(lockState));
        return true;
    }

    public static String ensureKeyHasUuid(ItemStack stack) {
        String currentUuid = stack.get(ModComponentType.KEY_F_CLOSE);
        
        if (currentUuid == null || currentUuid.isEmpty()) {
            String newUuid = UUID.randomUUID().toString().replace("-", "");
            stack.set(ModComponentType.KEY_F_CLOSE, newUuid);
            return newUuid;
        }
        
        return currentUuid;
    }
}
