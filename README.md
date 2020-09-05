# Database-Handler
This package is supposed to make managing resources a bit easier in java.

## Usage
#### <ins>Getting Started</ins>
- Firstly, make an instance of `DatabaseHandler.java`. The constructor takes two parameters, `String filename` and `String mode`.
```java
DatabaseHandler dh = new DatabaseHandler("database.txt","json");
```
- The `filename` is the file that you want to use as a database. The `mode` is what you want the data to be stored as - see different [Modes](###Modes) below.

### Modes
- The different modes are as follows:

| Mode | Description |
| ---- | ----------- |
| `json` | Resources are in JSON objects |
| `plaintext` | Resources are `String`'s, separated by commas |

#### <ins>Creating Resources</ins>
- There are two methods available for creating a resource:
```java
public void createResource(int index, String name, String value) {}
public void createResource(String name, String value) {}
```
The first method creates a resource with the specifed `index`. If a resource with that index already exists, then an error will be given.  
The second method creates a resource with the next available `index`. If index `0` is unused, then the method will create a resource with index `0` etc.

#### <ins>Deleting Resources</ins>
- There is one method available for deleting a resource:
```java
public void deleteResource(int index) {}
```
This method will delete the resource with the specified index. If the index is invalid, then an error will be given.

#### <ins>Getting Resources</ins>
- There are four method available for getting resources:
```java
public ArrayList<String> getResource(String name) {}
public ArrayList<HashMap<String, HashMapValue>> getNamedResource(String name) {}
public String getResource(int index) {}
public HashMap<String, HashMapValue> getIndexedResource(int index) {}
```
These methods are kinda broken. But they do work. The first uses the `plaintext` mode, and the second uses the `json` mode. The third uses the `plaintext` mode and the fourth uses the `json` mode.

#### <ins>Changing Resources</ins>
- There is one method available for changing a resource:
```java
public void changeResource(int index, String name, String value) {}
```
This method will change the resource at the specified index, by changing the name to the given name and the value to the given value.

#### <ins>Other Methods</ins>
- Some other useful methods:
```java
public void bindToFile(String filename) {}
public String getBoundFile() {}
public void setMode(String mode) {}
```
The `bindToFile()` method changes what file the instance can talk to.  
The `getBoundFile()` method returns what file the instance can talk to.  
The `setMode()` method changes what mode the instance is in (see [Modes](###Modes) above).
