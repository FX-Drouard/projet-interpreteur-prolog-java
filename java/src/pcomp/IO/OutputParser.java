package pcomp.IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 * Cette classe permet d'ecrire un String dans un .proji | version Personnel V0.9
 * @author François-Xavier Drouard 
 */
public class OutputParser {
	/**
	 * Permet d'écrire dans un fichier un string
	 * @param path: le path
	 * @param txt: Le String
	 * @throws IOException
	 * @return Le path du fichier ecrit!
	 */
	/*public static String writer(String path, String txt) throws IOException {
		String pathok = path.split(".pl")[0];
		try {
			   File file = new File(pathok+".pl");
			   int i =0;
			   // créer le fichier s'il n'existe pas
			   while (file.exists()) {
				   i++;
				   pathok = path.split(".pl")[0]+"_"+i;
				   file = new File(pathok+".pl");
			   }
			   file.createNewFile();
			   FileWriter fw = new FileWriter(file.getAbsoluteFile());
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(txt);
			   bw.close();
			   return pathok+".pl";
			  } catch (IOException e) {
				 throw new IOException("Erreur");
			  }
	}*/
	public static void write(JFrame frame, String txt) throws IOException {
		JFileChooser chooser = new JFileChooser( new File("."));
        chooser.setMultiSelectionEnabled(false);
        
        chooser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                return;
            }
        });

        FileFilter txtFilter = new FXFileFilter("pl");
        
        chooser.setFileFilter(txtFilter);
        chooser.setDialogTitle("Choisissez un .pl");
        int choix = chooser.showOpenDialog(frame);
        if (choix == JFileChooser.APPROVE_OPTION) {
           File file = chooser.getSelectedFile();
           if(!chooser.getSelectedFile().getAbsolutePath().endsWith(".pl")){
               file = new File(chooser.getSelectedFile() + ".pl");
           }
            try
            {
 			   // créer le fichier 
 			   file.createNewFile();
 			   FileWriter fw = new FileWriter(file.getAbsoluteFile());
 			   BufferedWriter bw = new BufferedWriter(fw);
 			   bw.write(txt);
 			   bw.close();
              
               
            }
            catch(IOException e)
            {
              throw new IOException("Ecriture du fichier:\n"+e.getMessage());
            }
        } else {
            throw new IOException("Aucun fichier choisie");
        }
	}
}
