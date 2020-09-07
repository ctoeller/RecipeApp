/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
/**
 *
 * @author ctoel
 */
public class Recipe {

    String recipeName;
    String recipeDescription;
    IngredientList ingredientList;
    ArrayList<Step> recipeSteps;
    int numIngredients;
    int matchingIngredients;
    String difficulty;

    Recipe(String recipeName) {
        this.recipeName = recipeName;
        ingredientList = new IngredientList();
        recipeSteps = new ArrayList<Step>();
        for (int i = 0; i < 20; i++) {
        	recipeSteps.add(new Step(i+1,""));
        }
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }
    public String getDescription() {
    	return recipeDescription;
    }

    public void addIngredient(Ingredient newIngredient) {
        ingredientList.addIngredient(newIngredient);
    }
    public IngredientList getIngredientList() {
    	return ingredientList;
    }
    
    public void addRecipeStep(int stepNumber, String step) {
    	Step newStep = new Step(stepNumber, step);
    	recipeSteps.set(stepNumber - 1, newStep);
    }
    public ArrayList<Step> getRecipeSteps(){
    	return recipeSteps;
    }
    public void setRecipeSteps(ArrayList<Step> recipeSteps) {
    	this.recipeSteps = recipeSteps;
    }
    public void clearRecipeSteps() {
    	recipeSteps.clear();
    }
    public void removeRecipeStep(int index) {
    	recipeSteps.remove(index);
    }

    public void addIngredient(String name, double quantity) {

    }

    public void addIngredient(String name, double quantity, String units) {

    }

    public void setIngredientName(String name, String newName) {

    }

    public void setIngredientQuantity(String name, double quantity) {

    }

    public void setIngredientUnits(String name, String units) {

    }

    public void setDifficulty(String level) {

    }

    public String getDifficulty() {
        return difficulty;
    }

    public IngredientList getIngredients() {
        return ingredientList;
    }
    public void incrementMatches() {
    	matchingIngredients++;
    }
    public int getMatches() {
    	return matchingIngredients;
    }
    public void resetMatches() {
    	matchingIngredients = 0;
    }
    public void setMatches(int matches) {
    	matchingIngredients = matches;
    }

    @Override
    public String toString() {
        String recipeString;
        
        recipeString =  recipeName + " - " + recipeDescription + "\n\nIngredients:" + "\n" + ingredientList.toString() + "\n\n" + "Recipe Instructions:" +"\n";
        for (int i = 0; i < this.recipeSteps.size(); i++) {
        	if (recipeSteps.get(i).getStep().trim().isEmpty()== false) {
        		recipeString = recipeString + "Step " + recipeSteps.get(i).getStepNumber() + ": " + recipeSteps.get(i).getStep() + "\n";
        	}
        }
        return recipeString;
    }

}
