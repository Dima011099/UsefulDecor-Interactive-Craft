package com.dweb.useful_interactive.block.cabinet;

//import net.minecraft.block.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder.Settings;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;

import com.dweb.useful_interactive.block.chest.BoxBlock;

/*import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
//import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.block.ShapeContext;*/

public class BoxCabinetBlock extends BoxBlock { 
   // private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);
   private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);

    public BoxCabinetBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
   

    private VoxelShape rotateShape(Direction direction) {
        switch (direction) {
            case SOUTH:
                return Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0); 
            case EAST:
                return Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0); 
            case WEST:
                return Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0); 
            case NORTH:
            default:
                return SHAPE; 
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.rotateShape(state.getValue(FACING));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return rotateShape(state.getValue(FACING)); 
    }
}