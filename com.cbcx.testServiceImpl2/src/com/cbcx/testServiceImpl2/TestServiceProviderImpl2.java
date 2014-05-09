package com.cbcx.testServiceImpl2;

import com.cbcx.service.IService;
import com.cbcx.service.IServiceProvider;

public class TestServiceProviderImpl2 extends IService implements IServiceProvider {

	@Override
	public String getServiceName() {

		return "I'm service TestServiceProviderImpl2 2 testing WatchService .... 2 2 ";
	}

	@Override
	public IServiceProvider getService(String serviceName) {
		
		return new TestServiceProviderImpl2();
	}

}
