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
			width: 70%;
			padding: 10px;
			background-color: white;
			margin-right: 30%;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.messageclassr{
			width: 70%;
			padding: 10px;
			background-color: #007bff;
			color: white;
			margin-left: 30%;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.delbutclass{
			float: right;
			margin-top: 2px;
		}
	</style>
	
</head>
<body>
	<nav class="navbar navbar-light bg-primary" style="top: 0; width: 100%; position: fixed;" >
  		<span class="navbar-brand mb-0 h1" style="color: white"><a style="color : white;" href="/agilemeetdash/">Agile Meeting Dashboard</a></span>
  		<a style="color: white" href="logoutfunc">Logout</a>
	</nav>

	<div style="width:100%;" class="wrapper">
        <div class="contentWrapper" style="width:100%; margin-top: 100px;">
        	

        	<div id="content" runat="server" class="content" style="width:70%; float:left; position: fixed;">
        		
        		<div style="margin: 30px;">
              	<h2 class="heading">Agile Meeting Dashboard - Meeting</h2>
              	<div>
              		
              		<%
              			String isadmin =(String) request.getAttribute("isadmin");
              		
              			if(isadmin.equals("true")){
              				out.println("<p></p>");
              				out.println("<h5>All your Notes Captures will be available in Admin section of page</h5>");
              			}
              		%>
              		
              		
              		
              	</div>
              	</div>
              	
              </div>
              
              <div style=" width: 30%; height: 100%;  margin-left: 70%;  padding: 10px; background-color: #999999;" >
					<div style="margin-top: 56px;"></div>
					<div id="messages" style="height : 85%;">
					
					</div>
					
					<div style="bottom: 10px; position: fixed; 
					width: 100%; height: 15%;" class="form-inline">
						<input class="form-control mr-sm-2" type="search" 
						name="searchdata" id="msgdata" style="width: 20%;">
				    	<button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="sendmsg"  onclick="send()" style="width: 10%">Send</button>
					</div>
				</div>
              
          </div>
      </div>
      
      <script>

	      var webSocket = new WebSocket('ws://localhost:8080/agilemeetdash/websocket');
	      var channelname =  '<%= (String) request.getAttribute("channelname")  %>';
	      var isadmin = '<%= (String) request.getAttribute("isadmin")  %>';
	      var currentuser = '<%= (String) request.getAttribute("useremail")  %>';
	      
			
	      webSocket.onerror = function(event) {
	        onError(event)
	      };
	
	      webSocket.onopen = function(event) {
	        onOpen(event)
	      };
	
	      webSocket.onmessage = function(event) {
	        onMessage(event)
	      };
	
	      function onMessage(event) {
	    	  
	    	var obj = JSON.parse(event.data);
	        if(obj.channelname == channelname){
	        	var divele = document.createElement('div');
	        	var inputele = document.createElement("INPUT");
	        	inputele.setAttribute("type", "hidden");
	        	inputele.setAttribute("value", JSON.stringify(obj));
	        	inputele.id = "hidinp" ;
	        	
	        	divele.append(inputele);
	        	
	        	if(obj.msgby == currentuser){
	        		divele.className = "messageclass"+ "r" + " btn-outline-primary";
	        		if( isadmin == "true"){
		        		var assignbutton = document.createElement('button');
			        	assignbutton.innerHTML = "Note" ;
			        	assignbutton.className = "delbutclass"
			        	assignbutton.onclick = assignfunc;
			        	divele.append(assignbutton);
		        	}
	        	}
	        	else{
	        		divele.className = "messageclass"+ "l" + " btn-outline-primary";
	        	}
	        	
	        	
	        	var pele = document.createElement('h5');
	        	pele.innerHTML = obj.text;
	        	divele.append(pele);
	        	
	        	document.getElementById('messages').append(divele);
	        }
	    	
	      }
	
	      function onOpen(event) {
	        document.getElementById('messages').innerHTML 
	          = 'Connection established';
	      }
	
	      function onError(event) {
	        alert(event.data);
	      }
	
	      function send() {
	        var txt = document.getElementById('msgdata').value;
	        var d = new Date();
	        var msg = {
	        		type : "message",
	        	    channelname : channelname,
	        	    text: txt,
	        	    date : d,
	        	    msgby : currentuser,
	        	    
	        	  };
	        
	        webSocket.send(JSON.stringify(msg));
	        txt.value = "";
	        return false;
	      }
	      
	      function assignfunc() {
	    	  var ele = this.parentNode;
	    	  console.log(ele);
	    	  var hidinp = ele.querySelector("#hidinp");
	    	  console.log(hidinp);
		      var jsonstr = hidinp.value;
		      console.log(jsonstr);
		      var obj = JSON.parse(jsonstr);
		      obj.type = "assign" ;
		        
		      webSocket.send(JSON.stringify(obj));
		        
		      return false;
	    	  
	      }
      </script>

	
</body>
</html>