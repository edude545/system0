package net.ethobat.system0.api.zdroid;

public class ZeeColony {

    private ZeeGenome genome;
    private int population;

    ZeeColony(ZeeGenome genome) {
        this.genome = genome;
        this.population = 1;
    }

    public ZeeAbilities getEffects() {
        return this.genome.getPhenotype();
    }

    public int getPopulation() {
        return this.population;
    }

    public void doWorkCycle() {
        this.doReplication();
    }

    public int doReplication() {
        this.population += this.getEffects().getReplicationRate() * this.population;
        return this.population;
    }

}
