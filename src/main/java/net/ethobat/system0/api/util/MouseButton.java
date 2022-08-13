package net.ethobat.system0.api.util;

public enum MouseButton {

    LEFT,
    RIGHT,
    MIDDLE;

    public static MouseButton fromInt(int i) {
        return switch (i) {
            case 0 -> LEFT;
            case 1 -> RIGHT;
            case 2 -> MIDDLE;
            default -> LEFT;
        };
    }

}
