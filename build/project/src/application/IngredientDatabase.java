/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ctoel
 */
package application;
public class IngredientDatabase extends IngredientList {

    IngredientDatabase() {
    	//ingredientList = new IngredientList();
    }
    public void addDatabaseIngredients() {
    	ingredientList.clear();
		for (int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			for (int j = 0; j <RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().size(); j++) {
				ingredientList.add(RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(j));
				//if (j!=i && RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(i).getIngredientName().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(j).getIngredientName()) && RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(i).getIngredientUnits().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(j).getIngredientUnits())) {
					//Ingredient combinedIngredient = new Ingredient(shoppingList.getList().get(i).getIngredientName());
					//combinedIngredient.setIngredientQuantity(shoppingList.getList().get(i).getIngredientQuantity() + shoppingList.getList().get(j).getIngredientQuantity());
					//combinedIngredient.setIngredientUnits(shoppingList.getList().get(i).getIngredientUnits());
					//shoppingList.getList().remove(i);
					//RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().remove(j);
					//shoppingList.getList().add(combinedIngredient);
				
				//}
			}
		}
    
    }
}