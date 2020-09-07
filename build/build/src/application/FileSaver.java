package application;

import javax.swing.*;    
import java.awt.event.*;    
import java.io.*;    
import java.net.URISyntaxException;
import java.io.IOException;
public class FileSaver extends JFrame {    
JMenuBar mb;    
JMenu file;    
JMenuItem save;    
JTextArea ta;   
File f;
String filePath;
FileSaver() throws IOException, URISyntaxException {    
save=new JMenuItem("Save File");    
            
file=new JMenu("File");    
file.add(save);             
mb=new JMenuBar();    
mb.setBounds(0,0,800,20);    
mb.add(file);              
ta=new JTextArea(800,800);    
ta.setBounds(0,20,800,800);              
add(mb);    
add(ta);              
    
   
    
    JFileChooser fc=new JFileChooser();    
    int i=fc.showSaveDialog(this);    
    if(i==JFileChooser.APPROVE_OPTION){    
        f =fc.getSelectedFile();
        //System.out.println(f.getPath());
        //filePath = f.getPath();
        RecipeApplication.runInitItems(f);
        //String filepath=f.getPath();    

        try{  
        BufferedReader br=new BufferedReader(new FileReader(f));    
        String s1="",s2="";                         
        while((s1=br.readLine())!=null){    
        s2+=s1+"\n";    
        }    
        ta.setText(s2);    
        br.close();    
        }catch (Exception ex) {ex.printStackTrace();  }                 
    }    
}    
        
public static void main(String[] args) throws IOException, URISyntaxException {    
    FileSaver om=new FileSaver();    
             om.setSize(500,500);    
             om.setLayout(null);    
             om.setVisible(true);    
             om.setDefaultCloseOperation(EXIT_ON_CLOSE);    
}    
public File getFile() {
	return f;
}
}  