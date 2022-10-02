package net.ethobat.system0.api.gui.widgets;

import net.ethobat.system0.api.util.MouseButton;

public interface IWClickable {

    void onMouseInteract(double mouseX, double mouseY, MouseButton mouseButton, boolean released);

}
