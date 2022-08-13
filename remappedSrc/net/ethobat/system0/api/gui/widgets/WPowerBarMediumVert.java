package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.energy.IEnergyContainer;
import net.minecraft.util.Identifier;

public class WPowerBarMediumVert extends PowerBarVert {

    public WPowerBarMediumVert(String name, int screenX, int screenY) {
        super(name, screenX, screenY);
    }

    public Identifier getTextureEmpty() {
        return new Identifier("system0", "textures/gui/widget/power_bar_m_vert.png");
    }

    public Identifier getTextureFull() {
//        if (this.energyType == null) {
//            return new Identifier("system0", "textures/gui/widget/power_bar_m_vert.png");
//        }
        return new Identifier("system0", "textures/gui/widget/power_bar_m_vert_"+this.energyType.getName()+".png");
    }

    @Override
    public int getWidth() {
        return 18;
    }

    @Override
    public int getHeight() {
        return 34;
    }

}
