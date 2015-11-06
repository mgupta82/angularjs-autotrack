package com.maxis.security.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/secure/")
public class MaxisApplication extends Application {
	
	/*public Set<Class<?>> getClasses(){
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TokenService.class);
		return classes;
	}*/

}
