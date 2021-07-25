package net.ethobat.system0.api.zdroid;

import net.ethobat.system0.api.GreekLetter;

public class ZeeAllele {

    private String name;
    private final GreekLetter gene;
    private final boolean isDominant;
    private final ZeeAbilities effects;

    public ZeeAllele(String name, GreekLetter gene, boolean isDominant, ZeeAbilities effects) {
        this.name = name;
        this.gene = gene;
        this.isDominant = isDominant;
        this.effects = effects;
    }

    // returns a descriptive name, e.g. Tau-Rec-46Rama
    public String getName() {
        return this.gene.asString() + (this.isDominant ? "-Dom-" : "-Rec-") + this.name;
    }

    // used to generate a heterozygous gene's effects when both alleles are expressed (i.e. both dominant or both recessive)
    // this is never called when the gene has a single dominant allele or when homozygous
    public ZeeAbilities getCombinedEffects(ZeeAllele allele) {
        if (this.equals(allele)) {
            return this.getEffects();
        }
        else {
            return this.getEffects().getCombinedEffectsForGene(allele.getEffects());
        }
    }

    // returns the GreekLetter corresponding to this allele's gene slot
    public GreekLetter getGene() {
        return this.gene;
    }

    public boolean getIsDominant() {
        return this.isDominant;
    }

    public boolean getIsRecessive() {
        return !this.isDominant;
    }

    public ZeeAbilities getEffects() {
        return effects;
    }

}
