package com.bubanc.gateway.provider;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

@Path("/transaction")
public class Transaction {
	
	@Context
	UriInfo uriInfo;
	
	@PUT
	@Consumes("application/xml")
	public Response confirm(ConfirmRequest request){
		
		URI uri = uriInfo.getAbsolutePath();
		Response r;
		r = Response.created(uri).build();
		return r;
	}

}
