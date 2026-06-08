package com.dweb.useful_interactive;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

import com.dweb.useful_interactive.block.clock.WallClockBlock;
import com.dweb.useful_interactive.block.clock.WallClockBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.phys.Vec3;

/**
 * Verified deferred block entity renderer for the Wall Clock.
 * Fully compatible with the 26.1 rendering engine.
 */
@SuppressWarnings("null")
public class WallClockRenderer implements BlockEntityRenderer<WallClockBlockEntity, WallClockRenderer.WallClockRenderState> {

    // Built-in vanilla white texture used for solid color shading directly through the vertex buffer
    private static final Identifier PLAIN_WHITE = Identifier.fromNamespaceAndPath("minecraft", "textures/misc/white.png");

    public WallClockRenderer(BlockEntityRendererProvider.Context context) {
        // Constructor is empty as geometry is built dynamically without heavy Java model parts
    }

    /*
    * Holds the interpolated render state for the Wall Clock.
    * Synchronizes and transfers world data safely from the client thread to the GPU submission queue.
    */
    public static class WallClockRenderState extends BlockEntityRenderState {
        public float hourRotation = 0.0f; 
        public float minuteRotation = 0.0f;
        public Direction facing = Direction.NORTH;
        
        // Custom translation fields tracking the block position relative to the player's camera
        public double xOffset = 0.0;
        public double yOffset = 0.0;
        public double zOffset = 0.0;
    }

    @Override
    public WallClockRenderState createRenderState() {
        return new WallClockRenderState();
    }

    /**
    * Extraction Phase: Executed on the main game thread.
    * Gathers block positioning, direction, and synchronized diurnal clock times to populate the render state.
    */
    @Override
    public void extractRenderState(WallClockBlockEntity entity, WallClockRenderer.WallClockRenderState state, float partialTicks, Vec3 cameraPos, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, cameraPos, crumblingOverlay);

        if (entity.getLevel() == null) {
            return;
        }

        state.facing = entity.getBlockState().getValue(WallClockBlock.FACING);
        
        state.xOffset = entity.getBlockPos().getX() - cameraPos.x();
        state.yOffset = entity.getBlockPos().getY() - cameraPos.y();
        state.zOffset = entity.getBlockPos().getZ() - cameraPos.z();

        long worldTime = entity.getLevel().getDefaultClockTime();

        float time = (float)(worldTime % 24000L) + partialTicks;

        state.minuteRotation = (time % 1000.0f) / 1000.0f * 360.0f;

        float correctedHourTime = (time + 6000.0f) % 12000f;

        state.hourRotation = (correctedHourTime / 12000.0f) * 360.0f;
    }

    /**
    * Geometry Submission Phase: Executed within the deferred graphics pass.
    * Batches custom vertices directly into the GPU pipeline utilizing SubmitNodeCollector.
    */
    @Override
    public void submit(WallClockRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraRenderState) {
        
        collector.submitCustomGeometry(poseStack, RenderTypes.entitySolid(PLAIN_WHITE), (currentPose, vertexConsumer) -> {
            
            // -----------------------------------------------------------------
            // 1. HOUR HAND RENDERING
            // -----------------------------------------------------------------
            poseStack.pushPose();
        
            poseStack.translate(state.xOffset + 0.5f, state.yOffset + 0.5f, state.zOffset + 0.5f); 

            applyBlockFacing(poseStack, state.facing);

            poseStack.translate(0.0f, 0.0f, 0.42);
            poseStack.mulPose(Axis.ZP.rotationDegrees(state.hourRotation)); 

            buildSafeQuad(poseStack, vertexConsumer, -0.015f, 0.015f, 0.0f, 0.16f, 0.0f, 140, 240, 0, 255);

            poseStack.popPose();

            // -----------------------------------------------------------------
            // 2. MINUTE HAND RENDERING
            // -----------------------------------------------------------------
            poseStack.pushPose();
            
            poseStack.translate(state.xOffset + 0.5f, state.yOffset + 0.5f, state.zOffset + 0.5f);
            
            applyBlockFacing(poseStack, state.facing);
            
            poseStack.translate(0.0f, 0.0f, 0.43f);
            poseStack.mulPose(Axis.ZP.rotationDegrees(state.minuteRotation));
            
            buildSafeQuad(poseStack, vertexConsumer, -0.01f, 0.01f, 0.0f, 0.21f, 0.0f, 160, 255, 0, 255);

            poseStack.popPose();
        });
    }

    private void applyBlockFacing(PoseStack poseStack, Direction facing) {
        switch (facing) {
            case SOUTH:
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
                break;
            case WEST:
                poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
                break;
            case EAST:
                poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f));
                break;
            default:
                break;
        }
    }

    
    /**
    * Transforms the coordinate matrix according to horizontal block orientation,
    * locking the dial face perfectly against the correct wall profile.
    */
    private void buildSafeQuad(PoseStack poseStack, VertexConsumer consumer, float minX, float maxX, float minY, float maxY, float z, int r, int g, int b, int a) {
        org.joml.Matrix4f matrix = poseStack.last().pose();
        
        consumer.addVertex(matrix, minX, minY, z).setColor(r, g, b, a).setUv(0f, 0f).setOverlay(OverlayTexture.pack(0, 10)).setLight(LightCoordsUtil.FULL_SKY).setNormal(0f, 0f, 1f);
        consumer.addVertex(matrix, minX, maxY, z).setColor(r, g, b, a).setUv(0f, 1f).setOverlay(OverlayTexture.pack(0, 10)).setLight(LightCoordsUtil.FULL_SKY).setNormal(0f, 0f, 1f);
        consumer.addVertex(matrix, maxX, maxY, z).setColor(r, g, b, a).setUv(1f, 1f).setOverlay(OverlayTexture.pack(0, 10)).setLight(LightCoordsUtil.FULL_SKY).setNormal(0f, 0f, 1f);
        consumer.addVertex(matrix, maxX, minY, z).setColor(r, g, b, a).setUv(1f, 0f).setOverlay(OverlayTexture.pack(0, 10)).setLight(LightCoordsUtil.FULL_SKY).setNormal(0f, 0f, 1f);
    }
}