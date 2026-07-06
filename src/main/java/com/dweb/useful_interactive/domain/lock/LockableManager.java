package com.dweb.useful_interactive.domain.lock;

import java.util.UUID;

import com.dweb.useful_interactive.core.lock.ILockableManager;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.items.KeyItem;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

 @SuppressWarnings("null")
public class LockableManager {
   
    public static boolean handleKeyUse(Player player,Level world, ItemStack stack,BlockPos pos, ILockableManager lockable) {
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
            world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 0.5F, 1.5F); 
            return true;   
        }
        String lockState = lockable.isLocked() ? "message.useful_interactive.locked" : "message.useful_interactive.unlocked";
        player.sendOverlayMessage(Component.translatable(lockState));
        world.playSound(null, pos, SoundEvents.VAULT_INSERT_ITEM_FAIL, SoundSource.BLOCKS, 1.0F, 1.0F); 
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
