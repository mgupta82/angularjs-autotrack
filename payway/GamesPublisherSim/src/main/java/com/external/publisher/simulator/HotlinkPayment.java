package com.external.publisher.simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


/**
 * Servlet implementation class HotlinkPayment
 */
public class HotlinkPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String tokenUrl;
	private String topupUrl;
	private String securePin;	
	private static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};	
	
	//initialize property file
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Properties prop = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file config.properties not found in the classpath");
			}
			tokenUrl = prop.getProperty("maxis.token.url");
			topupUrl = prop.getProperty("maxis.topup.url");
			securePin = prop.getProperty("maxis.secret.pin");
			System.out.println("tokenUrl:"+tokenUrl);
			System.out.println("redirectUrl:"+topupUrl);
			System.out.println("securePin:"+securePin);
		}catch(IOException ioe){
			throw new ServletException(ioe);
		}
		
	}
	
	//Call REST service for getting Token.	
	private String requestToken(){
		try
        {
			System.out.println("Calling Rest Service:"+tokenUrl);
            URL url = new URL(tokenUrl+"?requestid=1234&gateway=bubanc");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");
 
            if (conn.getResponseCode() != 200)
            {
                //throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            	return "dummytoken";
            }
 
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String apiOutput = br.readLine();
            System.out.println(apiOutput);
            conn.disconnect();
 
            JAXBContext jaxbContext = JAXBContext.newInstance(com.maxis.gamingportal.auth.Token.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.maxis.gamingportal.auth.Token token = (com.maxis.gamingportal.auth.Token) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
            return token.token;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
       return null;
	}		
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("RequestId","123456");
		fields.put("Gateway","Bubanc");
		//Any other fields if required
		//long lValue = 1*100;
		//fields.put("amount",lValue+"");

		String secureHash = getSHA(fields);
		fields.put("SecureHash", secureHash);
		System.out.println("SecureHash:"+secureHash);
		
		StringBuffer redirectURL = new StringBuffer();
		redirectURL.append(topupUrl).append('?');
		appendQueryFields(redirectURL, fields);
		System.out.println("Redirecting to URL:"+redirectURL);
		response.sendRedirect(redirectURL.toString());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	
	private String getSHA(Map fields){
		// create a list and sort it
		List<String> fieldNames = new ArrayList<String>(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(securePin);
		buf.append(requestToken()); //Token returned by Maxis Top up portal

		// iterate through the list and add the remaining field values
		Iterator<String> itr = fieldNames.iterator();

		while (itr.hasNext())
		{
		    String fieldName = (String) itr.next();
		    String fieldValue = (String) fields.get(fieldName);
		    if ((fieldValue != null) && (fieldValue.length() > 0))
		    {
				buf.append(fieldValue);
		    }
		}
		MessageDigest md = null;
		byte[] ba = null;

		// create the SHA hash and UTF-8 encode it
		try
		{
		    md = MessageDigest.getInstance("SHA-256");
		    ba = md.digest(buf.toString().getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return hex(ba);		
	}
	
	   /**
     * Returns Hex output of byte array
     */
    private String hex(byte[] input)
    {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++)
        {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }		

    private static void appendQueryFields(StringBuffer buf, Map<String, String> fields)
    {
        List<String> fieldNames = new ArrayList<String>(fields.keySet());
        Iterator<String> itr = fieldNames.iterator();
 		try
		{
		   while (itr.hasNext())
			{
				String fieldName = (String)itr.next();
				String fieldValue = fields.get(fieldName).toString();
				if(fieldValue != null)
				{
					// append the URL parameters
					buf.append(URLEncoder.encode(fieldName));
					buf.append('=');
					buf.append(URLEncoder.encode(fieldValue.toString()));
				}
				// add a '&' to the end if we have more fields coming.
				if (itr.hasNext())
				{
					buf.append('&');
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    

	


}
