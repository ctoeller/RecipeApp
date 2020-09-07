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

public class IngredientList {

    ArrayList<Ingredient> ingredientList;
    int numIngredients;

    IngredientList() {
        ingredientList = new ArrayList<Ingredient>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredientList.remove(ingredient);
    }

    public ArrayList<Ingredient> getList() {
        return ingredientList;
    }

    @Override
    public String toString() {
        String ingredientString = "";
        for (int i = 0; i < ingredientList.size(); i++) {
        	//if (ingredientList.get(i).getQuantityOverflowList().size() > 1 && ingredientList.get(i).getUnitsOverflowList().size() > 1) {
        		int j = 0;
        
        		ingredientString = ingredientString + ingredientList.get(i).getIngredientName() + " (" + ingredientList.get(i).getIngredientType() + ") " + ingredientList.get(i).getQuantityOverflowValue(j) + " " + ingredientList.get(i).getUnitsOverflowValue(j);
        		
        		j++;
        		while (j < ingredientList.get(i).getQuantityOverflowList().size() && j < ingredientList.get(i).getUnitsOverflowList().size()) {
        			ingredientString = ingredientString + " + " + ingredientList.get(i).getQuantityOverflowValue(j) + " " + ingredientList.get(i).getUnitsOverflowValue(j);
        			j++;
        		}
        		ingredientString = ingredientString + "\n";
            //ingredientString = ingredientString + "\n" + ingredientList.get(i).getIngredientName() + " (" + ingredientList.get(i).getIngredientType() + ") " + ingredientList.get(i).getIngredientQuantity() + " " + ingredientList.get(i).getIngredientUnits();
        }
        return ingredientString;
    }
}
