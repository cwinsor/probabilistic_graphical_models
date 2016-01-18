/**
 * 
 */
package cwinsor.us.a.pgm;

import weka.core.Instances;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import cwinsor.us.b.persistence.PersistentData;

/**
 * @author cwinsor
 *
 */
public class App01_persistence {

	String persistentDataFilename;	
	PersistentData persistentData;


	public App01_persistence() {
		//persistentDataFilename = System.getProperty("user.dir"));
		//persistentDataFilename.concat("\\data_files\\serialized_state.ser");
		persistentDataFilename = "C:\\serialized_state.ser";

		persistentData = new PersistentData();	
		persistentData.setToDefault();
	}

	public void stateRestore() {
		System.out.printf("\n---restoring state---");
		//zona System.out.printf("\nprojectPath: %s",persistentData.getProjPath());
		persistentData.restore(persistentDataFilename);
	}

	public void stateState() {
		System.out.printf("\n---saving state---");
		persistentData.save(persistentDataFilename);
	}


	private File chooseFile() {
		System.out.printf("\n---choosing data file---");

		//Create a file chooser
		String projPath = persistentData.getProjPath();
		final JFileChooser chooser = new JFileChooser(projPath);
		// directories only	
		//	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// files only
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// disable the "All files" option.
		chooser.setAcceptAllFileFilterUsed(false);

		//In response to a button click:
		if (chooser.showOpenDialog(/*this*/null) == JFileChooser.APPROVE_OPTION) { 
			//			System.out.println("zona - getCurrentDirectory(): " 
			//					+  chooser.getCurrentDirectory());
			//			System.out.println("zona - getSelectedFile() : " 
			//					+  chooser.getSelectedFile());
		}
		else {
			System.out.println("No Selection ");
		}
		persistentData.setProjPath(chooser.getSelectedFile().toString());
		return  chooser.getSelectedFile();
	}

	/**
	 * reading .rtff from file
	 */
	public Instances readDataFromFile(File file) {
		System.out.printf("\n---reading data from file---");

		BufferedReader reader;
		Instances data = null;
		try {
			reader = new BufferedReader(
					new FileReader(file));
			data = new Instances(reader);
			reader.close();
			// setting class attribute
			data.setClassIndex(data.numAttributes() - 1);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* test...
		PersistentData pd1 = new PersistentData();
		pd1.restore();
		String temp = 	pd1.getProjPath();		
		System.out.printf("%s",temp);

		PersistentData pd = new PersistentData();
		pd.setProjPath("blahblahblah");
		pd.save();

		PersistentData pd2 = new PersistentData();
		pd2.restore();
		temp = 	pd2.getProjPath();		
		System.out.printf("%s",temp);
		 */

		App01_persistence theApp = new App01_persistence();
		theApp.stateRestore();
		File file = theApp.chooseFile();
		theApp.stateState();
		Instances instances = theApp.readDataFromFile(file);
		// do more stuff

		System.out.printf("\n---done---");

	}


}
