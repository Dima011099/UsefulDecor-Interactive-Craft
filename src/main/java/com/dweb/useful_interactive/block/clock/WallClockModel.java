package com.dweb.useful_interactive.block.clock;


@SuppressWarnings("null")
public class WallClockModel {
   /*  private final ModelPart hourHand;
    private final ModelPart minuteHand;

    // Обычный, чистый конструктор Java без super() костылей
    public WallClockModel(ModelPart root) {
        this.hourHand = root.getChild("hour_hand");
        this.minuteHand = root.getChild("minute_hand");
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition rootDefinition = meshDefinition.getRoot();

        // Описываем размеры стрелок часов
        rootDefinition.addOrReplaceChild("hour_hand", 
            CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, -0.5f, -0.1f, 1.0f, 4.0f, 0.2f), 
            PartPose.offset(0.0F, 0.0F, 0.0F));

        rootDefinition.addOrReplaceChild("minute_hand", 
            CubeListBuilder.create().texOffs(0, 4).addBox(-0.5f, -0.5f, -0.1f, 1.0f, 6.0f, 0.2f), 
            PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 16, 16);
    }


    public void renderHands(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color, WallClockRenderer.WallClockRenderState state) {
        // Применяем вращение стрелок по оси Z напрямую из стейта
        this.hourHand.zRot = state.hourRotation;
        this.minuteHand.zRot = state.minuteRotation;

        // Рендерим каждую кость в буфер игры
        this.hourHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        this.minuteHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }*/
}