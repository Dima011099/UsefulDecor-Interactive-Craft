package com.dweb.useful_interactive;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dweb.useful_interactive.block.door.DoorDecor;
import com.dweb.useful_interactive.block.door.DoorDecorEntity;
import com.dweb.useful_interactive.recipe.ModRecipeTypes;
import com.dweb.useful_interactive.registry.ModComponentType;
import com.dweb.useful_interactive.registry.ModItemGroups;
import com.dweb.useful_interactive.registry.blockentites.ModBlockEntites;
import com.dweb.useful_interactive.registry.blocks.ModBlocks;
import com.dweb.useful_interactive.registry.items.KeyItem;
import com.dweb.useful_interactive.registry.items.ModItems;
import com.dweb.useful_interactive.ui.ModScreenHandlers;

/**
 * Main entrypoint for the UsefulDecor Interactive craft mod.
 * Handles the synchronized registration of all core systems, blocks, and items.
 */
public class UsefulDecorMod implements ModInitializer {
	public static final String MOD_ID = "useful_interactive";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final int PROTECTION_RADIUS_X = 15;
    private static final int PROTECTION_RADIUS_Z = 15;
    private static final int PROTECTION_RADIUS_Y_UP = 10;
    private static final int PROTECTION_RADIUS_Y_DOWN = 3;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Useful Decor Mod...");

		registerCoreSystems();
		registerGameContent();
		registerUserInterfaces();

		buildBlock();
		attackBlock();

		LOGGER.info("Useful Decor Mod initialized successfully!");
	}

	private void registerCoreSystems() {
		ModRecipeTypes.register();
		ModComponentType.register();
	}

	private void registerGameContent() {
		KeyItem.register();
		ModItems.register();
		ModBlocks.register();
		ModBlockEntites.register();
	}

	private void registerUserInterfaces() {
		ModScreenHandlers.registerScreenHandlers();
		ModItemGroups.registerItemGroups();
	}


	private boolean isInHouseArea(BlockPos targetPos, BlockPos doorPos) {
        boolean xMatches = targetPos.getX() >= doorPos.getX() - PROTECTION_RADIUS_X && targetPos.getX() <= doorPos.getX() + PROTECTION_RADIUS_X;
        boolean zMatches = targetPos.getZ() >= doorPos.getZ() - PROTECTION_RADIUS_Z && targetPos.getZ() <= doorPos.getZ() + PROTECTION_RADIUS_Z;
        
        boolean yMatches = targetPos.getY() >= doorPos.getY() - PROTECTION_RADIUS_Y_DOWN && targetPos.getY() <= doorPos.getY() + PROTECTION_RADIUS_Y_UP;

        return xMatches && yMatches && zMatches;
    }

	private boolean isAreaProtectedByClosedDoor(Level level, BlockPos targetPos) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

        for (int x = -PROTECTION_RADIUS_X; x <= PROTECTION_RADIUS_X; x++) {
            for (int z = -PROTECTION_RADIUS_Z; z <= PROTECTION_RADIUS_Z; z++) {
                for (int y = -PROTECTION_RADIUS_Y_UP; y <= PROTECTION_RADIUS_Y_DOWN; y++) {
                    
                    checkPos.set(targetPos.getX() + x, targetPos.getY() + y, targetPos.getZ() + z);
                    
                    if (isDoorClosed(level, checkPos)) {
                        if (isInHouseArea(targetPos, checkPos)) {
                            return true; 
                        }
                    }
                }
            }
        }
        return false;
    }

	private void attackBlock(){
		 PlayerBlockBreakEvents.BEFORE.register((level, player, breakPos, state, blockEntity) -> {
            if (player.isCreative()) return true;

			if(state.getBlock() instanceof DoorDecor)
				return true;

            if (isAreaProtectedByClosedDoor(level, breakPos)) {
                player.sendOverlayMessage(
                    Component.translatable("message.useful_interactive.protected_by_door")
                    .withStyle(ChatFormatting.RED));
                return false; 
            }
            return true;
        });
	}

	private void buildBlock(){
UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
    if (player.isCreative()) return InteractionResult.PASS;

    BlockPos clickedPos = hitResult.getBlockPos();

    if (isAreaProtectedByClosedDoor(level, clickedPos)) {
        
        BlockState clickedState = level.getBlockState(clickedPos);
        if (clickedState.getBlock() instanceof DoorBlock) {
            return InteractionResult.PASS; 
        }

        net.minecraft.world.item.ItemStack itemInHand = player.getItemInHand(hand);
        
        boolean isPlacingBlock = itemInHand.getItem() instanceof net.minecraft.world.item.BlockItem;
        boolean isUsingBucket = itemInHand.getItem() instanceof net.minecraft.world.item.BucketItem || 
                                 itemInHand.getItem() instanceof net.minecraft.world.item.SolidBucketItem;

        if (isPlacingBlock || isUsingBucket) {
            if (level.isClientSide()) {
                player.sendOverlayMessage(
                    Component.translatable("message.useful_interactive.cannot_build_here")
                    .withStyle(ChatFormatting.RED));
            }
            return InteractionResult.FAIL; 
        }
    }
    return InteractionResult.PASS; 
});
	}


	@SuppressWarnings("null")
    private boolean isDoorClosed(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof DoorDecorEntity door) {
            return door.isLocked();
        }
        return false;
    }
}