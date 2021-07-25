package net.ethobat.system0.api.registry;

import net.ethobat.system0.api.energy.EnergyType;
import net.ethobat.system0.api.gui.widgets.GUIWidget;
import net.ethobat.system0.api.progression.ProgressItem;
import net.ethobat.system0.api.psionics.PsionicSchema;
import net.minecraft.util.Identifier;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

public class S0Registry<T> {

    public static final S0Registry<PsionicSchema> SCHEMA = new S0Registry<>();
    public static final S0Registry<EnergyType> ENERGY_TYPE = new S0Registry<>();
    public static final S0Registry<ProgressItem> PROGRESSION = new S0Registry<>();
    public static final S0Registry<Class<? extends GUIWidget>> WIDGETS = new S0Registry<>();

    private final HashMap<Identifier, T> REGISTRY;

    public S0Registry() {
        this.REGISTRY = new HashMap<>();
    }

    public T get(Identifier id) {
        return this.REGISTRY.get(id);
    }

    public T register(T obj, Identifier id) {
        return this.REGISTRY.put(id, obj);
    }

    public Collection<T> getAll() {
        System.out.println("Schema registry contents: "+this.REGISTRY.values().toString());
        return this.REGISTRY.values();
    }

}
