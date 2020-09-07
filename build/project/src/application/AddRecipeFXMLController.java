package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class AddRecipeFXMLController {
	@FXML
	private TextField recipeNameField;
	@FXML
	private TextArea recipeDescriptionBox;
	@FXML
	private Button saveRecipeButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TableView ingredientTable;
	@FXML
	private TableColumn ingredientNameColumn;
	@FXML
	private TableColumn ingredientTypeColumn;
	@FXML
	private TableColumn ingredientQuantityColumn;
	@FXML
	private TableColumn ingredientUnitsColumn;
	@FXML
	private Button addTableRowButton;
	@FXML
	private TextField quantityField;
	@FXML
	private Button deleteTableRow;
	@FXML
	private TextField typeField;
	@FXML
	private ComboBox unitsCombo;
	@FXML
	private ComboBox ingredientNameCombo;
	@FXML
	private FlowPane unitsComboFlowPane;
	private TableView tableView;
	
	@FXML
	public void initialize() {
		FxUtilTest.autoCompleteComboBoxPlus(unitsCombo, ( typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
		for (int i = 0; i < RecipeApplication.getUnitList().size(); i++) {
			unitsCombo.getItems().add(RecipeApplication.getUnitList().get(i));
		}
		FxUtilTest.autoCompleteComboBoxPlus(ingredientNameCombo, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
		RecipeApplication.ingredientDatabase.addDatabaseIngredients();
		for (int i = 0; i < RecipeApplication.ingredientDatabase.getList().size(); i++) {
			ingredientNameCombo.getItems().add(RecipeApplication.ingredientDatabase.getList().get(i).getIngredientName());
		}
	}

	// Event Listener on Button[#saveRecipeButton].onAction
	@FXML
	public void saveRecipe(ActionEvent event) throws IOException {
		String recipeName = recipeNameField.getCharacters().toString();
		String recipeDescription = recipeDescriptionBox.getParagraphs().toString();
		int length = recipeDescription.length();
		recipeDescription = recipeDescription.substring(1,length-1);
		String ingredientName = "";
		double ingredientQuantity;
		String ingredientUnits;
		String ingredientType;
		Recipe newRecipe = new Recipe(recipeName);
		newRecipe.setDescription(recipeDescription);
		for (int i =0; i<ingredientTable.getItems().size(); i++) {
			ingredientName = ingredientNameColumn.getCellObservableValue(i).getValue().toString();
			ingredientQuantity = Double.parseDouble(ingredientQuantityColumn.getCellObservableValue(i).getValue().toString());
			ingredientUnits = ingredientUnitsColumn.getCellObservableValue(i).getValue().toString();
			ingredientType = ingredientTypeColumn.getCellObservableValue(i).getValue().toString();
			Ingredient newIngredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnits, ingredientType);
			newRecipe.addIngredient(newIngredient);
		}
		//Check to see if Recipe has a name
		if (newRecipe.getRecipeName().trim().isEmpty()) {
			System.out.println("Recipe must be given a name.");
			return;
		}
		//Check to see if Recipe Name is unique
		for (int i = 0; i < RecipeApplication.recipeDatabase.getRecipeList().size(); i++) {
			if (newRecipe.getRecipeName().equals(RecipeApplication.recipeDatabase.getRecipeList().get(i))) {
				System.out.println("Recipe name must be unique.");
				return;
			} 
		}
		
		RecipeApplication.recipeDatabase.addRecipe(newRecipe);
		RecipeApplication.ingredientDatabase.addDatabaseIngredients();
		
		((FXMLDocumentController) RecipeApplication.loader.getController()).loadIngredientsList();
		
		Stage stage = (Stage) saveRecipeButton.getScene().getWindow();
		stage.close();
		recipeStepsWindow(newRecipe);


		
	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void closeNewRecipeWindow(ActionEvent event) {
		 Stage stage = (Stage) cancelButton.getScene().getWindow();
		 stage.close();
	}
	// Event Listener on Button[#addTableRowButton].onAction
	@FXML
	public void addTableRow(ActionEvent event) {
		double ingredientQuantity;
		String ingredientName;
		String ingredientType;
		//String ingredientName = ingredientNameField.getCharacters().toString();
		if (ingredientNameCombo.getValue() == null || ingredientNameCombo.getValue().toString().trim().isEmpty()) {
			System.out.println("Ingredient must have a name.");
			return;
		} else if (unitsCombo.getValue() == null || unitsCombo.getValue().toString().trim().isEmpty()) {
			System.out.println("Ingredient must have a unit of measure.");
			return;
		}
		
		try {
			ingredientQuantity = Double.parseDouble( quantityField.getCharacters().toString());
		} catch (Exception e) {
			try {
				ingredientQuantity = parse(quantityField.getCharacters().toString());
			} catch (Exception f) {
				System.out.println("Invalid quantity.");
				return;
			}
		}
		ingredientName = ingredientNameCombo.getValue().toString();
		ingredientType = typeField.getCharacters().toString();
		String ingredientUnits = unitsCombo.getValue().toString();
		ingredientNameCombo.setValue(null);
		quantityField.clear();
		unitsCombo.setValue(null);
		typeField.clear();
		Ingredient testIngredient = new Ingredient(ingredientName, ingredientQuantity, ingredientUnits, ingredientType);
		ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
		ingredientTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientType"));
		ingredientQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientQuantity"));
		ingredientUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientUnits"));
		ingredientTable.getItems().add(testIngredient);
		
		FxUtilTest.autoCompleteComboBoxPlus(unitsCombo, ( typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
		for (int i = 0; i < RecipeApplication.getUnitList().size(); i++) {
			unitsCombo.getItems().add(RecipeApplication.getUnitList().get(i));
		}
		FxUtilTest.autoCompleteComboBoxPlus(ingredientNameCombo, (typedText, itemToCompare) -> itemToCompare.toString().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
		RecipeApplication.ingredientDatabase.addDatabaseIngredients();
		for (int i = 0; i < RecipeApplication.ingredientDatabase.getList().size(); i++) {
			ingredientNameCombo.getItems().add(RecipeApplication.ingredientDatabase.getList().get(i).getIngredientName());
		}
		
	}
	// Event Listener on Button[#deleteTableRow].onAction
	@FXML
	public void deleteTableRow(ActionEvent event) {
		ingredientTable.getItems().remove(ingredientTable.getSelectionModel().getSelectedItem());
		
	}
	
	double parse(String ratio) {
		String pattern = "###.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
	    if (ratio.contains("/")) {
	        String[] rat = ratio.split("/");
	        return Double.parseDouble(decimalFormat.format( Double.parseDouble(rat[0]) / Double.parseDouble(rat[1])));
	    } else {
	        return Double.parseDouble(decimalFormat.format(Double.parseDouble(ratio)));
	    }
	}
	public void recipeStepsWindow(Recipe newRecipe) throws IOException {
		StepsWindowController.setRecipe(newRecipe);
		Stage stage = new Stage();
		Parent newFXML = FXMLLoader.load(getClass().getResource("StepsWindow.fxml"));
		Scene scene = new Scene(newFXML);

        //primaryStage.setTitle("My Title");
        stage.setScene(scene);
        stage.show();
//		Label title = new Label("Recipe Steps");
//		Button saveButton = new Button("Save");
//		Pane leftPane = new Pane();
//		leftPane.setPrefSize(200,600);
//		TextField step1 = new TextField();
//		borderPane1.setLeft(leftPane);
//		borderPane1.setTop(title);
//		borderPane1.setCenter(step1);
//		borderPane1.setBottom(saveButton);

		
	}
	

}
