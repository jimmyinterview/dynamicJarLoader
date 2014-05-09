package com.cbcx.serviceImpl;

import com.cbcx.service.IService;
import com.cbcx.service.IServiceProvider;

public class ServiceProviderImpl extends IService implements IServiceProvider {
	
	@Override
	public String getServiceName() {
		
		return "I'm service ServiceProvider 1 testing WatchService .... 1";
	}

	@Override
	public IServiceProvider getService(String serviceName) {

		return new ServiceProviderImpl();
	}
}
