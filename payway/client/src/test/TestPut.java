package test;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.bubanc.gateway.provider.ConfirmRequest;


public class TestPut {
	
	public static void main(String args[]){
		ClientConfig configuration = new ClientConfig();
		configuration.property(ClientProperties.CONNECT_TIMEOUT, 1000);
		configuration.property(ClientProperties.READ_TIMEOUT, 1000);		
		
		Client client = ClientBuilder.newClient(configuration);
		
		//WebTarget webTarget = client.target("http://localhost:8080/payment/rest/token?requestid=1234&gateway=bubanc");
		
		WebTarget target = client.target("http://localhost:8080/payment/rest").path("transaction");	
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("SomeStatus", "Success");
		m.put("SomeKey", "SomeValue");
		
		ConfirmRequest confirmRequest = new ConfirmRequest("bubanc","123",new Double(23),"P","SUCCESS","","",m,"aasda222");
		
		 try {
	            JAXBContext context = JAXBContext.newInstance(ConfirmRequest.class);
	            Marshaller mr = context.createMarshaller();
	            //for pretty-print XML in JAXB
	            mr.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	 
	            // Write to System.out for debugging
	            mr.marshal(confirmRequest, System.out);

	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }

		
		//ConfirmRequest request = target.request(MediaType.APPLICATION_XML).put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),ConfirmRequest.class);
		
		
		
		Response response = target.request().put(Entity.entity(confirmRequest, MediaType.APPLICATION_XML));
		
		System.out.println("HTTP Status:"+response.getStatus());
		
		//java.net.ConnectException: Connection refused: connect
	}	

}
