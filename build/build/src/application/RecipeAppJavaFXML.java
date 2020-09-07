package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 *
 * @author ctoel
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecipeAppJavaFXML extends Application {
	RecipeApplication run;
	FXMLLoader loader;
    @Override
    public void start(Stage primaryStage) throws Exception {
            loader = new FXMLLoader();
            loader.setLocation(RecipeAppJavaFXML.class.getResource("FXMLDocument.fxml"));
            ((FXMLDocumentController) loader.getController()).setLoader(loader);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Recipe App");
            primaryStage.show();
            
            run = new RecipeApplication(loader);
            String ret = System.getProperty("file.separator");
            try {
            	File file = new File(System.getProperty("user.home") + ret +"RecipeAppData" + ret + "Recipes.csv");
            	RecipeApplication.filePath = System.getProperty("user.home") + ret +"RecipeAppData" + ret + "Recipes.csv";
            	RecipeApplication.runInitItems(file);
            } catch (IOException e) {
            	try {
            		FileChooser fileChooser =  new FileChooser();
            		fileChooser.setTitle("Locate 'Recipes.csv'");
            		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            		File file = new File(fileChooser.showOpenDialog(primaryStage).getPath()); 
            		RecipeApplication.filePath = file.getAbsolutePath();
            		RecipeApplication.runInitItems(file);
            	} catch (Exception ex) {
            		new File(System.getProperty("user.home") + ret + "RecipeAppData").mkdir();
            		File recipes = new File(System.getProperty("user.home") + ret + "RecipeAppData" + ret + "Recipes.csv");
            		RecipeApplication.filePath = recipes.getAbsolutePath();
            		BufferedWriter newFile = new BufferedWriter(new FileWriter(recipes));
            		RecipeApplication.runInitItems(recipes);
            	}
            }
        ((FXMLDocumentController) loader.getController()).loadIngredientsList();
    }
    
    public String getRootDirectory() {
    	File f = new File(RecipeAppJavaFXML.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    	File dir = f.getAbsoluteFile();
    	String path = dir.toString();
    	return path;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


