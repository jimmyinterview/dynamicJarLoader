package com.cbcx.serviceImpl;

import com.cbcx.service.IService;
import com.cbcx.service.IServiceProvider;

public class ServiceProviderImpl2 extends IService implements IServiceProvider {

	@Override
	public String getServiceName() {

		return "I'm service ServiceProvider 1 testing WatchService .... 2";
	}

	@Override
	public IServiceProvider getService(String serviceName) {
		
		return new ServiceProviderImpl2();
	}

}
