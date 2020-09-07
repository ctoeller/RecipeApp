package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
public class WriteFile {
	String fileName;
	File file;
	FileWriter fileWriter;
	BufferedWriter bw;
	WriteFile(String fileName) throws IOException {
		//this.file = file;
		this.fileName = fileName;
		fileWriter = new FileWriter(fileName, true);
		//fileWriter = new FileWriter(file);
		bw = new BufferedWriter(fileWriter);
	}
	public void writeRecipe(Recipe recipe) throws IOException {
		bw.newLine();
		bw.append("RecipeName,");
		bw.append(recipe.getRecipeName());
		bw.newLine();
		bw.append("RecipeDescription,");
		bw.append(recipe.getDescription());
		bw.newLine();
		for (int i = 0; i < recipe.getIngredientList().getList().size(); i++) {
			bw.append("Ingredient,");
			bw.append(recipe.getIngredientList().getList().get(i).getIngredientName()+",");
			bw.append(recipe.getIngredientList().getList().get(i).getIngredientQuantity()+",");
			bw.append(recipe.getIngredientList().getList().get(i).getIngredientUnits()+ ",");
			bw.append(recipe.getIngredientList().getList().get(i).getIngredientType());
			bw.newLine();
		}
		for (int i = 0; i < recipe.getRecipeSteps().size(); i++) {
			if (recipe.getRecipeSteps().get(i).getStep().trim().isEmpty() == false) {
				bw.append("Step,");
				bw.append(recipe.getRecipeSteps().get(i).getStep());
				bw.newLine();
			}
		}
		bw.close();
	}
}
