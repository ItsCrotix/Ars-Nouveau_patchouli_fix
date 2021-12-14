package com.hollingsworth.arsnouveau.client.renderer.tile;

import com.hollingsworth.arsnouveau.common.block.IntangibleAirBlock;
import com.hollingsworth.arsnouveau.common.block.tile.IntangibleAirTile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class IntangibleAirRenderer implements BlockEntityRenderer<IntangibleAirTile> {

    public IntangibleAirRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {

    }

//    static class DummyRender extends net.minecraft.client.renderer.RenderType{
//        public static final RenderType RenderBlock = create("MiningLaserRenderBlock",
//                DefaultVertexFormat.BLOCK, GL11.GL_QUADS, 256,
//                RenderType.CompositeState.builder()
//                        .setShadeModelState(SMOOTH_SHADE)
//                        .setLightmapState(LIGHTMAP)
//                        .setTextureState(BLOCK_SHEET_MIPPED)
//                        .setLayeringState(VIEW_OFFSET_Z_LAYERING)
//                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
//                        .setDepthTestState(LEQUAL_DEPTH_TEST)
//                        .setCullState(CULL)
//                        .setWriteMaskState(COLOR_WRITE)
//                        .createCompositeState(false));
//
//        public DummyRender(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
//            super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
//        }
//    }


    private void renderModelBrightnessColorQuads(PoseStack.Pose matrixEntry, VertexConsumer builder, float red, float green, float blue, float alpha, List<BakedQuad> listQuads, int combinedLightsIn, int combinedOverlayIn) {
        for(BakedQuad bakedquad : listQuads) {
            float f;
            float f1;
            float f2;

            if (bakedquad.isTinted()) {
                f = red;
                f1 = green;
                f2 = blue;
            } else {
                f = 1f;
                f1 = 1f;
                f2 = 1f;
            }
            builder.putBulkData(matrixEntry, bakedquad, f, f1, f2, alpha, combinedLightsIn, combinedOverlayIn);
        }
    }

    @Override
    public void render(IntangibleAirTile tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockState renderState = Block.stateById(tileEntityIn.stateID);
        if(renderState == null)
            return;
        double scale = ((double)tileEntityIn.duration)/(double)tileEntityIn.maxLength;

        BlockRenderDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        BakedModel ibakedmodel = blockrendererdispatcher.getBlockModel(renderState);
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        int color = blockColors.getColor(renderState, tileEntityIn.getLevel(), tileEntityIn.getBlockPos(), 0);
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        matrixStackIn.pushPose();


        for (Direction direction : Direction.values()) {
            if (!(tileEntityIn.getLevel().getBlockState(tileEntityIn.getBlockPos().relative(direction)).getBlock() instanceof IntangibleAirBlock)) {
            //    renderModelBrightnessColorQuads(matrixStackIn.last(), bufferIn.getBuffer(DummyRender.RenderBlock), f, f1, f2, (float)scale, ibakedmodel.getQuads(renderState, direction, new Random(Mth.getSeed(tileEntityIn.getBlockPos())), EmptyModelData.INSTANCE), combinedLightIn, combinedOverlayIn);
            }
        }

        matrixStackIn.popPose();
    }
}
