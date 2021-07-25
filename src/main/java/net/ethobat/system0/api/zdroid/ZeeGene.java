package net.ethobat.system0.api.zdroid;

public class ZeeGene {

    private ZeeAllele firstAllele;
    private ZeeAllele secondAllele;
    private ZeeAbilities effects;

    public ZeeGene(ZeeAllele firstAllele, ZeeAllele secondAllele) {
        this.firstAllele = firstAllele;
        this.secondAllele = secondAllele;
        this.generateEffects();
    }

    public boolean has(ZeeAllele allele) {
        return this.firstAllele.equals(allele) || this.secondAllele.equals(allele);
    }

    public boolean equals(ZeeGene gene) {
        return gene.has(this.firstAllele) && gene.has(this.secondAllele);
    }

    public void generateEffects() {
        if (this.firstAllele.getIsDominant() && this.secondAllele.getIsRecessive()) {
            this.effects = firstAllele.getEffects();
        }
        else if (this.firstAllele.getIsRecessive() && this.secondAllele.getIsDominant()) {
            this.effects = secondAllele.getEffects();
        }
        else {
            this.effects = this.firstAllele.getCombinedEffects(this.secondAllele);
        }
    }

    public ZeeAbilities getEffects() {
        return this.effects;
    }

}
