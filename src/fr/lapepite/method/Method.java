package fr.lapepite.method;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class Method {
	
public static HashMap<String, String> getParameters (HttpServletRequest request) {
		
		Enumeration<String> listParameters = request.getParameterNames();
		
		HashMap<String, String> parametersMap = new HashMap<>();
		
		while (listParameters.hasMoreElements()) {
			
			String parameterName = listParameters.nextElement();
			
			System.out.println(parameterName);
			
			String string = (String) request.getParameter(parameterName);
			
			parametersMap.put(parameterName, string);
			
		}
		
		return parametersMap;
		
	}

}
