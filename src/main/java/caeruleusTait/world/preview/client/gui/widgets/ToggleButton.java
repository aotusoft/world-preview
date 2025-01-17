package caeruleusTait.world.preview.client.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ToggleButton extends ImageButton {
    public boolean selected;
    protected final int xDiff;

    public ToggleButton(int x, int y, int width, int height, int xTexStart, int yTexStart, ResourceLocation resourceLocation, OnPress onPress) {
        this(x, y, width, height, xTexStart, yTexStart, width, height, resourceLocation, 256, 256, onPress);
    }

    public ToggleButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int xDiff, int yDiff, ResourceLocation resourceLocation, OnPress onPress) {
        this(x, y, width, height, xTexStart, yTexStart, xDiff, yDiff, resourceLocation, 256, 256, onPress);
    }

    public ToggleButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int xDiff, int yDiff, ResourceLocation resourceLocation, int texWidth, int texHeight, OnPress onPress) {
        this(x, y, width, height, xTexStart, yTexStart, xDiff, yDiff, resourceLocation, texWidth, texHeight, onPress, CommonComponents.EMPTY);
    }

    public ToggleButton(
            int x,
            int y,
            int width,
            int height,
            int xTexStart,
            int yTexStart,
            int xDiff,
            int yDiff,
            ResourceLocation resourceLocation,
            int texWidth,
            int texHeight,
            OnPress onPress,
            Component component
    ) {
        super(x, y, width, height, xTexStart, yTexStart, yDiff, resourceLocation, texWidth, texHeight, onPress, component);
        this.xDiff = xDiff;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int x = this.xTexStart;
        int y = this.yTexStart;
        if (!selected) {
            x += xDiff;
        }
        if (!this.isActive()) {
            y += yDiffTex * 2;
        } else if (this.isHoveredOrFocused()) {
            y += yDiffTex;
        }

        RenderSystem.disableDepthTest();
        guiGraphics.blit(resourceLocation, getX(), getY(), x, y, width, height, textureWidth, textureHeight);
    }

    @Override
    public void onPress() {
        selected = !selected;
        super.onPress();
    }
}
