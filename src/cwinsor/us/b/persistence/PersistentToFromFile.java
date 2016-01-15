package cwinsor.us.b.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author cwinsor
 * Utility to write/read persistent data to/from file
  */
public class PersistentToFromFile { 

	/**
	 * write object to file
	 * @param persistentData
	 */
	public void toFile(PersistentData persistentData, String filename) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(persistentData);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * read object from file
	 */
	public void fromFile(PersistentData persistentData, String filename) {
	      

		FileInputStream fis;
		try {
			fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object result = ois.readObject();
			persistentData.populateFromSerialObject(result);
			ois.close();
		} catch (FileNotFoundException e) {
			// if there is no state file - print msg and have object default itself
			System.out.println( "- no state file was found -" );
			persistentData.setToDefault();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
