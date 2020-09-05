package Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlainTextHandler {
	
	protected static void createResource(int index, String name, String value, String filename, File file) {
		try {
			FileWriter filewriter = new FileWriter(filename, true);
			if (!resourceExists(index, file)) {
				filewriter.write(String.valueOf(index) + "," + name + "," + value + "\n");
			} else {
				System.out.println("Error: resource " + String.valueOf(index) + " already exists");
			}
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static void createResource(String name, String value, String filename, File file) {
		try {
			FileWriter filewriter = new FileWriter(filename,true);
			int index = 0;
			while (true) {
				if (!resourceExists(index, file)) {
					filewriter.write(String.valueOf(index) + "," + name + "," + value + "\n");
					break;
				} else {
					index ++;
				}
			}
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static void deleteResource(int index, String filename, File file) {
		try {
			Scanner scanner = new Scanner(file);
			ArrayList<String> buffer = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				try {
					if (Integer.parseInt(parts[0]) != index) { buffer.add(line); }
				} catch (NumberFormatException e) {
					System.out.println("Error: " + parts[0] + " is not a valid index");
				}
			}
			scanner.close();
			try {
				FileWriter filewriter = new FileWriter(filename);
				String fileStream = "";
				for (String part : buffer) { fileStream = fileStream.concat(part + "\n"); }
				filewriter.write(fileStream);
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static ArrayList<String> getResource(String name, File file) {
		ArrayList<String> resources = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts[1].equals(name)) {
					resources.add(line);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return resources;
	}
	
	protected static String getResource(int index, File file) {
		String resource = null;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				try {
					if (Integer.parseInt(parts[0]) == index) { resource = line; }
				} catch (NumberFormatException e) {
					System.out.println("Error: " + parts[0] + " is not a valid index");
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	protected static void changeResource(int index, String name, String value, String filename, File file) {
		try {
			Scanner scanner = new Scanner(file);
			ArrayList<String> buffer = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (Integer.parseInt(parts[0]) != index) {
					buffer.add(line);
				} else {
					buffer.add(String.valueOf(index) + "," + name + "," + value);
				}
				try {
					if (Integer.parseInt(parts[0]) != index) {
						buffer.add(line);
					} else {
						buffer.add(String.valueOf(index) + "," + name + "," + value);
					}
				} catch (NumberFormatException e) {
					System.out.println("Error: " + parts[0] + " is not a valid index");
				}
			}
			scanner.close();
			try {
				FileWriter filewriter = new FileWriter(filename);
				String fileStream = "";
				for (String part : buffer) { fileStream = fileStream.concat(part + "\n"); }
				filewriter.write(fileStream);
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static boolean resourceExists(int index, File file) {
		boolean exists = false;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				try {
					if (Integer.parseInt(parts[0]) == index) { exists = true; }
				} catch (NumberFormatException e) {
					System.out.println("Error: " + parts[0] + " is not a valid index");
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
}
