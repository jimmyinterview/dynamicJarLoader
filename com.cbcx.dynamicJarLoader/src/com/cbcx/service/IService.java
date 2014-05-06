package com.cbcx.service;

import com.cbcx.serviceImpl.ServiceProvider;

public abstract class IService {
	public abstract ServiceProvider getService(String serviceName);
}
