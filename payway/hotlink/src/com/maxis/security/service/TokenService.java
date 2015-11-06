package com.maxis.security.service;

import java.security.SecureRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/token/{requestid}")
public class TokenService {
	
	@GET
	@Produces("application/json")
	public String getToken(@PathParam("requestid") String requestId){
		byte[] nonce = new byte[16];
		try{
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(nonce);
		}catch(java.security.NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return "{requestid:"+requestId+",token:"+nonce+"}";
	}

}
