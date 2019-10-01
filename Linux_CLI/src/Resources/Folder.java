package Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;



public class Folder implements Iterable<Folder>{
	private Folder parent;
	private TreeMap<String, Folder> directory;
	private String name;
	
	public Folder(Folder parent, String name) {	
		this.parent = parent;
		this.name = name;
		directory = new TreeMap<>();
	}
	
	public void createFolder(String name) {
		this.directory.put(name, new Folder(this, name));
	}
	
	public boolean containsFolder(String name) {
		if (this.directory.containsKey(name))
			return true;
		
		return false;
	}
	
	public void removeFolder(String name) {
		this.directory.remove(name);
	}
	
	public String getPresentWorkingDirectory() {
		ArrayList<String> folder = new ArrayList<>();
		Folder currentFolder = this;
		
		while (currentFolder != null) {
			folder.add(currentFolder.getName());
			currentFolder = currentFolder.getParent();
		}
		
		String presentWorkingDirectory = folder.get(folder.size() - 1);
		
		if (folder.size() >= 2) {
			presentWorkingDirectory = presentWorkingDirectory + folder.get(folder.size() - 2);  // To take care of /
		}
		
		for (int i = folder.size() - 3; i >= 0; i--) {
			presentWorkingDirectory = presentWorkingDirectory + "/" + folder.get(i); 
		}
		
		
		
		return presentWorkingDirectory;
	}
	
	public Folder getChildFolder(String name) {
		return directory.get(name);   
	}
	
	public boolean isChildOf(Folder parentFolder) {
		Folder currentFolder = this;
		
		while (currentFolder != null) {
			if (currentFolder == parentFolder)
				return true;
			else
				currentFolder = currentFolder.getParent();
		}
		
		return false;
	}
	
	public String getChildFolders() {
		Collection<Folder> folders = directory.values();
		Iterator<Folder> iterator = folders.iterator();
		
		String message = "";
		
		while(iterator.hasNext()) {
			message = message + iterator.next().name + " ";
		}
		
		return message;
	}
	
	public Iterator<Folder> getAllChildFolders() {
		Collection<Folder> folders = directory.values();
		Iterator<Folder> iterator = folders.iterator();
		
		return iterator;
	}
	
	public String getName() {
		return name;
	}
	
	public Folder getParent() {
		return parent;
	}
	
	public Folder getRoot() {
		Folder currentFolder = this;
		Folder root = currentFolder;
		
		while(currentFolder != null) {
			root = currentFolder;
			currentFolder = currentFolder.getParent();
		}
		
		return root;
	}

	
	public Iterator<Folder> iterator() {
		
		Collection<Folder> folders = directory.values();
		Iterator<Folder> iterator = folders.iterator();
		
		return iterator;
	}
}
