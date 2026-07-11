package com.dweb.useful_interactive.block.trash;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("null")
public class Trash extends Block {
   // public static final MapCodec<Trash> CODEC = simpleCodec(Trash::new);

    private static final VoxelShape SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

    public Trash(Properties properties) {
        super(properties);
    }

   /* @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }*/

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if(!level.isClientSide()){
            ItemStack stackInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(!stackInHand.isEmpty()){
                player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                level.playSound(null, pos, SoundEvents.SULFUR_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}