$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateItemForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
		 url : "PowercutAPI",
		 type : type,
		 data : $("#formItem").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemSaveComplete(response.responseText, status);
		 }
		 });
		});

function onItemSaveComplete(response, status) {
	if (status == "success") {
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 	{
 		$("#alertSuccess").text("The record has been inserted successfully with the latest changes!");
 		$("#alertSuccess").show();
 		$("#divItemsGrid").html(resultSet.data);
 	} else if (resultSet.status.trim() == "error") {
 		$("#alertError").text(resultSet.data);
 		$("#alertError").show();
 	}
 	} else if (status == "error") {
 		$("#alertError").text("Error while inserting the record!");
 		$("#alertError").show();
 	} else {
 		$("#alertError").text("Error while inserting the record!");
 		$("#alertError").show();
 	}
 		$("#hidItemIDSave").val("");
 		$("#formItem")[0].reset();
}

// UPDATE==========================================
//Update Records in the DB
$(document).on("click", ".btnUpdate", function(event) {
	$("#lineNo").prop('disabled', true);     
	$("#areaNo").prop('disabled', true);     
	$("#areaName").prop('disabled', true);     
	$("#startTime").prop('disabled', true); 
	$("#endTime").prop('disabled', true);     
	$("#date").prop('disabled', false);  
	$("#reason").prop('disabled', false);

 	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#lineNo").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#areaNo").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#areaName").val($(this).closest("tr").find('td:eq(3)').text());
 	$("#startTime").val($(this).closest("tr").find('td:eq(4)').text());
 	$("#endTime").val($(this).closest("tr").find('td:eq(5)').text());
 	$("#date").val($(this).closest("tr").find('td:eq(6)').text());
 	$("#reason").val($(this).closest("tr").find('td:eq(7)').text());
 	
});

//Delete Records from the DB
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
	{
 		url : "PowercutAPI",
 		type : "DELETE",
 		data : "ID=" + $(this).data("ID"),
 		dataType : "text",
 		complete : function(response, status)
 	{
 		onItemDeleteComplete(response.responseText, status);
 	}
 	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success") {
 			$("#alertSuccess").text("The record has been deleted successfully!");
 			$("#alertSuccess").show();
 			$("#divItemsGrid").html(resultSet.data);
 		} else if (resultSet.status.trim() == "error") {
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
 	} else if (status == "error") {
		$("#alertError").text("Error while deleting the record!");
 		$("#alertError").show();
 	} else {
 		$("#alertError").text("Unknown error while deleting the record!");
 		$("#alertError").show();
 	}
}



// CLIENT-MODEL================================================================
function validateItemForm()
{
if ($("#lineNo").val().trim() == "")
 {
 return "Missing Line Number .";
 }
if ($("#areaNo").val().trim() == "")
 {
 return "Missing Area Number.";
 }

if ($("#startTime").val().trim() == "")
 {
 return "Please Enter Start Time of the Powercut";
 }
if ($("#endTime").val().trim() == "")
{
return "Please Enter End Time of the Powercut";
}

var tmpDate = $("#date").val().trim();
if (!$.isNumeric(tmpDate))
 {
 return "Invalid Date format";
 }

return true;
}

