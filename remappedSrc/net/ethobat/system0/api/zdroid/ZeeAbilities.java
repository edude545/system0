package net.ethobat.system0.api.zdroid;

import net.ethobat.system0.api.recipe.S0RecipeType;

import java.util.HashSet;
import java.util.Set;

public class ZeeAbilities {

    private int replicationRate;
    private int territoryRange;
    private HashSet<S0RecipeType> recipeTypes;
    private HashSet<ZeeWorldEffect> worldEffects;

    public ZeeAbilities() {
        this(0, 0, new HashSet<S0RecipeType>(), new HashSet<ZeeWorldEffect>());
    }

    public ZeeAbilities(int replicationRate, int territoryRange, HashSet<S0RecipeType> recipeTypes, HashSet<ZeeWorldEffect> effects) {
        this.replicationRate = replicationRate;
        this.territoryRange = territoryRange;
        this.recipeTypes = recipeTypes;
        this.worldEffects = effects;
    }

    // Used to generate a heterozygous gene's effects when both alleles are expressed (i.e. both dominant or both recessive).
    // This is never called when the gene has a single dominant allele or is homozygous.
    // Numerical values are the ceiling of the average.
    // Recipe types and world effects are the intersection of the sets, i.e. both alleles must provide process/effect.
    public ZeeAbilities getCombinedEffectsForGene(ZeeAbilities za) {

        HashSet<S0RecipeType> newRecipeTypes = new HashSet<S0RecipeType>(this.getRecipeTypes());
        newRecipeTypes.retainAll(za.getRecipeTypes());
        HashSet<ZeeWorldEffect> newWorldEffects = new HashSet<ZeeWorldEffect>(this.getWorldEffects());
        newWorldEffects.retainAll(za.getWorldEffects());

         return new ZeeAbilities(
                 (int) Math.ceil((float) (this.getReplicationRate() + za.getReplicationRate()) / 2),
                 (int) Math.ceil((float) (this.getTerritoryRange() + za.getTerritoryRange()) / 2),
                 newRecipeTypes,
                 newWorldEffects
         );
    }

    // Additively combines zee abilities - used to calculate a genome's ultimate phenotype.
    // Numerical values are added, sets are unioned.
    public ZeeAbilities getCombinedEffectsForPhenotype(ZeeAbilities za) {

        HashSet<S0RecipeType> newRecipeTypes = new HashSet<S0RecipeType>(this.getRecipeTypes());
        newRecipeTypes.addAll(za.getRecipeTypes());
        HashSet<ZeeWorldEffect> newWorldEffects = new HashSet<ZeeWorldEffect>(this.getWorldEffects());
        newWorldEffects.addAll(za.getWorldEffects());

        return new ZeeAbilities(
                this.getReplicationRate() + za.getReplicationRate(),
                this.getTerritoryRange() + za.getTerritoryRange(),
                newRecipeTypes,
                newWorldEffects
        );
    }


    public int getReplicationRate() {
        return replicationRate;
    }

    public int getTerritoryRange() {
        return territoryRange;
    }

    public Set<S0RecipeType> getRecipeTypes() {
        return recipeTypes;
    }

    public Set<ZeeWorldEffect> getWorldEffects() {
        return worldEffects;
    }

}
