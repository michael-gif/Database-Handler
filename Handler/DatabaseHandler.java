package Handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import JSON.HashMapValue;

public class DatabaseHandler {
	
	public File file;
	public String filename;
	public String mode;
	
	public DatabaseHandler(String filename, String mode) {
		File potentialFile = new File(filename);
		if (potentialFile.exists()) {
			this.filename = filename;
			this.file = potentialFile;
			this.mode = mode;
		} else {
			this.filename = null;
			System.out.println("Error: file \"" + filename + "\" does not exist");
		}
	}
	
	public DatabaseHandler() {	
	}
	
	public void bindToFile(String filename) {
		File potentialFile = new File(filename);
		if (potentialFile.exists()) {
			this.filename = filename;
			this.file = potentialFile;
		} else {
			this.filename = null;
			System.out.println("Error: file \"" + filename + "\" does not exist");
		}
	}
	
	public String getBoundFile() {
		return this.filename;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void createResource(int index, String name, String value) {
		switch (this.mode) {
		case "plaintext":
			PlainTextHandler.createResource(index, name, value, this.filename, this.file);
		case "json":
			JSONHandler.createResource(index, name, value, this.filename);
		default:
			break;
		}
	}
	
	public void createResource(String name, String value) {
		switch (this.mode) {
		case "plaintext":
			PlainTextHandler.createResource(name, value, this.filename, this.file);
		case "json":
			JSONHandler.createResource(name, value, this.filename, this.file);
		default:
			break;
		}
	}
	
	public void deleteResource(int index) {
		switch (this.mode) {
		case "plaintext":
			PlainTextHandler.deleteResource(index, this.filename, this.file);
		case "json":
			JSONHandler.deleteResource(index, this.filename, this.file);
		default:
			break;
		}
	}
	
	public ArrayList<String> getResource(String name) {
		ArrayList<String> resource = null;
		switch (this.mode) {
		case "plaintext":
			resource = PlainTextHandler.getResource(name, this.file);
		default:
			break;
		}
		return resource;
	}
	
	public ArrayList<HashMap<String, HashMapValue>> getNamedResource(String name) {
		ArrayList<HashMap<String, HashMapValue>> resource = null;
		resource = JSONHandler.getResource(name, this.filename);
		return resource;
	}
	
	public String getResource(int index) {
		String resource = null;
		switch (this.mode) {
		case "plaintext":
			resource = PlainTextHandler.getResource(index, this.file);
		case "json":
			break;
		default:
			break;
		}
		return resource;
	}
	
	public HashMap<String, HashMapValue> getIndexedResource(int index) {
		HashMap<String, HashMapValue> resource = null;
		resource = JSONHandler.getResource(index, this.filename);
		return resource;
	}
	
	public void changeResource(int index, String name, String value) {
		switch (this.mode) {
		case "plaintext":
			PlainTextHandler.changeResource(index, name, value, this.filename, this.file);
		case "json":
			JSONHandler.changeResource(index, name, value, this.filename, this.file);
		default:
			break;
		}
	}
	
}
