<%@page import="com.Powercut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title> Powercut Scheduling Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Powercut.js"></script>
</head>

<body bgcolor = "#A2A1A1">
<div class="container" >
	<div class="row">
		<div class="col-30">
		<br><br>
		<center>
		<h1 style="font-size:34px; color:black; font-weight:bold;"> Power Cut Scheduling</h1>
		</center>
		<br><br>
			<h3 style="font-size:16px; color:black; "> Add Power cut schedule with details </h3>
		<br>
		<form id="formItem" name="formItem">
	 		<h2 style="font-size:18px; color:black;">Line Number:</h2>
	 		<input style="border-color: black; border-width: 2px" id="lineNo" name="lineNo" type="text" class="form-control form-control-sm">
	 		<br> 
	 		
	 		<h2 style="font-size:18px; color:black;">Area Number:</h2>
	 		<input style="border-color: black; border-width: 2px" id="areaNo" name="areaNo" type="text" class="form-control form-control-sm">
	 		<br> 
	 		
	 		
			<div class="input-group input-group-sm mb-3">
				<div class="input-group-prepend">
		 			<span class="input-group-text" id="areaName">Area Name:</span>
				</div>
				<select id="areaName" name="recipientDept">
					<option value="0">--Select Area--</option>
					<option value="Customer Service Department">Colombo</option>
					<option value="Power Cut Department">Gampaha</option>
					<option value="Billing Department">Kalutara</option>
					<option value="New Connections Department">Ja-ela</option>
					<option value="New Connections Department">Mount Lavinia</option>
				</select>
			</div>
			
	 		<h2 style="font-size:17px; color:black;">Start Time</h2>
	 		<input style="border-color: black; border-width: 2px; width: 120px" id="startTime" name="startTime" type="time" 
	 		class="form-control form-control-sm">
	 		<br> 
	 		<h2 style="font-size:17px; color:black;">End Time:</h2>
	 		<input style="border-color: black; border-width: 2px; width: 120px" id="endTime" name="endTime" type="time"
	 		class="form-control form-control-sm">
	 		<br> 
	 		<h2 style="font-size:17px; color:black;">Date:</h2>
	 		<input style="border-color: black; border-width: 2px; width: 120px" id="date" name="date" type="date"
	 		class="form-control form-control-sm">
	 		<br> 
	 		<br> 
	 		<h2 style="font-size:17px; color:black;">Reason:</h2>
	 		<input style="border-color: black; border-width: 2px; height: 50px" id="reason" name="reason" type="text"
			class="form-control form-control-sm">
	 		<br><br>
	 		<center> <input style="font-size:17px; width: 200px; background-color: #00cc00; border-color:#00cc00; color: black" id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary"></center>
	 		<br><br>
	 		<input type="hidden" id="hidItemIDSave"
	 		name="hidItemIDSave" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
 <%
 Powercut powercutObj = new Powercut();
 out.print(powercutObj.readPowercut());
 %>
</div>
</div> </div> </div>
</body>
</html>