package application;
import java.io.File;
public class FileDeleter {
	File file;
	   FileDeleter(String fileName) { 
		   //this.file = file;
		   file = new File(fileName);
	      try { 
	         if(file.delete()) { 
	            System.out.println(file.getName() + " is deleted!");
	         } else {
	            System.out.println("Delete operation is failed.");
	    		}
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
}
