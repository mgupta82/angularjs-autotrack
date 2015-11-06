package com.bubanc.gateway.provider;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {
	
	public Set<Class<?>> getClasses(){
	Set<Class<?>> classes = new HashSet<Class<?>>();
	classes.add(TokenService.class);
	classes.add(Transaction.class);
	return classes;
	}

}
