package application;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.awt.event.*;
import javafx.event.*;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import java.util.ArrayList;

public class StepsWindowController {
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TableView stepsTable;
	@FXML
	private TableColumn stepNumberColumn;
	@FXML
	private TableColumn instructionsColumn;
	public static Recipe recipe;
	@FXML
	ObservableList<Step> steps;
	public void initialize() {
		steps = FXCollections.observableArrayList();
		fillStepsList();
		instructionsColumn.setEditable(true);
		stepsTable.setEditable(true);
		instructionsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
	    instructionsColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Step, String>>() {
	        @Override
	        public void handle(TableColumn.CellEditEvent<Step, String> t) {
	            t.getRowValue().setStep(t.getNewValue());
	        }
	    });

		stepNumberColumn.setCellValueFactory( new PropertyValueFactory<>("stepNumber"));
		instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("step"));
		stepsTable.setItems(steps);
		
		
	}


	// Event Listener on Button[#saveButton].onAction
	@FXML
	public void saveRecipeSteps(ActionEvent event) throws IOException {
		for (int i = 0; i < recipe.getRecipeSteps().size(); i++) {
			System.out.println(recipe.getRecipeSteps().get(i).toString());
		}
		String ret = System.getProperty("file.separator");
		WriteFile fileWriter = new WriteFile(RecipeApplication.filePath);
		fileWriter.writeRecipe(recipe);
		 Stage stage = (Stage) saveButton.getScene().getWindow();
		 stage.close();
	}
	// Event Listener on Button[#cancelButton].onAction
	@FXML
	public void closeWindow(ActionEvent event) throws IOException {
		for (int i = 0; i < recipe.getRecipeSteps().size(); i++) {
			recipe.addRecipeStep(i+1, "");
		}
		for (int i = 0; i < recipe.getRecipeSteps().size(); i++) {
			System.out.println(recipe.getRecipeSteps().get(i).getStep());
		}
		String ret = System.getProperty("file.separator");
		WriteFile fileWriter = new WriteFile(RecipeApplication.filePath);
		fileWriter.writeRecipe(recipe);
		 Stage stage = (Stage) cancelButton.getScene().getWindow();
		 stage.close();
	}
	
	public static void setRecipe(Recipe newRecipe) {
		recipe = newRecipe;
	}
	public void fillStepsList() {
		for(int i = 0; i< recipe.getRecipeSteps().size(); i++ ) {
			steps.add(recipe.getRecipeSteps().get(i));
		}
	}
	
}
