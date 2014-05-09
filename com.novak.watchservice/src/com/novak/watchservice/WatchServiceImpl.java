package com.novak.watchservice;

import java.nio.file.Path;
import java.util.ArrayList;

import com.cbcx.service.IServiceProvider;
import com.cbcx.service.ServiceProviderLoader;

public class WatchServiceImpl extends IWatchService {
	
	private String pluginLocation;
	
	
	public WatchServiceImpl(Path pluginPath){
		super(pluginPath);
		
		pluginLocation = pluginPath.getFileName().toString();
	}
	
	@Override
	protected void action(Path path){
		
		ArrayList<IServiceProvider> provider = ServiceProviderLoader.getServiceProviderList("", pluginLocation);
		if (provider != null){
			for (IServiceProvider iProvider : provider) {
				System.out.println(iProvider.getServiceName());
			}
		}else{
			System.out.println("no plugin found");
		}
	}
		
}
