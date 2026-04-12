package com.dweb.useful_interactive;

import com.dweb.useful_interactive.block.clock.WallClockBlock;
import com.dweb.useful_interactive.block.clock.WallClockBlockEntity;
import com.ibm.icu.text.RelativeDateTimeFormatter.Direction;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

public class WallClockRenderer implements BlockEntityRenderer<WallClockBlockEntity> {
    @Override
    public void render(WallClockBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();
        
        // 1. Центрируем и поворачиваем под стену (FACING)
        Direction dir = entity.getBlockState().getValue(WallClockBlock.FACING);
        // ... (логика перемещения poseStack к центру блока)

        // 2. Рассчитываем угол времени
        float time = entity.getLevel().getDayTime() % 24000;
        float rotation = (time / 24000.0f) * 360.0f;

        // 3. Вращаем стрелку
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        
        // 4. Отрисовываем модель стрелки (через VertexConsumer)
        // ...
        
        poseStack.popPose();
    }
}