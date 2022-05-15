package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




@SuppressWarnings("serial")
@WebServlet("/PowercutAPI")
public class PowercutAPI extends HttpServlet
{
	
Powercut powercutObj = new Powercut();
 
protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			String output = powercutObj.insertPowercut(request.getParameter("lineNo"),
			request.getParameter("areaNo"),
			request.getParameter("areaName"),
			request.getParameter("startTime"),
			request.getParameter("endTime"),
			request.getParameter("date"),
			request.getParameter("reason"));
			response.getWriter().write(output);
			}

	
//Convert request parameters to a Map
@SuppressWarnings("rawtypes")
private static Map getParasMap(HttpServletRequest request)
{
Map<String, String> map = new HashMap<String, String>();
try
{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
		scanner.close();
		
	String[] params = queryString.split("&");
	for (String param : params)
		{
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
		}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}


protected void doPut(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException
		{
		 @SuppressWarnings("rawtypes")
		Map paras = getParasMap(request);
		String output = powercutObj.updatePowercut(paras.get("hidItemIDSave").toString(),
				paras.get("lineNo").toString(),
				paras.get("areaNo").toString(),
				paras.get("areaName").toString(),
				paras.get("startTime").toString(),
				paras.get("endTime").toString(),
				paras.get("date").toString(),
				paras.get("reason").toString());
				
		response.getWriter().write(output);
		}
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException
		{
		 @SuppressWarnings("rawtypes")
		Map paras = getParasMap(request);
		String output = powercutObj.deletePowercut(paras.get("ID").toString());
		response.getWriter().write(output);
		}

}

	
	


