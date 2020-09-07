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
import java.util.ArrayList;
public class Ingredient {

    String ingredientName;
    String ingredientType;
    //double ingredientQuantity;
    //String ingredientUnits;
    ArrayList<String> unitsOverflow;
    ArrayList<Double> quantityOverflow;

    Ingredient() {
    	unitsOverflow = new ArrayList<String>();
    	quantityOverflow = new ArrayList<Double>();
    }

    Ingredient(String ingredientName) {
    	unitsOverflow = new ArrayList<String>();
    	quantityOverflow = new ArrayList<Double>();
        this.ingredientName = ingredientName;
    }

    Ingredient(String ingredientName, double quantity) {
    	unitsOverflow = new ArrayList<String>();
    	quantityOverflow = new ArrayList<Double>();
        this.ingredientName = ingredientName;
        this.quantityOverflow.add(quantity); 
    }

    Ingredient(String ingredientName, double quantity, String units) {
    	unitsOverflow = new ArrayList<String>();
    	quantityOverflow = new ArrayList<Double>();
        this.ingredientName = ingredientName;
        this.quantityOverflow.add(quantity);
        this.unitsOverflow.add(units);
    }
    Ingredient(String ingredientName, double quantity, String units, String type) {
    	unitsOverflow = new ArrayList<String>();
    	quantityOverflow = new ArrayList<Double>();
        this.ingredientName = ingredientName;
        this.ingredientType = type;
        this.quantityOverflow.add(quantity);
        this.unitsOverflow.add(units);
    }

    public void setIngredientName(String ingredientName) {
    	
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientType(String ingredientType) {
    	this.ingredientType = ingredientType;
    }
    public String getIngredientType() {
    	return ingredientType;
    }

    public void setIngredientQuantity(double quantity) {
    	if (quantityOverflow.size() == 0) {
    		quantityOverflow.add(quantity);
    	} else {
    		this.quantityOverflow.set(0,quantity);
    	}
    }

    public double getIngredientQuantity() {
        return quantityOverflow.get(0);
    }

    public void setIngredientUnits(String units) {
    	if (unitsOverflow.size() == 0) {
    		unitsOverflow.add(units);
    	} else {
    		this.unitsOverflow.set(0, units);
    	}
    }

    public String getIngredientUnits() {
        return unitsOverflow.get(0);
    }
    
    public void addQuantityOverflow(double quantity) {
    	quantityOverflow.add(quantity);
    }
    public double getQuantityOverflowValue(int index){
    	return quantityOverflow.get(index);
    }
    public void setQuantityOverflow(ArrayList<Double> quantityOverflow) {
    	this.quantityOverflow = quantityOverflow;
    }
    public ArrayList<Double> getQuantityOverflowList(){
    	return quantityOverflow;
    }
    public void addUnitsOverflow(String units) {
    	unitsOverflow.add(units);
    }
    public void setUnitsOverflow(ArrayList<String> unitsOverflow) {
    	this.unitsOverflow = unitsOverflow;
    }

    public String getUnitsOverflowValue(int index) {
    	return unitsOverflow.get(index);
    }
    public ArrayList<String> getUnitsOverflowList(){
    	return unitsOverflow;
    }

    @Override
    public String toString() {
        return ingredientName + quantityOverflow.get(0) + unitsOverflow.get(0);
    }
}
