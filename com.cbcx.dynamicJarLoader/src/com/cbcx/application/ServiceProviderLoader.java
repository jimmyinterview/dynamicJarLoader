package com.cbcx.application;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ServiceLoader;

import com.cbcx.service.IService;
import com.cbcx.serviceImpl.ServiceProvider;
import com.novak.watchservice.IWatchService;

public class ServiceProviderLoader extends IWatchService {
	
	private static final String PLUGIN_LOCATION = "plugins";
	private static final String SEARCH_FILE_TYPE = ".jar";

	@Override
	protected void action(Path path) {
		
		ServiceProvider provider;
		try {
			provider = getServiceProvider(path.toString());
			if (provider != null){
	        	System.out.println(provider.getServiceName());
	        }else{
	        	System.out.println("no plugin found");
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private ServiceProvider getServiceProvider(String serviceName) throws MalformedURLException{
		
		if (serviceName == null || serviceName.isEmpty()){
			return null;
		}
		
		//get plugin location
        File location = new File(PLUGIN_LOCATION);
        File[] jarlist = location.listFiles(new FileFilter() {
            public boolean accept(File file) {
            	return file.getPath().toLowerCase().endsWith(SEARCH_FILE_TYPE);
            }
        });
        URL[] urls = new URL[jarlist.length];
        for (int i = 0; i < jarlist.length; i++){
            urls[i] = jarlist[i].toURI().toURL();
        }
        //get URLClassLoader
        URLClassLoader ucl = new URLClassLoader(urls);
        //get ServiceLoader
        ServiceLoader<IService> serviceProviderLoader =	ServiceLoader.load(IService.class, ucl);
        
        for (IService l_service : serviceProviderLoader) {
			ServiceProvider serviceProvider = l_service.getService(serviceName);
			if (serviceProvider != null){
				return serviceProvider;
			}
	    }
		return null;
	}

}
