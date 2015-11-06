package com.bubanc.gateway.provider;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request")
public class ConfirmRequest {
	
	public ConfirmRequest(){
		
	}
	
	public ConfirmRequest(String gateway,String orderId,Double amount,String customerType,String transactionStatus,String failureReason,String userEmailId,Map<String, String> group,String secureHash){
		this.gateway = gateway;
		this.orderId = orderId;
		this.amount = amount;
		this.customerType = customerType;
		this.transactionStatus = transactionStatus;
		this.failureReason = failureReason;
		this.userEmailId = userEmailId;
		this.group = group;
		this.secureHash = secureHash;
	}	
	
	@XmlElement(name="gateway",required=true)
	public String gateway;
	
	@XmlElement(name="orderId",required=true)
	public String orderId;
	
	@XmlElement(name="amount",required=true)
	public Double amount;	
	
	@XmlElement(name="customerType",required=true)
	public String customerType;
	
	@XmlElement(name="transactionStatus",required=true)
	public String transactionStatus;
	
	@XmlElement(name="failureReason")
	public String failureReason;
	
	@XmlElement(name="userEmailId")
	public String userEmailId;
	
	@XmlElement(name="group", required=false)
	public Map<String, String> group;
	
	@XmlElement(name="secureHash",required=true)
	public String secureHash;

}
