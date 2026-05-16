package com.dweb.useful_interactive;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import com.dweb.useful_interactive.block.clock.WallClockBlock;
import com.dweb.useful_interactive.block.clock.WallClockBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;

import net.minecraft.core.Direction;


class WallClockRenderState extends BlockEntityRenderState {
    public float rotation;
    public Direction facing;
}

public class WallClockRenderer /*implements BlockEntityRenderer<WallClockBlockEntity, WallClockRenderState> */{

  /*   public WallClockRenderer(BlockEntityRendererProvider.Context context) {
        // Здесь инициализируем модели, если они есть
    }

    @Override
    public WallClockRenderState createRenderState() {
        return new WallClockRenderState();
    }

    @Override
    public void extractRenderState(WallClockBlockEntity entity, WallClockRenderState state, float partialTicks) {
        // Шаг 1: Копируем данные из сущности в state
        state.facing = entity.getBlockState().getValue(WallClockBlock.FACING);
        
        float time = (entity.getLevel().getDayTime() + partialTicks) % 24000;
        state.rotation = (time / 24000.0f) * 360.0f;
    }

    @Override
    public void render(WallClockRenderState state, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // Шаг 2: Только отрисовка на основе данных из state
        poseStack.pushPose();
        
        poseStack.translate(0.5f, 0.5f, 0.5f);
        // Используем Axis.Z_POSITIVE (новое имя в 26.1)
        poseStack.mulPose(Axis.Z_POSITIVE.rotationDegrees(state.rotation));
        
        // Отрисовка вашей модели стрелки...
        
        poseStack.popPose();
    }*/
}
