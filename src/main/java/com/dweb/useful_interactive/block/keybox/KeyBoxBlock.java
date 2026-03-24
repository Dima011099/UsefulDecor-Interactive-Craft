package com.dweb.useful_interactive.block.keybox;

import com.dweb.useful_interactive.block.chest.BoxBlock;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;


public class KeyBoxBlock extends BaseEntityBlock/*BlockWithEntity*/{
    public static final MapCodec<BoxBlock> CODEC = simpleCodec(BoxBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public KeyBoxBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends BoxBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity/*createBlockEntity*/(BlockPos pos, BlockState state) {
        return new KeyBoxBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult/*ActionResult*/ useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
            player.openMenu(new SimpleMenuProvider((syncId, playerInventory, p) -> {
                SimpleContainer temporaryInventory = new SimpleContainer(4); 
                return new KeyBoxScreenHandler(syncId, playerInventory, temporaryInventory,ContainerLevelAccess.create(world, pos));
            }, Component.translatable("container.useful_interactive.keybox"))); 
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}