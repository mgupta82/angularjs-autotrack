package com.bubanc.gateway.provider;

import java.security.SecureRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/token")
public class TokenService {

	@GET
	@Produces("application/xml")
	public Token getToken(@QueryParam("gateway") String gateway,@QueryParam("orderId") String orderId,@QueryParam("publisherId") String publisherId,@QueryParam("publisherUserId") String publisherUserId){
		byte[] nonce = new byte[16];
		try{
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(nonce);
		}catch(java.security.NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		//return "{requestid:"+requestId+",gateway="+gateway+",token:"+nonce+"}";
		return new Token(gateway,orderId,publisherId,publisherUserId,nonce.toString());
	}	
	
}
