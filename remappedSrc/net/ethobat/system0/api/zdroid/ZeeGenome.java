package net.ethobat.system0.api.zdroid;

import net.ethobat.system0.api.GreekLetter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ZeeGenome implements Map<GreekLetter, ZeeGene> {

    private ZeeGene[] genes;
    private ZeeAbilities phenotype;

    public ZeeGenome(ZeeGene[] genes) {
        this.genes = genes;
        this.generatePhenotype();
    }

    public ZeeAbilities getPhenotype () {
        return this.phenotype;
    }

    public void generatePhenotype() {
        ZeeAbilities za = new ZeeAbilities();
        for (ZeeGene gene : this.values()) {
            za = za.getCombinedEffectsForPhenotype(gene.getEffects());
        }
        this.phenotype = za;
    }

    @Override
    public int size() {
        return 24;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return key instanceof GreekLetter;
    }

    @Override
    public boolean containsValue(Object value) {
        for (ZeeGene gene : this.genes) {
            if (gene.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ZeeGene get(Object key) {
        int index;
        if (key instanceof GreekLetter) {
            index = ((GreekLetter) key).asInt();
        }
        else {
            index = (int) key;
        }
        return this.genes[index];
    }

    @Nullable
    @Override
    public ZeeGene put(GreekLetter key, ZeeGene value) {
        return null;
    }

    @Override
    public ZeeGene remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends GreekLetter, ? extends ZeeGene> m) {

    }

    // nope
    @Override
    public void clear() {
    }

    @NotNull
    @Override
    public Set<GreekLetter> keySet() {
        return new HashSet<GreekLetter>(Arrays.asList(GreekLetter.ALL));
    }

    @NotNull
    @Override
    public Collection<ZeeGene> values() {
        return new HashSet<ZeeGene>(Arrays.asList(this.genes));
    }

    // fuck off
    @NotNull
    @Override
    public Set<Entry<GreekLetter, ZeeGene>> entrySet() {
        return new HashSet<Entry<GreekLetter, ZeeGene>>();
    }
}