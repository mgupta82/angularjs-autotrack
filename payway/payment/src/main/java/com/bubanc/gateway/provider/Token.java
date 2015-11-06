package com.bubanc.gateway.provider;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Token {
	
	public Token(){
		
	}
	
	public Token(String gateway,String orderId,String publisherId,String publisherUserId,String token){
		this.orderId = orderId;
		this.publisherId = publisherId;
		this.publisherUserId = publisherUserId;
		this.gateway = gateway;
		this.token = token;
	}
	
	@XmlElement(name="gateway",required=true)
	public String gateway;
	
	@XmlElement(name="orderId",required=true)
	public String orderId;	
	
	@XmlElement(name="publisherId",required=true)
	public String publisherId;
	
	@XmlElement(name="publisherUserId",required=true)
	public String publisherUserId;	
	
	@XmlElement(name="token",required=true)
	public String token;
	
}
