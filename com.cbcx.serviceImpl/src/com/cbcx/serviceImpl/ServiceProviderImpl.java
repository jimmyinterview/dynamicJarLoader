package com.cbcx.serviceImpl;

import com.cbcx.service.IService;
import com.cbcx.service.ServiceProvider;

public class ServiceProviderImpl extends IService implements ServiceProvider {
	
	@Override
	public String getServiceName() {
		
		return "I'm service ServiceProvider testing WatchService 555";
	}

	@Override
	public ServiceProvider getService(String serviceName) {

		return new ServiceProviderImpl();
	}
}
