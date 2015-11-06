package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class TestGet {
	
	public static void main(String args[]){
	       try
	        {
	            URL url = new URL("http://localhost:8080/payment/rest/token?requestid=1234&gateway=bubanc");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/xml");
	 
	            if (conn.getResponseCode() != 200)
	            {
	                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	            }
	 
	            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	            String apiOutput = br.readLine();
	            System.out.println(apiOutput);
	            conn.disconnect();
	 
	            JAXBContext jaxbContext = JAXBContext.newInstance(Token.class);
	            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	            Token token = (Token) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
	 
	            System.out.println(token.token);
	             
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	}	

}
