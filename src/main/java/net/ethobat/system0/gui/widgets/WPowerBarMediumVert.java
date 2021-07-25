package net.ethobat.system0.gui.widgets;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.gui.widgets.AbstractPowerBar;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WPowerBarMediumVert extends AbstractPowerBar {

    WPowerBarMediumVert(int screenX, int screenY, long amt, long maxAmt, EnergyType energyType) {
        super(screenX, screenY, amt, maxAmt, energyType);
    }

    @Override
    public void draw(MatrixStack matrices) {
        prepareTexture(this.getTextureEmpty());
        this.drawTexture(matrices, this.screenX, this.screenY, 0, 0, 18, 34);
        prepareTexture(this.getTextureFull());
        this.drawTexture(matrices, this.screenX, this.screenY, 0, 0, 18, this.getRoundedFillAmount(34));
    }

    public Identifier getTextureEmpty() {
        return new Identifier("system0", "textures/gui/widget/power_bar_m_vert.png");
    }

    public Identifier getTextureFull() {
        return new Identifier("system0", "textures/gui/widget/power_bar_m_vert"+this.energyType.NAME+".png");
    }

}
