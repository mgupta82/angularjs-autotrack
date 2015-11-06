package com.maxis.gateway.provider;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GatewayUtil {

	static final String SECURE_SECRET = "3BEE43F838459256513519D399446332";
	static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};	
    
	public static String hashAllFields(Map fields)
	{
		// create a list and sort it
		List<String> fieldNames = new ArrayList<String>(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);
		buf.append("asdadsadsa23423sad"); //Token returned for this request id

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

		// create the md5 hash and UTF-8 encode it
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
	
}
