package net.ethobat.system0.api.energy;

import net.ethobat.system0.api.color.RGB;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class EnergyType {

    protected final Identifier REGISTRY_ID;
    protected final RGB PRIMARY_COLOR;

    public final Formatting FORMAT_COLOR_CODE;

    public EnergyType(Identifier registryID, RGB primaryColor) {
        this.REGISTRY_ID = registryID;
        this.PRIMARY_COLOR = primaryColor;

        this.FORMAT_COLOR_CODE = this.PRIMARY_COLOR.getFormatCode();
    }

    public Identifier getRegistryID() {
        return this.REGISTRY_ID;
    }

    public String getRegistryIDString() {
        return this.REGISTRY_ID.toString();
    }

    public String getName() {
        return this.getRegistryID().getPath();
    }

    public MutableText getTranslatedName() {
        return new TranslatableText(EnergyType.getTranslationKeyFromID(this.REGISTRY_ID));
    }

    public RGB getPrimaryColor() {
        return this.PRIMARY_COLOR;
    }

    // e.g. "energy.system0.skeintillating"
    public static String getTranslationKeyFromID(Identifier id) {
        return "energy." + id.getNamespace() + "." + id.getPath();
    }

}
