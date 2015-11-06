package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		String clientID = "AYTG6RC4PRFZBRrvivl-B7NwhHE-50Jp6A6WsBHERg9zj53LaLP7YX3fi4Yd";
		String clientSecret = "EHVzxRAQ7hGk_IbPdHCJF5cIEHENV3Vs-riIB1ZiuELeQ089z7-qN94lUGCa";
    	try{
    		URL url = new URL("https://api.sandbox.paypal.com/v1/oauth2/token");
    		//URL url = new URL("https://www.google.com");
    		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
    		String authStr = clientID + ":" + clientSecret;
    		String authEncoded = Base64.encodeBase64String(authStr.getBytes()) ;
            connection.setRequestMethod("GET");
            //Header (-H)
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Language" ,"en_US" );
            connection.setRequestProperty("content-type" ,"application/x-www-form-urlencoded" );
            //Basic Auth -u
            connection.setRequestProperty  ("Authorization", "Basic " + authEncoded);
            connection.setDoOutput(true);
            //Data -d
    		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
    		out.write("grant_type=client_credentials"); //json input
    		out.close();
    		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		String line = null;
    		while ((line = br.readLine()) != null) {
    			System.out.println(line);
			}

    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
}
