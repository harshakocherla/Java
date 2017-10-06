<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BCJ-helpdesk</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- let's add tag srping:url -->
<link rel="icon"
	href="<c:url value="/resources/theme1/css/icon3.png" />" type="image">
<link href="<c:url value="/resources/theme1/css/BCJhelpDesk.css" />" rel="stylesheet">

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/redmond/jquery-ui.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.14.0/css/ui.jqgrid.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.14.0/jquery.jqgrid.min.js"></script>
<script type='text/javascript'>

jQuery(document).ready(function() {
$("#jqGrid").jqGrid({
	url : "displayTickets",
	datatype :"json",
	mtype : 'POSt',
		colNames : ['Ticket Id', 'category', 'subcategory', 'description', 'status', 'comments', 'ticket number'],
		colModel : [{
			name: 'ticketId',
			index:'ticketId',
			width:60
		},
		{
			name: 'category',
			index:'category',
			width:60
		},
		{
			name: 'subcategory',
			index:'subcategory',
			width:60
		},
		{
			name: 'description',
			index:'description',
			width:200
		},
		{
			name: 'stat',
			index:'stat',
			width:60
		},
		{
			name: 'comments',
			index:'comments',
			width:200
		},
		{
			name: 'ticketNumber',
			index:'ticketNumber',
			width:60
		}],
		
		loadonce : true,
		width : 810,
		height: 200,
		rowNum : 10,
		pager : '#jqGridPager',
		rowList : [ 10, 20, 30 ],
		viewrecords : true,
		gridview : true,
		caption : 'My Tickets',
		jsonReader : {
			repeatitems : false,
			},
		
	});
	jQuery("#jqGrid").jqGrid('navGrid', '#jqGridPager',{edit:false, add: false, del:false});
});
</script>
<style type ="text/css">
h1, h3, h4 {
	color: white;
	text-shadow: 3px 2px gray;
	font-family: FreeMono, monospace;
}

h2{
	color: gray;
	text-shadow: 4px 2px white;
	font-family: "Helvetic Neue", Helvetica, Arial, sans-serif;   
}
</style>
</head>


<body class="bg3">

	<div class="container-fluid row">
		<div class="col-sm-4">
			<img src="<c:url value="/resources/theme1/css/icon11.png"/>"
				alt="icon11.png" height="140px" />
		</div>
		<div class="col-sm-4">
				<h1 align = "center">Welcome to Boot Camp Java</h1>
				<h3 align = "center">How can we help you today?</h3>
		</div>
	</div>

	<nav class="navbar navbar-inverse" data-spy="affix"
		data-offset-top="197" style="background: linear-gradient(to bottom, #080808 45%, #989898 99%);">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand " href="#"><b>BCJ-HelpDesk</b></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="returntologin">Home</a></li>
				<li><a href="displayCreateTickets">Create Tickets</a></li>
				<li data-toggle="tab" data-target="#contact"><a href="#"><span
						class="glyphicon glyphicon-phone-alt"></span>Contact</a></li>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li data-toggle="tab" data-target="#myProfile"><a id="Profile"
					href="#"><span class="glyphicon glyphicon-user"></span>
						My Profile</a></li>

				<li><a href="logout"><span
						class="glyphicon glyphicon glyphicon-off"></span> Logout</a></li>

			</ul>
		</div>
	</nav>
	<div class="tab-content">
		<div id="contact" class="container jumbotron tab-pane fade"
			style="background: rgba(153, 235, 255, 0.5)">
			<h3 align="center">Contact Details</h3><hr>
			<p>
			<pre>
				Ektha Solution is very much responsive to queries and questions through email. 
				Please send us your business inquiries through the contact form or send an email or mail on the address below. 
				We are open to phone calls on business days during business hours.
			</pre>
			</p><hr>
			<p>
			<pre>
			<b>
			Office: USA
			</b>
			<b>
			Address:</b>
			3050 Regent BLVD, STE 150 Irving, TX 75063
			<b>
			Email:</b>
			info@ekthasol.com
			<b>
			Phone:</b>
			+1 214 550 0232
			</pre>
			</p>
		</div>
		</div>
			<div class = "container" align= "center">
				<table id="jqGrid">
				</table>
				<div id="jqGridPager"></div>
				
			</div>


</body>

</html>