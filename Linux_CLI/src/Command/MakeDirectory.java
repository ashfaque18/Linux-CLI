package Command;



import Resources.Folder;
import Resources.Path;


public class MakeDirectory {
	private Folder currentFolder;
	
	public MakeDirectory(Folder currentFolder) {
		this.currentFolder = currentFolder;
	}
	
	public String makeDirectories(String[] directory) {
		
		
		if (directory.length == 1)
			return "mkdir: missing operand(s)";
		
		String message = "mkdir: ";
		
		for (int i = 1; i < directory.length; i++) {
			Path path = new Path(currentFolder, directory[i]);
			

			
			boolean canCreateFolder = path.isValidPath();
			
			if (canCreateFolder) {  
				Folder navigatingFolder = path.getNavigatingFolder();
				String lastFolderName = path.getLastFolderName();
				if (navigatingFolder.containsFolder(lastFolderName)) {  
					message = message + fileExistsError(directory[i]) + "\n";
				}
				else {
					navigatingFolder.createFolder(lastFolderName);   
				}
			}
			else {
				message = message + fileDoesNotExistError(directory[i]) + "\n";
			}
		}
		
		return message;
	}
	
	private String fileExistsError(String name) {
		return "cannot create directory '" + name + "': File exists";
	}
	
	private String fileDoesNotExistError(String name) {
		return "cannot create directory '" + name + "': No such file or directory";
	}
}

