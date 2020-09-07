
package application;
/**
 *
 * @author ctoel
 */
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.security.CodeSource;
import java.net.URISyntaxException;
import java.io.InputStream;
import javax.swing.JFileChooser;


public class ReadFile {

    String recipeName;
    String recipeDescription;
    String ingredientInfo;
    String row;
    Recipe recipeLoading;
    Ingredient ingredientLoading;
    RecipeDatabase recipeDatabase;
    IngredientDatabase ingredientDatabase;
    int stepNum;

    ReadFile(RecipeDatabase recipeDatabase, IngredientDatabase ingredientDatabase, File inputFile) throws IOException {
        this.recipeDatabase = recipeDatabase;
        this.ingredientDatabase = ingredientDatabase;
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
        while ((row = fileReader.readLine()) != null) {
            String[] data = row.split(",");
            sortData(data);
        }
        fileReader.close();
    }

    void sortData(String[] data) {
        if (data[0].equals("RecipeName") && data.length >=2) {
            recipeName = data[1];
            createRecipeObject(recipeName);
            stepNum=0;
        } else if (data[0].equals("RecipeDescription") && data.length >= 2) {
        	if (data.length>2) {
        		recipeDescription = "";
        		for (int i = 1; i < data.length - 1; i++) {
        			recipeDescription = recipeDescription + data[i] + ",";
        		}
        		recipeDescription = recipeDescription + data[data.length-1];
        	}else {
        		recipeDescription = data[1];
        	}
            addRecipeDescription(recipeDescription);
        } else if (data[0].equals("Step") && data[1] != null) {
        	String recipeStep = "";
        	if (data.length>2) {
        		for (int i = 1; i < data.length - 1; i++) {
        			recipeStep = recipeStep + data[i] + ",";
        		}
        		recipeStep = recipeStep + data[data.length-1];
        	}else {
        		recipeStep = data[1];
        	}
        	stepNum++;
        	recipeLoading.addRecipeStep(stepNum, recipeStep);
        }
        
        else if (data[0].equals("Ingredient") && data[1] != null) {
            ingredientInfo = data[1];
            if (data.length >= 3 && data[2] != null) {
                ingredientInfo = ingredientInfo + "," + data[2];
            }
            if (data.length >= 4 && data[3] != null) {
                ingredientInfo = ingredientInfo + "," + data[3];
            }
            if (data.length >= 5 && data[4] != null) {
            	ingredientInfo = ingredientInfo + "," + data[4];
            }
            createIngredientObject(ingredientInfo);
        } else {

        }
    }

    public void createIngredientObject(String recipeIngredient) {
        String[] ingredientData = recipeIngredient.split(",");
        ingredientLoading = new Ingredient(ingredientData[0]);
        if (ingredientData.length >= 2 && ingredientData[1] != null) {
            try {
                ingredientLoading.setIngredientQuantity(Double.parseDouble(ingredientData[1]));
            } catch (Exception e) {
                ingredientLoading.setIngredientQuantity(0.0);
            }
        }
        if (ingredientData.length >= 3 && ingredientData[2] != null) {
            ingredientLoading.setIngredientUnits(ingredientData[2]);
        }
        if (ingredientData.length >= 4 && ingredientData[3] != null) {
        	ingredientLoading.setIngredientType(ingredientData[3]);
        }
        recipeLoading.addIngredient(ingredientLoading);
    }

    public void createRecipeObject(String recipeName) {
        recipeLoading = new Recipe(recipeName);
        recipeDatabase.addRecipe(recipeLoading);
    }

    public void addRecipeDescription(String recipeDescription) {
        recipeLoading.setDescription(recipeDescription);
    }

    public RecipeDatabase getRecipeDatabase() {
        return recipeDatabase;
    }
}
