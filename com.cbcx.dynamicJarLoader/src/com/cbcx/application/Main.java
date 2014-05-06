package com.cbcx.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Path pluginPath = Paths.get(System.getProperty("user.dir"),"plugins");
		
		ServiceProviderLoader serviceProviderLoader = new ServiceProviderLoader();
		serviceProviderLoader.run(pluginPath);
    }
	
}
