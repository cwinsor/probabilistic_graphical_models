/**
 * 
 */
package cwinsor.us.b.persistence;

import java.io.File;
import java.io.Serializable;

/**
 * @author cwinsor
 * This class holds data we might want to be persistent
 * It is serializable
 */
public class PersistentData implements Serializable {
	private static final long serialVersionUID = 1L;

	String PROJ_PATH;	// path to top of project

	public String getPersistentFileName () {
		String str = "";
		str.concat(System.getProperty("user.dir"));
		str.concat("\\state_serialized.ser");
		return str;
	}

	public void setToDefault() {
		this.setProjPath(null);
	}

	public void setProjPath(String x) {
		PROJ_PATH = x;
	}

	public String getProjPath() {
		return PROJ_PATH;
	}

	public void save(String filename) {
		PersistentToFromFile stff = new PersistentToFromFile();
		stff.toFile(this, filename);
	}

	public void restore(String filename) {
		PersistentToFromFile stff = new PersistentToFromFile();
		stff.fromFile(this, filename);
	}

	public void populateFromSerialObject(Object result) {
		PersistentData temp = (PersistentData) result;
		this.setProjPath(temp.getProjPath());;
	}

}
