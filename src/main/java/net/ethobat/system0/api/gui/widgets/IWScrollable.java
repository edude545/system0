package net.ethobat.system0.api.gui.widgets;

public interface IWScrollable {

    // Positive direction is up, negative is down.
    void onMouseScroll(double mouseX, double mouseY, double direction);

}
