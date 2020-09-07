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
import javafx.fxml.FXMLLoader;


public class RecipeDatabase extends RecipeList {
	FXMLLoader loader;
    RecipeList RecipeDatabase;

    RecipeDatabase(FXMLLoader loader) {
    	this.loader= loader;
    }
    @Override
    public void addRecipe(Recipe newRecipe) {
    	addRecipe(newRecipe, loader);
    }
    
    public void addRecipe(Recipe newRecipe, FXMLLoader loader) {
    	this.recipeList.add(newRecipe);
    	((FXMLDocumentController) loader.getController()).addRecipeToDatabaseColumn(newRecipe);
    }
}
