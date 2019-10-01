package Resources;

public class Path {
	private String directory;
	private Folder currentFolder;
	private Folder navigatingFolder;
	private String lastFolderName;
	
	public Path(Folder currentFolder, String directory) {
		this.currentFolder = currentFolder;
		this.directory = directory;
	}
	
	public boolean isValidPath() {	
		boolean pathExists = true;
		String[] path = directory.split("/");	
		
		if (path[0].equals("")) {	
			navigatingFolder = currentFolder.getRoot();	

			for (int j = 1; j < path.length - 1; j++) {	
				if (!navigatingFolder.containsFolder(path[j])) {
					pathExists = false;
					break;
				}
				else {
					navigatingFolder = navigatingFolder.getChildFolder(path[j]);  
				}
			}
			

		}
		else {	
			navigatingFolder = currentFolder;

			
			for (int j = 0; j < path.length - 1; j++) {  
				if (!navigatingFolder.containsFolder(path[j])) {
					pathExists = false;
					break;
				}
				else {
					navigatingFolder = navigatingFolder.getChildFolder(path[j]);
				}
			}
			
		}
		
		lastFolderName = path[path.length - 1];
		
		return pathExists;
	}
	
	public Folder getNavigatingFolder() {		
		return navigatingFolder;
	}
	
	public String getLastFolderName() {		
		return lastFolderName;
	}
}
