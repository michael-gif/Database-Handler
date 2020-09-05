package Handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import JSON.HashMapValue;
import JSON.Reader;
import JSON.Writer;

public class JSONHandler {
	
	protected static void createResource(int index, String name, String value, String filename) {
		if (!resourceExists(index, filename)) {
			ArrayList<HashMap<String, String>> data = new ArrayList<>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("index", String.valueOf(index));
			map.put("name", name);
			map.put("value", value);
			data.add(map);
			Writer.writeString(data, filename);
		} else {
			System.out.println("Error: " + index + " already exists");
		}
	}
	
	protected static void createResource(String name, String value, String filename, File file) {
		int index = 0;
		while (true) {
			if (!resourceExists(index, filename)) {
				ArrayList<HashMap<String, String>> data = new ArrayList<>();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("index", String.valueOf(index));
				map.put("name", name);
				map.put("value", value);
				data.add(map);
				Writer.writeString(data, filename);
				break;
			} else {
				index ++;
			}
		}
	}
	
	protected static void deleteResource(int index, String filename, File file) {
		ArrayList<HashMap<String, HashMapValue>> buffer = new ArrayList<>();
		ArrayList<HashMap<String, HashMapValue>> json = Reader.read(filename);
		for (HashMap<String, HashMapValue> component : json) {
			try {
				if (!component.get("index").getString().equals(String.valueOf(index))) {
					buffer.add(component);
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: " + component.get("index") + " is not a valid index");
			}
		}
		Writer.writeCompound(buffer, filename, false);
	}
	
	protected static ArrayList<HashMap<String, HashMapValue>> getResource(String name, String filename) {
		ArrayList<HashMap<String, HashMapValue>> resources = new ArrayList<>();
		ArrayList<HashMap<String, HashMapValue>> json = Reader.read(filename);
		for (HashMap<String, HashMapValue> component : json) {
			try {
				if (component.get("name").getString().equals(name)) {
					resources.add(component);
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: " + component.get("index") + " is not a valid index");
			}
		}
		return resources;
	}
	
	protected static HashMap<String, HashMapValue> getResource(int index, String filename) {
		HashMap<String, HashMapValue> resource = null;
		ArrayList<HashMap<String, HashMapValue>> json = Reader.read(filename);
		for (HashMap<String, HashMapValue> component : json) {
			try {
				if (component.get("index").getString().equals(String.valueOf(index))) {
					resource = component;
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: " + component.get("index") + " is not a valid index");
			}
		}
		return resource;
	}
	
	protected static void changeResource(int index, String name, String value, String filename, File file) {
		ArrayList<HashMap<String, HashMapValue>> buffer = new ArrayList<>();
		ArrayList<HashMap<String, HashMapValue>> json = Reader.read(filename);
		for (HashMap<String, HashMapValue> component : json) {
			try {
				if (!component.get("index").getString().equals(String.valueOf(index))) {
					buffer.add(component);
				} else {
					HashMap<String, HashMapValue> newData = new HashMap<>();
					newData.put("name", new HashMapValue(name));
					newData.put("index", new HashMapValue(String.valueOf(index)));
					newData.put("value", new HashMapValue(value));
					buffer.add(newData);
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: " + component.get("index") + " is not a valid index");
			}
		}
		Writer.writeCompound(buffer, filename, false);
	}
	
	protected static boolean resourceExists(int index, String filename) {
		boolean exists = false;
		ArrayList<HashMap<String, HashMapValue>> json = Reader.read(filename);
		for (HashMap<String, HashMapValue> component : json) {
			if (component.get("index").getString().equals(String.valueOf(index))) {
				exists = true;
			}
		}
		return exists;
	}
	
}
