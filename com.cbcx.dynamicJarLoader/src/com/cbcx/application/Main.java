package com.cbcx.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.cbcx.service.IServiceProvider;
import com.cbcx.service.ServiceProviderLoader;
import com.novak.watchservice.WatchServiceImpl;

public class Main {
	
	private static final String PLUGIN_LOCATION = "plugins";
	private static final Path PUGIN_PATH = Paths.get(System.getProperty("user.dir"),PLUGIN_LOCATION);
	
	public static void main(String[] args) throws IOException {
		
		//run watch service on plugin dir
		Thread watchService1 = new Thread(new WatchServiceImpl(PUGIN_PATH));
		watchService1.start();
		
		//write out serviceName in a cycle of 5s
		while(true){
			ArrayList<IServiceProvider> serviceProvider = ServiceProviderLoader.getServiceProviderList("", PLUGIN_LOCATION);
			if (serviceProvider != null){
				for (IServiceProvider iProvider : serviceProvider) {
					System.out.println(iProvider.getServiceName());
				}
			}else{
				System.out.println("no provider found. Waiting ...");
			}
			try {
			    Thread.sleep(5000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
    }
}
