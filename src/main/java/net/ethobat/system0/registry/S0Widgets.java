package net.ethobat.system0.registry;

import net.ethobat.system0.gui.widgets.WPowerBarMediumVert;
import net.ethobat.system0.gui.widgets.WSlotGrid;

public class S0Widgets {

    public void init() {
        S0Registrar.register(WSlotGrid.class, "slot_grid");
        S0Registrar.register(WPowerBarMediumVert.class, "power_bar_medium_vert");
    }

}
