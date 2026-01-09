package com.dweb.useful_interactive.common;

import net.minecraft.block.BlockState;


import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.block.ShapeContext;





public class BoxCabinetBlock extends BoxBlock { 

   // public static final EnumProperty<Direction> FACING = EnumProperty.of("facing", Direction.class);

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);

    // Используем Properties для Forge 1.20+ / 1.21+
    public BoxCabinetBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }


    private VoxelShape rotateShape(Direction direction) {
        switch (direction) {
            case SOUTH:
                // Используем createCuboidShape вместо box
                return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0); 
            case EAST:
                return Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0); 
            case WEST:
                return Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0); 
            case NORTH:
            default:
                return SHAPE; 
        }
    }




   @Override
   protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
       return this.rotateShape(state.get(FACING));
   }

   @Override
   protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rotateShape(state.get(FACING)); 
   }
  
 



}