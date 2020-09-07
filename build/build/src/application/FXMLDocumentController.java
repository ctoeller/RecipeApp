package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.util.*;
import javafx.scene.control.TableView.*;
import javafx.scene.control.TableCell.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javafx.print.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;
import java.io.IOException;
import java.net.URISyntaxException;

//import CheckBoxTableCellTestApp.ClinicClientInfo;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.*;

public class FXMLDocumentController {
	@FXML
	private MenuItem fileOpen;
	@FXML
	private MenuItem fileClose;
	@FXML
	private MenuItem helpAbout;
	@FXML
	private Button AddRecipeButton;
	@FXML
	private Button deleteRecipeButton;
	@FXML
	private Button editRecipeButton;
	@FXML
	private Button mainToShoppingListButton;
	@FXML
	private TableView recipeDatabaseTable;
	@FXML
	private TableColumn recipeDatabaseColumn;
	@FXML
	private TextArea recipeDetailsDisplayBox;
	@FXML
	private BorderPane ingredientBorderPane;
	@FXML
	private Button generateRecipesButton;
	@FXML
	private Button addtoShoppingListButton;
	@FXML
	private TableView generatedRecipesTable;
	@FXML
	private TableColumn generatedRecipesColumn;
	@FXML
	private TextArea recipeDetailsBox;
	@FXML
	private Button printButton;
	@FXML
	private Button clearButton;
	@FXML
	private TextArea shoppingListTextBox;
	

	private TableView<IngredientInfo> selectIngredientsTable;
	private TableColumn<IngredientInfo, Boolean> ingredientCheckboxColumn;
	private TableColumn<IngredientInfo, String> tableIngredientsColumn;
	private ObservableList<IngredientInfo> ingredientInfoList;
	private ShoppingList shoppingList;
	
	static FXMLLoader loader;
	
	
	@FXML
	public void initialize(){
		
		shoppingList = new ShoppingList();
		recipeDatabaseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			Recipe displayedRecipe;
		    if (newSelection != null) {
		    	for(int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
		    		if (recipeDatabaseColumn.getCellObservableValue(recipeDatabaseTable.getSelectionModel().getSelectedItem()).getValue().toString().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i).getRecipeName())){
		    			displayedRecipe = RecipeApplication.recipeDatabase.getRecipeList().get(i);
		    			displayRecipe(displayedRecipe);
		    		}
		    	}
		    }
		});

		generatedRecipesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			Recipe displayedRecipe;
		    if (newSelection != null) {
		    	for(int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
		    		if (generatedRecipesColumn.getCellObservableValue(generatedRecipesTable.getSelectionModel().getSelectedItem()).getValue().toString().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i).getRecipeName())){
		    			displayedRecipe = RecipeApplication.recipeDatabase.getRecipeList().get(i);
		    			tabDisplayRecipe(displayedRecipe);
		    		}
		    	}
		    }
		});
	}
	@FXML
	public void openCSV(ActionEvent event) {
		FileChooser fileChooser =  new FileChooser();
		fileChooser.setTitle("Locate 'Recipes.csv'");
		//fileChooser.setInitialDirectory(new File(RecipeAppJavaFXML.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsoluteFile().getParentFile());
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = new File(fileChooser.showOpenDialog(null).getPath()); 
		RecipeApplication.filePath = file.getAbsolutePath();
		System.out.println(file.getAbsolutePath());
		try {
			RecipeApplication.recipeDatabase = new RecipeDatabase(loader);
	        RecipeApplication.ingredientDatabase = new IngredientDatabase();
			RecipeApplication.runInitItems(file);
			loadIngredientsList();
			refreshRecipeDatabaseColumn();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on MenuItem[#fileClose].onAction
	@FXML
	public void exit(ActionEvent event) {
		Platform.exit();
	}
	// Event Listener on MenuItem[#helpAbout].onAction
	@FXML
	public void openAbout(ActionEvent event) {
		Stage stage2 = new Stage();
		BorderPane newBP = new BorderPane();
		Label promptText = new Label("Recipe App How To");
		TextArea text = new TextArea();
		text.setText("\tRecipe App was created for the purpose of getting the most out of our groceries. "
				+ "All too often, we throw away extra ingredients because we can't think of a good use for them. Not anymore! "
				+ "This app automatically finds recipes from your own personal database of recipes that will maximize the use of ingredients that you have on-hand in your home. "
				+ "Try it out!"
				+ "\n\nRecipes Tab - \n\nAdd all of your favorite recipes using the Add button and filling in all of the recipe info."
				+ "\n\nRecipe Suggestions Tab - \n\n\t1) Select all of the ingredients you have on-hand in your house using the checkboxes on the left."
				+ "\n\n\t2) Click Generate Recipes button at the bottom of the left column."
				+ " Browse through the generated list of recipes in the middle column. *Recipes at the top of this list utilize more of the"
				+ " ingredients you have on-hand."
				+ "\n\n\t3) As you find recipes that interest you, simply click the Add to Shopping List button at the bottom to add all of the ingredients necessary"
				+ " for the selected recipe to be added to your shopping list."
				+ "\n\nShopping List Tab - \n\nView your shopping list containing of all the ingredients needed for the recipes you chose in the previous tab."
				+ "\n\nCopyright 2020 - Chris Toeller");
		text.setWrapText(true);
		text.setEditable(false);
		
		newBP.setPrefWidth(500);
		newBP.setPrefHeight(400);
		Button btText = new Button("Got it.");
		FlowPane buttonPane = new FlowPane();
		BorderPane lowerPane = new BorderPane();
		buttonPane.getChildren().add(btText);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setHgap(10);
		Insets pad = new Insets(15);
		buttonPane.setMargin(btText, pad);
		lowerPane.setCenter(buttonPane);
		lowerPane.setAlignment(buttonPane, Pos.BOTTOM_CENTER);
		newBP.setTop(promptText);
		newBP.setCenter(text);
		newBP.setAlignment(promptText, Pos.CENTER);
		newBP.setBottom(lowerPane);
		Scene promptWindow = new Scene(newBP);
		stage2.setScene(promptWindow);
		stage2.setTitle("About Recipe App");
		stage2.show();
		
		// Action listener for Button
		btText.setOnAction(e -> {
			stage2.close();
			return;
		});
	}
	// Event Listener on Button[#AddRecipeButton].onAction
	@FXML
	public void addRecipe(ActionEvent event) throws Exception {
		Stage newWindow = new Stage();
		Parent newFXML = FXMLLoader.load(getClass().getResource("AddRecipeFXML.fxml"));
        Scene newRecipeWindow = new Scene(newFXML);
        newWindow.setScene(newRecipeWindow);
        newWindow.show();
        
	}
	// Event Listener on Button[#deleteRecipeButton].onAction
	@FXML
	public void deleteRecipe(ActionEvent event) throws IOException {
		Recipe recipeToDelete;
		for (int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			if (recipeDatabaseColumn.getCellObservableValue(recipeDatabaseTable.getSelectionModel().getSelectedItem()).getValue().toString().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i).getRecipeName())) {
				recipeToDelete = RecipeApplication.recipeDatabase.getRecipeList().get(i);
				RecipeApplication.recipeDatabase.removeRecipe(recipeToDelete);
			}
		}
		
		recipeDatabaseTable.getItems().remove(recipeDatabaseTable.getSelectionModel().getSelectedItem());
		loadIngredientsList();
		String ret = System.getProperty("file.separator");
		FileDeleter deleteRecipeFile = new FileDeleter(RecipeApplication.filePath);
		
		for (int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			WriteFile newRecipeFile = new WriteFile(RecipeApplication.filePath);
			newRecipeFile.writeRecipe(RecipeApplication.recipeDatabase.getRecipeList().get(i));
		}
	}
	// Event Listener on Button[#editRecipeButton].onAction
	@FXML
	public void editRecipe(ActionEvent event) throws IOException {
		EditRecipeController.setRecipe(((Recipe) recipeDatabaseTable.getSelectionModel().getSelectedItem()));
		EditRecipeController.setLoader(loader);
		Parent root = FXMLLoader.load(getClass().getResource("EditRecipe.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
		
	}
	@FXML
	public void mainToShoppingList(ActionEvent event) {
		shoppingListTextBox.clear();		
		shoppingList.addRecipeToList((Recipe) recipeDatabaseTable.getSelectionModel().getSelectedItem());
		shoppingListTextBox.insertText(0, shoppingList.toString());

	}
	
	// Event Listener on Button[#generateRecipesButton].onAction
	@FXML
	public void generateRecipes(ActionEvent event) {
		RecipeList newList = new RecipeList();
		
		//Create ArrayList of ingredients that are checked
		ObservableList<Ingredient> checkedIngredients = FXCollections.observableArrayList();
		for (int i =0; i<ingredientInfoList.size(); i++) {
			if (ingredientInfoList.get(i).getIsChecked() == true) {
				checkedIngredients.add(ingredientInfoList.get(i).getIngredient());
			}
		}
		newList = RecipeApplication.generateRecipesList(checkedIngredients);
		fillRecipesColumn(newList);
	}
	// Event Listener on Button[#addtoShoppingListButton].onAction
	@FXML
	public void addToShoppingList(ActionEvent event) {
		
		shoppingListTextBox.clear();		
		shoppingList.addRecipeToList((Recipe) generatedRecipesTable.getSelectionModel().getSelectedItem());
		shoppingListTextBox.insertText(0, shoppingList.toString());
	}
	// Event Listener on Button[#printButton].onAction
	@FXML
	public void printList(ActionEvent event) {
		printPage(shoppingListTextBox);

	}
    public void printPage(final Node node) {
    	
    	PrinterJob job = PrinterJob.createPrinterJob();
    	if (job != null && job.showPrintDialog(node.getScene().getWindow())){
    	    boolean success = job.printPage(node);
    	    if (success) {
    	        job.endJob();
    	    }
    	}
	}
	// Event Listener on Button.onAction
	@FXML
	public void saveList(ActionEvent event) throws IOException , URISyntaxException {
		ExtensionFilter filter = new ExtensionFilter("Text File", "*.txt");
		FileChooser fileSaver = new FileChooser();
		fileSaver.getExtensionFilters().add(filter);
		fileSaver.setInitialFileName("ShoppingList.txt");
		File shopList = fileSaver.showSaveDialog(null);
		
		if (shopList != null)
		{
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(shopList));				
				String ingredientString = "";
		        for (int i = 0; i < shoppingList.getList().size(); i++) {
		        		int j = 0;
		        		ingredientString = "";
		        		ingredientString = ingredientString + shoppingList.getList().get(i).getIngredientName() + " (" + shoppingList.getList().get(i).getIngredientType() + ") " + shoppingList.getList().get(i).getQuantityOverflowValue(j) + " " + shoppingList.getList().get(i).getUnitsOverflowValue(j);
		        		j++;
		        		while (j < shoppingList.getList().get(i).getQuantityOverflowList().size() && j < shoppingList.getList().get(i).getUnitsOverflowList().size()) {
		        			ingredientString = ingredientString + " + " + shoppingList.getList().get(i).getQuantityOverflowValue(j) + " " + shoppingList.getList().get(i).getUnitsOverflowValue(j);
		        			j++;
		        		}
		        		ingredientString = ingredientString + "\n";
		        		writer.write(ingredientString);
		        		writer.newLine();
		        }
				writer.flush();
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void clearShoppingList(ActionEvent event) {
		shoppingList = new ShoppingList();
		shoppingListTextBox.clear();
	}
	public void addRecipeToDatabaseColumn(Recipe recipe) {
		recipeDatabaseColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		recipeDatabaseTable.getItems().add(recipe);
	}
	public void displayRecipe(Recipe recipe) {
		recipeDetailsDisplayBox.clear();
		recipeDetailsDisplayBox.insertText(0, recipe.toString());
		
	}
	public void tabDisplayRecipe(Recipe recipe) {
		recipeDetailsBox.clear();
		recipeDetailsBox.insertText(0, recipe.toString());
	}
	public void loadIngredientsList() {
		selectIngredientsTable = new TableView<IngredientInfo>();
		tableIngredientsColumn = new TableColumn<IngredientInfo,String>("Select Ingredients");
		ingredientCheckboxColumn = new TableColumn<IngredientInfo,Boolean>();
		selectIngredientsTable.getItems().clear();
		ingredientInfoList = FXCollections.observableArrayList();
		
		//Add all ingredients to list as IngredientInfo Objects
		for (int i =0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			for (int j = 0; j< RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().size(); j++) {
				ingredientInfoList.add(new IngredientInfo(RecipeApplication.recipeDatabase.getRecipeList().get(i).getIngredientList().getList().get(j), false));
			}
		}
		
		//Remove duplicate ingredient name entries (Additional ingredient object data is irrelevant for list because the TableColumn will only display the name of the Ingredient)
		for (int i = 0; i < ingredientInfoList.size(); i++) {
			for (int j = 0; j < ingredientInfoList.size(); j++) {
				if (ingredientInfoList.get(i).getIngredientName().equals(ingredientInfoList.get(j).getIngredientName()) && (j != i)) {
					ingredientInfoList.remove(j);
				}
			}
		}
		
        for (IngredientInfo ingredient : ingredientInfoList) {
            ingredient.isCheckedProperty().addListener((obs, notChecked, isChecked) ->{
            });
        }
        
		selectIngredientsTable.getColumns().addAll(ingredientCheckboxColumn, tableIngredientsColumn);
		selectIngredientsTable.setItems(ingredientInfoList);
		tableIngredientsColumn.setEditable(true);
	    selectIngredientsTable.setEditable(true);
	    tableIngredientsColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
	    tableIngredientsColumn.setVisible(true);
	    ingredientCheckboxColumn.setCellValueFactory(new PropertyValueFactory<>("isChecked"));
		ingredientCheckboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(ingredientCheckboxColumn));
		ingredientCheckboxColumn.setMinWidth(25);
		ingredientCheckboxColumn.setMaxWidth(400);
		selectIngredientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ingredientCheckboxColumn.setVisible(true);
		ingredientCheckboxColumn.setEditable(true);
		ingredientBorderPane.setCenter(selectIngredientsTable);
		tableIngredientsColumn.setSortable(true);
		selectIngredientsTable.sort();
		selectIngredientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	public class IngredientInfo {
		private final StringProperty ingredientName;
		private final BooleanProperty isChecked;
		private Ingredient ingredient;
		public IngredientInfo(Ingredient ingredient, boolean isChecked){
			this.ingredientName = new SimpleStringProperty( ingredient.getIngredientName());
			this.isChecked = new SimpleBooleanProperty(isChecked);
			this.ingredient = ingredient;
		}
		public Ingredient getIngredient() {
			return ingredient;
		}
		public boolean getIsChecked() {
			return isChecked.get();
		}
		public String getIngredientName() {
			return ingredientName.get();
		}
		public StringProperty ingredientNameProperty() {
			return ingredientName;
		}
		public void setIsCheckedProperty(boolean isChecked) {
			this.isChecked.set(isChecked);
		}
		public BooleanProperty isCheckedProperty() {
			return isChecked;
		}
	}
	public void fillRecipesColumn(RecipeList list) {
		generatedRecipesTable.getItems().clear();
		generatedRecipesColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		for (int i = 0; i< list.getRecipeList().size(); i++) {
			generatedRecipesTable.getItems().add(list.getRecipeList().get(i));
		}
	}
	public void refreshRecipeDatabaseColumn() {
		recipeDatabaseColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		recipeDatabaseTable.getItems().clear();
		for (int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			recipeDatabaseTable.getItems().add(RecipeApplication.recipeDatabase.getRecipeList().get(i));
		}
	}
	public static void setLoader(FXMLLoader mainLoader) {
		loader = mainLoader;
	}
}
