package com.maxis.gateway.provider;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentProvider
 */
@WebServlet("/PaymentProvider.html")
public class PaymentProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fields = new HashMap<String, String>();
		String vpcSecureHash = "";
		for (Enumeration<String> enumParam = request.getParameterNames(); enumParam.hasMoreElements();)
		{
			String fieldName = (String) enumParam.nextElement();
			String fieldValue = request.getParameter(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0))
			{
				if(fieldName.equalsIgnoreCase("SecureHash")){
					vpcSecureHash = fieldValue;
				}else{
					fields.put(fieldName, fieldValue);
				}
				System.out.println("fieldName:"+fieldName);
				System.out.println("fieldValue:"+fieldValue);
			}
		}
		String secureHash = GatewayUtil.hashAllFields(fields);
		
		if(secureHash.equalsIgnoreCase(vpcSecureHash)){
			System.out.println("Valid Hash");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			System.out.println("Invalid Hash");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
