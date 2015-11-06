package com.bubanc.gateway.provider;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servlet implementation class HotlinkPayment
 */
public class HotlinkPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SECURE_SECRET = "3BEE43F838459256513519D399446332";
	private static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};		

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

		StringBuffer redirectURL = new StringBuffer();
		redirectURL.append("http://localhost:9080/hotlink/PaymentProvider.html").append('?'); //host and port will change at runtime
		appendQueryFields(redirectURL, fields);
		response.sendRedirect(redirectURL.toString());
	}
	
	private static String getSHA(Map fields){
		// create a list and sort it
		List<String> fieldNames = new ArrayList<String>(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);
		buf.append("asdadsadsa23423sadx"); //Token returned by Maxis Top up portal

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
    private static String hex(byte[] input)
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

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
