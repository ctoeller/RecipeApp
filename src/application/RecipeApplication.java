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
import java.util.ArrayList;

import javax.swing.JFileChooser;

import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;

import java.io.IOException;
import javafx.collections.*;
import java.net.URISyntaxException;
import java.io.File;

public class RecipeApplication {

    static RecipeDatabase recipeDatabase;
    static IngredientDatabase ingredientDatabase;
    static ArrayList<String> unitList;
    String FileDirectory;
    static FXMLLoader loader;
    static String filePath;

    RecipeApplication(FXMLLoader loader) throws IOException,URISyntaxException {
    	this.loader = loader;
        recipeDatabase = new RecipeDatabase(loader);
        ingredientDatabase = new IngredientDatabase();
    }

//    public void addRecipe(Recipe newRecipe) {
//    	recipeDatabase.addRecipe(newRecipe, loader);
//    	((FXMLDocumentController) loader.getController()).addRecipeToDatabaseColumn(newRecipe);
//    }
//
//    public Recipe getRecipe(String name) {
//        return null;
//    }

    public static void loadRecipes(RecipeDatabase recipeDatabase, IngredientDatabase ingredientDatabase, File inputFile) throws IOException, URISyntaxException {
        ReadFile readFile = new ReadFile(recipeDatabase, ingredientDatabase, inputFile);
        recipeDatabase = readFile.getRecipeDatabase();
    }

  public static RecipeList generateRecipesList(ObservableList<Ingredient> ingredientList) {
	//Set matches to 0 for all Recipes (instance variable)
	for (int i = 0; i < recipeDatabase.getRecipeList().size(); i++) {
		  recipeDatabase.getRecipeList().get(i).setMatches(0);
	}
	RecipeList newList = new RecipeList();
	
	//Count ingredient matches for each recipe
	for (int i = 0; i< recipeDatabase.getRecipeList().size(); i++) {
		for (int j = 0; j < recipeDatabase.getRecipeList().get(i).getIngredientList().getList().size(); j++) {
			for (int k = 0; k < ingredientList.size(); k++) {
				if (recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(j).getIngredientName().equals(ingredientList.get(k).getIngredientName()) && newList.getRecipeList().contains(recipeDatabase.getRecipeList().get(i)) == false) {
					recipeDatabase.getRecipeList().get(i).incrementMatches();
					newList.addRecipe(recipeDatabase.getRecipeList().get(i));
				} else if (newList.getRecipeList().contains(recipeDatabase.getRecipeList().get(i))) {
					int matches = 0;
					for (int x = 0; x < recipeDatabase.getRecipeList().get(i).getIngredientList().getList().size(); x++) {
						for (int y = 0; y < ingredientList.size(); y++) {
							if (recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(x).getIngredientName().equals(ingredientList.get(y).getIngredientName())){
								matches++;
							}
						}
					}
					if (matches > recipeDatabase.getRecipeList().get(i).getMatches()) {
						recipeDatabase.getRecipeList().get(i).setMatches(matches);
					}
				}
			}
		
		}
	}
	//Sort the list with quick sort based on number of ingredient matches each recipe has. 
	QuickSort sort = new QuickSort(newList.getRecipeList(), 0, newList.getRecipeList().size()-1);
	
	//Reverse the list so the recipes with the highest ingredient match count are at the top of the list
	ArrayList<Recipe> revList = new ArrayList<Recipe>();
	for (int i = newList.getRecipeList().size()-1; i >=0; i--) {
		revList.add(newList.getRecipeList().get(i));
	}
	newList.setRecipeList(revList);
	return newList;
}
  public static void initUnitList()  {
	  unitList.add("Cups");
	  unitList.add("Quarts");
	  unitList.add("Tablespoons");
	  unitList.add("Teaspoons");
	  unitList.add("Fluid Ounces");
	  unitList.add("Gallons");
	  unitList.add("Pints");
	  unitList.add("Liters");
	  unitList.add("Milliliters");
	  unitList.add("Pounds");
	  unitList.add("Ounces");
	  unitList.add("Milligrams");
	  unitList.add("Grams");
	  unitList.add("Kilograms");
	  unitList.add("Millimeters");
	  unitList.add("Centimeters");
	  unitList.add("Meters");
	  unitList.add("Inches");
  }

  public static ArrayList<String> getUnitList(){
	  return unitList;
  }
  public static void runInitItems(File file) throws IOException, URISyntaxException {
    loadRecipes(recipeDatabase, ingredientDatabase, file);
    unitList = new ArrayList<String>();
    initUnitList();
  }
}
