/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
/**
 *
 * @author ctoel
 */
import java.util.*;

public class RecipeList {

    ArrayList<Recipe> recipeList;
    int numRecipes;
    String recipeListName;

    RecipeList() {
        recipeList = new ArrayList<Recipe>();
    }

    public void setName(String name) {
        this.recipeListName = name;
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getRecipeName().equals(recipe.recipeName)) {
                recipeList.remove(i);
            }
        }
    }

    public void removeRecipe(String recipeName) {
        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getRecipeName().equals(recipeName)) {
                recipeList.remove(i);
            }
        }
    }

    public Recipe getRecipe(String recipeName) {
        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getRecipeName().equals(recipeName)) {
                return recipeList.get(i);
            }
        }
        return null;
    }

    public Recipe getRecipe(int index) {
        return recipeList.get(index);
    }
    public ArrayList<Recipe> getRecipeList(){
    	return recipeList;
    }
    public void setRecipeList(ArrayList<Recipe> recipeList) {
    	this.recipeList = recipeList;
    }

    @Override
    public String toString() {
        String recipeNames = "";
        for (int i = 0; i < recipeList.size(); i++) {
            recipeNames = recipeNames + recipeList.get(i).getRecipeName() + "\n";
        }
        return recipeNames;
    }
}
