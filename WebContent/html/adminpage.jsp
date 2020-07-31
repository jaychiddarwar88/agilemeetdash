<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<html>
<head>
	<title>Agile Meeting Dashboard</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<style>
		.searchbar{
		margin-top: 20px;
		margin-bottom: 20px;
		margin-right: 30%;
		margin-left: 40%;
		width: 30%;
	
		}
		.chatbox{
			margin-right:10px; 
			margin-left:10px;  
			margin-bottom: 10%;
			overflow-y: hidden; 
			overflow-y: auto;
			background-color: #DDDDDD; 
			padding: 20px;  
		}
		.postclass{
			color : black;
			margin: 10%;
			width: 80%;
			padding: 10px;

		}
		.marginclass{
			margin-top: 10px; 
			padding-bottom: 10px;
		}
		.messageclassl{
			
			padding: 10px;
			background-color: white;
			margin-right: 30%;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.messageclassr{
			
			padding: 10px;
			background-color: #28a745;
			color: white;
			margin-left: 30%;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.delbutclass{
			float: right;
			margin-top: 5px;
		}
	</style>
	
</head>
<body>
	<nav class="navbar navbar-light bg-primary" style="top: 0; width: 100%; position: fixed;" >
  		<span class="navbar-brand mb-0 h1" style="color: white"><a style="color : white;" href="/agilemeetdash/">Agile Meeting Dashboard</a></span>
  		<a style="color: white" href="logoutfunc">Logout</a>
	</nav>

	<div style="width:100%;" class="wrapper">
        <div class="contentWrapper" style="width:100%;">
        	<div id="leftnav"  class="left-nav" style="width:20%; float:left; position:fixed; height:100%; background-color:#007bff; top: 56px; left: 0px; overflow-y:scroll hidden;">
        		<div style="top: 56px"></div>
        		<div id="tableofcontents">
        			
        			<div style="margin-top: 20px;"></div>
        			
					<div style="text-align: center; color: white;"><h3>Create Channel</h3></div>
					<div style="margin-top: 20px;"></div>
					<div style="margin-left: 15px; margin-right: 15px;">
						
						<form action = "createchannel">
					    		<input class="form-control mr-sm-2" placeholder="Create Channel" id="channelname" name="channelname" type="text" required="required">
					    		<div style="margin-top: 20px;"></div>
					    		<button class='btn btn-outline-light my-2 my-sm-0' id='createchannelid' >Create Channel</button>
					    </form>
					    
					        		
					</div>
					<div style="margin-top: 20px;"></div>
					<div id="channellist" style="margin: 10px; overflow-y: auto;background-color: white; color: black; ">
						<%  String haschannel = (String) request.getAttribute("haschannel");
							if (haschannel != null && haschannel.equals("yes")){
								
							
							// retrieve your list from the request, with casting 
							ArrayList<String> list = (ArrayList<String>) request.getAttribute("channellist");
							
							// print the information about every category of the list
							for(String channel : list) {
								out.println("<p></p>");
								out.println("<div style = 'margin : 20px;'> ");
								out.println("<form action='getchannel'  method='POST'   >  <input name='channelname' type='hidden' value = '" + channel +"'>");
							    out.println("<button class='btn btn-outline-primary my-2 my-sm-0' id='createchannelid' >"+ channel +"</button>");
							    out.println("</form></div>");
							    out.println("<p></p>");
							}
							}
							
							%>
					</div>
        			
        		</div>
        	</div>

        	<div id="content" runat="server" class="content" style="width:80%; float:right;">
        		<div style="margin-top: 56px;"></div>
        		<div style="margin: 30px;">
              	<h2 class="heading">Agile Meeting Dashboard - Admin Page</h2>
              	<div>
              		
              		<div >
              		<form action='addmember' method='POST'>
              			<input type="text" name="memberemail" >
						<button class='btn btn-outline-primary my-2 my-sm-0'   >Add Member</button>
					</form>
              		</div>
              		
					<p></p>
					<form action='startmeeting'  method='GET'>
						<button class='btn btn-outline-primary my-2 my-sm-0'  >Tracker</button>
					</form>
					<br>
					
					<p></p>
					
					<h3>Notes Captured in meeting</h3>
					<div id="notescontenthere" >
						
					</div>
					
					
              		
              		
              	</div>
              	</div>
              	
              </div>
          </div>
      </div>
      <script>
      /*
      	var jsonraw = ${jsonlist};
      	var jsonlist = JSON.parse(jsonraw);
      	console.log(jsonlist);
      	*/
      	
      	
      	var noteslist = [];
      	<%
      		String channel = (String) request.getAttribute("channelname");
	      	ArrayList<String> noteslist = (ArrayList<String>) request.getAttribute("noteslist");
      		int i = 0;
			for( String notes : noteslist){
				
      	%>
      		noteslist[ <%= i %>] =  '<%= notes %>' ;
		<%
			i = i + 1;
			}
      	%>
      	
		var memberlist = [];
      	
      	<%
	      	ArrayList<String> memberlist = (ArrayList<String>) request.getAttribute("memberlist");
      		int j = 0;
			for( String member : memberlist){
				
      	%>
      		memberlist[ <%= j %>] =  '<%= member %>' ;
		<%
			j = j + 1;
			}
      	%>
      	//console.log(memberlist);
      	
      	var channelname = '<%= channel %>';
      	//console.log(channelname);
      	
      	noteslist.forEach(myFunction);

      	function myFunction(item, index) {
      		var obj = JSON.parse(item);
      		var divele = document.createElement('div');
        	var inputele = document.createElement("INPUT");
        	inputele.setAttribute("type", "hidden");
        	inputele.setAttribute("value", obj.nodeid);
        	inputele.id = "hidinp" ;
        	var title = document.createElement("h4");
        	title.innerHTML = obj.title ;
        	var datetime = obj.datetime;
        	var spdate = datetime.split("T");
        	var date = spdate[0];
        	var time = spdate[1];
        	time = time.substring(0, time.length - 5);
        	
        	var newpele = document.createElement("p");
        	newpele.innerHTML = date + " " + time;
        	
        	divele.append(inputele);
        	divele.append(title);
        	
        	var editbtn = document.createElement("button");
        	var assignbtn = document.createElement("button");
        	
        	//edit button 
        	editbtn.onclick = editcurrent ;
        	editbtn.innerHTML = "Edit";
        	
        	
        	//dropdown list dynamic
        	
        	var dropdownlist = document.createElement("SELECT");
        	dropdownlist.id = "curselectval";
        	var soption = document.createElement("OPTION");
        	//soption.innerHTML = "members";
        	dropdownlist.options.add(soption);
        	
        	for (var i = 0; i < memberlist.length; i++) {
                var option = document.createElement("OPTION");
 
                //Set Customer Name in Text part.
                option.innerHTML = memberlist[i];
 
                //Set CustomerId in Value part.
                option.value = memberlist[i];
 
                //Add the Option element to DropDownList.
                dropdownlist.options.add(option);
            }
        	
        	
        	// assign buuton
        	assignbtn.onclick = assigncurrent ;
        	assignbtn.innerHTML = "Assign task" ;
        	
        	
        	divele.className = "messageclass"+ "l" + " btn-outline-primary";
        		/*
	        		var assignbutton = document.createElement('button');
		        	assignbutton.innerHTML = "Note" ;
		        	assignbutton.className = "delbutclass"
		        	assignbutton.onclick = assignfunc;
		        	divele.append(assignbutton);
		        */	
		        
        	var pele = document.createElement('h5');
        	pele.innerHTML = obj.msgvalue;
        	divele.append(pele);
        	divele.append(newpele);
        	divele.append(editbtn);
        	divele.append(document.createElement("p"));
        	divele.append(dropdownlist);
        	divele.append(assignbtn);
        	
        	document.getElementById('notescontenthere').append(divele);
      	}
      	
      	function editcurrent(){
      		var ele = this.parentNode;
      		var hidinp = ele.querySelector("#hidinp");
	    	  console.log(hidinp);
	    	var noteid = hidinp.value;
	    	
      		var newtitle;
      	  	var title = prompt("Enter new Title", "");
      	  	if (title == null || title == "") {
      	    	
      	  	} else {
      	    	newtitle = title ;
      	    	ajaxfunc(noteid, newtitle);
      	    	
      	  	}
      	  //document.getElementById("demo").innerHTML = txt;
      	  
      	}
      	
      	function assigncurrent(){
      		var ele = this.parentNode;
      		var hidinp = ele.querySelector("#hidinp");
	    	  console.log(hidinp);
	    	var noteid = hidinp.value;
	    	var memberemail = ele.querySelector("#curselectval").value ;
	    	
      		var newdate;
      	  	var duedate = prompt("Enter new Due Date for task", "");
      	  	if (duedate == null || duedate == "") {
      	    	newdate = "nodate";
      	  	} else {
      	    	newdate = duedate ;	
      	  	}
      	  ajaxassignfunc(noteid, channelname , newdate, memberemail);
      	  //document.getElementById("demo").innerHTML = txt;
      	  
      	}
      	
      	function ajaxfunc(noteid, newtitle){
      		var xhr = new XMLHttpRequest();
			//request.open('POST' , '/agilemeetdash/editnotes');
			console.log(noteid);
			console.log( newtitle) ;
			
			
			
			var data = "noteid=" + encodeURIComponent(noteid)
	        + "&newtitle=" + encodeURIComponent(newtitle);
			xhr.open("POST", '/agilemeetdash/editnotes', true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onload = () =>{

				var data = xhr.responseText;	
				console.log(data);
				console.log("success");
				location.reload();

			}
			xhr.send(data);
			
      		
      	}
      	
      	function ajaxassignfunc(noteid, channelname , newdate, memberemail){
      		var xhr = new XMLHttpRequest();
			
      		
      		//request.open('POST' , '/agilemeetdash/editnotes');
			console.log(noteid);
			console.log( channelname) ;
			console.log( newdate) ;
			console.log( memberemail) ;
			
			
			
			var data = "noteid=" + encodeURIComponent(noteid)
			+ "&channelname=" + encodeURIComponent(channelname)
			+ "&newdate=" + encodeURIComponent(newdate)
	        + "&memberemail=" + encodeURIComponent(memberemail);
			
			xhr.open("POST", '/agilemeetdash/assigntask', true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onload = () =>{

				var data = xhr.responseText;	
				console.log(data);
				console.log("success");

			}
			xhr.send(data);
			
			
      		
      	}
      	
      </script>

	
</body>
</html>