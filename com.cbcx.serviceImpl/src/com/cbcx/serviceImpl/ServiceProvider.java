package com.cbcx.serviceImpl;

import com.cbcx.service.IService;

public class ServiceProvider extends IService {
	
	public String getServiceName() {
		return "I'm service ServiceProvider testing WatchService 2 asdf";
	}
	
	public String getServiceName2() {
		return "I'm service ServiceProvider 007 Blond 2 asdf";
	}
	
	@Override
	public ServiceProvider getService(String serviceName) {
		return new ServiceProvider();
	}

}
