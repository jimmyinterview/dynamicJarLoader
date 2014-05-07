package com.novak.watchservice;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Timestamp;

public abstract class IWatchService {
	
	public void run(Path path) throws IOException{
		java.util.Date date= new java.util.Date();
		
		// Sanity check - Check if path is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(path, "basic:isDirectory", LinkOption.NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}
		
		System.out.println("Watching path: " + path);
		
		// We obtain the file system of the Path
		FileSystem fs = path.getFileSystem ();
		
		// We create the new WatchService using the new try() block
		try(WatchService service = fs.newWatchService()) {
			
			// register the path to the service and add events to watch
			path.register(service, 
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			
			// Start the infinite polling loop
			WatchKey key = null;
			while(true) {
				key = service.take();
				
				// Dequeueing events
				Kind<?> kind = null;
				for(WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event and add action
					kind = watchEvent.kind();
					if (StandardWatchEventKinds.OVERFLOW == kind) {
						continue; //loop
					} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println(new Timestamp(date.getTime()) + ": New path created: " + newPath);
						action(newPath);
					} else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
						Path modPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println(new Timestamp(date.getTime()) + ": Modified path: " + modPath);
						action(modPath);
					} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("New path created: " + newPath);
						action(newPath);
					} else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
						Path modPath = ((WatchEvent<Path>) watchEvent).context();
						System.out.println("Modified path: " + modPath);
						action(modPath);
					}
				}
				
				if(!key.reset()) {
					break; //loop
				}
			}
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	protected abstract void action(Path path);
		
}
