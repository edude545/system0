package net.ethobat.system0.api.recipe;

import net.minecraft.item.Item;

public class S0Recipe {

    private final S0RecipeType recipeType;
    private final Item[] inputs;
    private final Item[] outputs;

    public S0Recipe(S0RecipeType recipeType, Item[] inputs, Item[] outputs) {
        this.recipeType = recipeType;
        this.inputs = inputs;
        this.outputs = outputs;
    }

}
