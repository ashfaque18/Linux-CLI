package Command;

import Resources.Folder;
import Resources.Path;


public class ChangeDirectory {
	private Folder currentFolder;
	private String[] directory;
	private Folder lastFolder;
	
	public ChangeDirectory(Folder currentFolder, String[] directory) {
		this.currentFolder = currentFolder;
		this.directory = directory;
		this.lastFolder = currentFolder;
	}
	
	public String changeDirectory() {
		String message = "";
		
		if (directory.length == 1) {
			lastFolder = currentFolder.getRoot(); 	// switch to root
			return message;
		}
		
		if (directory.length > 2) {
			message = "cd: too many arguments";
			return message;
		}
		
		if (directory[1].equals("..")) {
			if (currentFolder.getParent() == null) {
				lastFolder = currentFolder;
			}
			else {
				lastFolder = currentFolder.getParent();
			}
			return message;
		}
		
		
		Path path = new Path(currentFolder, directory[1]);
		
		boolean isValidPath = path.isValidPath();
		
		if (isValidPath) {
			Folder navigatingFolder = path.getNavigatingFolder();
			String lastFolderName = path.getLastFolderName();
			if (!navigatingFolder.containsFolder(lastFolderName)) {  
				message = message + fileDoesNotExistError(directory[1]);
			}
			else {
				lastFolder = navigatingFolder.getChildFolder(lastFolderName);
			}
		}
		else {
			message = message + fileDoesNotExistError(directory[1]);
		}
		
		return message;
	}
	
	private String fileDoesNotExistError(String name) {
		return "cd: " + name + ": No such file or directory";
	}
	
	public Folder getLastFolder() {
		return lastFolder;
	}
	

}