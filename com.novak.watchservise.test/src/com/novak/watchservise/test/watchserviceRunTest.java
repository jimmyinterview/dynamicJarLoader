package com.novak.watchservise.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.novak.watchservice.LoaderWatchService;

public class watchserviceRunTest {
	
	public static void main(String[] args) throws IOException {
		
		Path pluginPath = Paths.get(System.getProperty("user.dir", "plugin"));
		LoaderWatchService loaderWatchService = new LoaderWatchService();
		loaderWatchService.run(pluginPath);
		
	}
}
