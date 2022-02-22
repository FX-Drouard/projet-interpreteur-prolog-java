package pcomp.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	public static String writer(String path, String txt) throws IOException {
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
	}
}
