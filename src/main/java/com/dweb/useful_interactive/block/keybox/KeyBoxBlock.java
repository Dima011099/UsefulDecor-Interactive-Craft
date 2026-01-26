package com.dweb.useful_interactive.block.keybox;



import com.dweb.useful_interactive.block.chest.BoxBlock;
import com.dweb.useful_interactive.ui.keybox.KeyBoxScreenHandler;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;


public class KeyBoxBlock extends BlockWithEntity{
   public static final MapCodec<BoxBlock> CODEC = createCodec(BoxBlock::new);
    public static final EnumProperty<Direction> FACING = FacingBlock.FACING;

    public KeyBoxBlock(Settings settings) {
        super(settings);
    }

        @Override
    public MapCodec<? extends BoxBlock> getCodec() {
        return CODEC;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeyBoxBlockEntity(pos, state);
    }

     @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
    if (!world.isClient()) {
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, p) -> {
              Inventory temporaryInventory = new SimpleInventory(4); 
            return new KeyBoxScreenHandler(syncId, playerInventory, temporaryInventory,ScreenHandlerContext.create(world, pos));
        }, Text.translatable("container.key_cabinet"))); 
    }
    return ActionResult.SUCCESS;
    }

        @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

     @Override
 protected void appendProperties(Builder<Block, BlockState> builder) {
    builder.add(FACING);
    super.appendProperties(builder);
 }

 @Override
 public @org.jspecify.annotations.Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
    return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
 }
}
