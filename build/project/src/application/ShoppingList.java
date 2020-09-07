package application;

import java.util.ArrayList;

public class ShoppingList extends IngredientList {
	
	ShoppingList(){
		
	}
	
	/**
	 * Adds the ingredients from a recipe to the Field ArrayList<Ingredient> 'ingredientList'
	 * @param recipe
	 */
	public void addRecipeToList(Recipe recipe) {
		for (int i = 0; i < recipe.getIngredientList().getList().size(); i++) {
			ingredientList.add(recipe.getIngredientList().getList().get(i));
		}
		handleDuplicateIngredients();
	}
	
	public void handleDuplicateIngredients() {
		ArrayList<Ingredient> tempList = new ArrayList<Ingredient>();
		for (int i = 0; i < ingredientList.size(); i++) {
			tempList.add(ingredientList.get(i));
		}
		for (int i = 0; i < tempList.size(); i++) {
			// Checks to see if tempList already contains Ingredient with same name and type
				for(int j = 0; j < tempList.size(); j++) {
					
					if( (tempList.get(i).getIngredientName().equals(tempList.get(j).getIngredientName())
							&& tempList.get(i).getIngredientType() != null
							&& tempList.get(j).getIngredientType() != null
							&& tempList.get(i).getIngredientType().equals(tempList.get(j).getIngredientType())
							&& i != j)
							//&& tempList.get(i) != tempList.get(j)) 
							|| (tempList.get(i).getIngredientName().equals(tempList.get(j).getIngredientName()) 
									&& tempList.get(i).getIngredientType() == null && tempList.get(j).getIngredientType() == null)
							&& i != j){
							//&& tempList.get(i) != tempList.get(j)){
									
						// If tempList already contains a matching ingredient, create a new ingredient and merge the two together into new ingredient. 
						// Remove the one that already existed in tempList. Then add this new ingredient to tempList. 
						Ingredient combinedIngredient = new Ingredient();
						combinedIngredient.setIngredientName(tempList.get(j).getIngredientName());
						if (tempList.get(j).getIngredientType() != null) {
							combinedIngredient.setIngredientType(tempList.get(j).getIngredientType());
						}
						if (tempList.get(j).getIngredientUnits().equals(tempList.get(i).getIngredientUnits()) == false || (tempList.get(i).getQuantityOverflowList().size() >= 2 && tempList.get(i).getUnitsOverflowList().size() >= 2) ) {
							if (tempList.get(i).getQuantityOverflowList().size() < 2 && tempList.get(i).getUnitsOverflowList().size() < 2) { // in theory, tempList.get(j) should never have and "overflowed" list for quantity or units
								combinedIngredient.addQuantityOverflow(tempList.get(j).getIngredientQuantity());
								combinedIngredient.addQuantityOverflow(tempList.get(i).getIngredientQuantity());
								combinedIngredient.addUnitsOverflow(tempList.get(j).getIngredientUnits());
								combinedIngredient.addUnitsOverflow(tempList.get(i).getIngredientUnits());
							} else if (tempList.get(i).getQuantityOverflowList().size() >= 2 && tempList.get(i).getUnitsOverflowList().size() >= 2) {
								for (int z = 0; z < tempList.get(i).getQuantityOverflowList().size(); z++) {
									if (tempList.get(j).getIngredientUnits().equals(tempList.get(i).getUnitsOverflowList().get(z))) {
										combinedIngredient.setUnitsOverflow(tempList.get(i).getUnitsOverflowList());
										combinedIngredient.setQuantityOverflow(tempList.get(i).getQuantityOverflowList());
										combinedIngredient.getQuantityOverflowList().set(z, tempList.get(i).getQuantityOverflowValue(z) + tempList.get(j).getIngredientQuantity());

									}
								}
							} 

							tempList.set(i, combinedIngredient);
							tempList.remove(j);


						} else {
							combinedIngredient.setIngredientQuantity(tempList.get(j).getIngredientQuantity() + tempList.get(i).getIngredientQuantity());
							combinedIngredient.setIngredientUnits(tempList.get(j).getIngredientUnits());
							//ingredientList.remove(i);
							//i--;
							//tempList.remove(j);
							//tempList.add(combinedIngredient);
							tempList.set(i,combinedIngredient);
							tempList.remove(j);

						}
					} else {

					}
				}
			}
			
		
		ingredientList = tempList;
	}
}
