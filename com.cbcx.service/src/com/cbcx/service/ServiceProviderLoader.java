package com.cbcx.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.ServiceLoader;

public class ServiceProviderLoader {

	private static final String SEARCH_FILE_TYPE = ".jar";
	private String pluginLocation;
	
	public ServiceProviderLoader(String pluginLocation) {
		super();
		this.pluginLocation = pluginLocation;
	}
	
	public void writeOutServiceName(){
		
		ServiceLoader<IService> serviceProviderLoader = getServiceLoader();
		for (IService l_service : serviceProviderLoader) {
			IServiceProvider serviceProvider = l_service.getService("");
			if (serviceProvider != null){
				System.out.println(serviceProvider.getServiceName());
			}
	    }
		System.out.println();
	}
	
	public IServiceProvider getServiceProvider(String serviceName){
		
		return getServiceProvider(serviceName);
	}
	
	public static IServiceProvider getServiceProvider(String serviceName, String pluginLocation){
		
		ServiceLoader<IService> serviceProviderLoader = getServiceLoader(pluginLocation);
        for (IService l_service : serviceProviderLoader) {
			IServiceProvider serviceProvider = l_service.getService(serviceName);
			if (serviceProvider != null){
				return serviceProvider;
			}
	    }
		return null;
	}
	
	public static ArrayList<IServiceProvider> getServiceProviderList(String serviceName, String pluginLocation){
		
		ArrayList<IServiceProvider> list = new ArrayList<IServiceProvider>();
		ServiceLoader<IService> serviceProviderLoader = getServiceLoader(pluginLocation);
		if (serviceProviderLoader == null){
			return null;
		}
        for (IService l_service : serviceProviderLoader) {
			IServiceProvider serviceProvider = l_service.getService(serviceName);
			if (serviceProvider != null){
				list.add(serviceProvider);
			}
	    }
		return list;
	}
	
	private ServiceLoader<IService> getServiceLoader() {
		
		return getServiceLoader(pluginLocation);
	}
	
	private static ServiceLoader<IService> getServiceLoader(String pluginLocation) {
		ServiceLoader<IService> serviceProviderLoader = null;
		//get plugin location
        File location = new File(pluginLocation);
        File[] jarlist = location.listFiles(new FileFilter() {
            public boolean accept(File file) {
            	return file.getPath().toLowerCase().endsWith(SEARCH_FILE_TYPE);
            }
        });
        URL[] urls = new URL[jarlist.length];
        try {
	        for (int i = 0; i < jarlist.length; i++){
	            urls[i] = jarlist[i].toURI().toURL();
	        }
	        //get URLClassLoader
	        URLClassLoader ucl = new URLClassLoader(urls);
	        //get ServiceLoader
			serviceProviderLoader = ServiceLoader.load(IService.class, ucl);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return serviceProviderLoader;
	}
	
}
