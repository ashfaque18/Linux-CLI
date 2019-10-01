import java.util.*;

import Command.ChangeDirectory;
import Command.MakeDirectory;
import Command.RemoveDirectory;
import Resources.Folder;



public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		
		
		sc = new Scanner(System.in);
		
		Folder parentOfRoot = null;
		Folder root = new Folder(parentOfRoot, "~");
		Folder currentFolder = root;  
		
		System.out.println("Ashfaque CLI: ");
		
		while (true) {
			String presentWorkingDirectory = currentFolder.getPresentWorkingDirectory();
			System.out.print(presentWorkingDirectory + "#");   
			
			String input = sc.nextLine();

			
			input = input.trim().replaceAll(" +", " ");		
			
			String[] keyword = input.split(" ");
			String command = keyword[0];
			
			if (command.equals("cd")) {
				ChangeDirectory cd = new ChangeDirectory(currentFolder, keyword);
				String message = cd.changeDirectory();
				if (message.equals("")) {
					currentFolder = cd.getLastFolder();
				}
				else {
					System.out.println(message);
				}
			}
			else if (command.equals("pwd")) {
				System.out.println("PATH:" + presentWorkingDirectory);
			}
			else if (command.equals("ls")) {
				String message = currentFolder.getChildFolders();
				System.out.println(message);
			}
			else if (command.equals("mkdir")) {
				MakeDirectory mkdir = new MakeDirectory(currentFolder);
				String message = mkdir.makeDirectories(keyword);
				
				if (!message.equals("mkdir: "))
					System.out.println(message);
			}
			else if (command.equals("rm")) {
				RemoveDirectory rm = new RemoveDirectory(currentFolder);
				String message = rm.removeDirectories(keyword);
				
				if (!message.equals("rm: ")) {
					System.out.println(message);
				}
				else {
					currentFolder = rm.getLastFolder();
				}
			}
			else if (command.equals("session")) {
				if (keyword[1].equals("clear")) {
					root = new Folder(parentOfRoot, "/");
					currentFolder = root;
				}
				else {
					System.out.println("unknown option with session");
				}
			}
			else if (command.equals("exit")) {
				break;
			}
			else {
				System.out.println(command + ": command not found");
			}
		}
		
		
	}

}
