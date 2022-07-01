package net.ethobat.system0.api.registry;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.ethobat.system0.api.progression.ProgressItem;
import net.ethobat.system0.api.psionics.PsionicSchema;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.HashMap;

public class S0Registry<T> {

    public static final S0Registry<PsionicSchema> SCHEMA = new S0Registry<>();
    public static final S0Registry<EnergyType> ENERGY_TYPE = new S0Registry<>();
    public static final S0Registry<ProgressItem> PROGRESSION = new S0Registry<>();
    public static final S0Registry<Class<? extends GUIWidget>> WIDGETS = new S0Registry<>();

    private final HashMap<Identifier, T> MAP;

    public S0Registry() {
        this.MAP = new HashMap<>();
    }

    public T get(Identifier id) {
        T ret = this.MAP.get(id);
        if (ret == null) {
            throw new IllegalArgumentException("Nothing registered under key "+id.toString());
        } else {
            return ret;
        }
    }

    public T get(String string) {
        return this.get(new Identifier(string));
    }

    public T register(T obj, Identifier id) {
        return this.MAP.put(id, obj);
    }

    public boolean hasKey(Identifier id) {
        return this.MAP.containsKey(id);
    }

    public boolean hasKey(String string) {
        return this.MAP.containsKey(new Identifier(string));
    }

    public Collection<T> getAll() {
        System.out.println("Schema registry contents: "+this.MAP.values().toString());
        return this.MAP.values();
    }

}
