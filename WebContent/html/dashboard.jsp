<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Agile Meeting Dashboard</title>
    <link rel="stylesheet" href="style.css">
    <style type="text/css">
    	body{
		  margin: 0;
		  padding: 0;
		  font-family: "Roboto", sans-serif;
		}

		header{
		  position: fixed;
		  background: #22242A;
		  padding: 20px;
		  width: 100%;
		  height: 30px;
		}

		.left_area h3{
		  color: #fff;
		  margin: 0;
		  text-transform: uppercase;
		  font-size: 22px;
		  font-weight: 900;
		}

		.left_area span{
		  color: #19B3D3;
		}

		.logout_btn{
		  padding: 5px;
		  background: #19B3D3;
		  text-decoration: none;
		  float: right;
		  margin-top: -30px;
		  margin-right: 40px;
		  border-radius: 2px;
		  font-size: 15px;
		  font-weight: 600;
		  color: #fff;
		  transition: 0.5s;
		  transition-property: background;
		}

		.logout_btn:hover{
		  background: #0B87A6;
		}

		.sidebar {
		  background: #2f323a;
		  margin-top: 70px;
		  padding-top: 30px;
		  position: fixed;
		  left: 0;
		  width: 250px;
		  height: 100%;
		  transition: 0.5s;
		  transition-property: left;
		}

		.sidebar .profile_image{
		  width: 100px;
		  height: 100px;
		  border-radius: 100px;
		  margin-bottom: 10px;
		}

		.sidebar h4{
		  color: #ccc;
		  margin-top: 0;
		  margin-bottom: 20px;
		}

		.sidebar a{
		  color: #fff;
		  display: block;
		  width: 100%;
		  line-height: 60px;
		  text-decoration: none;
		  padding-left: 40px;
		  box-sizing: border-box;
		  transition: 0.5s;
		  transition-property: background;
		}

		.sidebar a:hover{
		  background: #19B3D3;
		}

		.sidebar i{
		  padding-right: 10px;
		}

		label #sidebar_btn{
		  z-index: 1;
		  color: #fff;
		  position: fixed;
		  cursor: pointer;
		  left: 300px;
		  font-size: 20px;
		  margin: 5px 0;
		  transition: 0.5s;
		  transition-property: color;
		}

		label #sidebar_btn:hover{
		  color: #19B3D3;
		}

		#check:checked ~ .sidebar{
		  left: -190px;
		}

		#check:checked ~ .sidebar a span{
		  display: none;
		}

		#check:checked ~ .sidebar a{
		  font-size: 20px;
		  margin-left: 170px;
		  width: 80px;
		}

		.content{
		  margin-left: 250px;
		  background: #354152;
		  background-position: center;
		  background-size: cover;
		  height: 100vh;
		  transition: 0.5s;
		}

		#check:checked ~ .content{
		  margin-left: 60px;
		}

		#check{
		  display: none;
		}
    </style>
  </head>
  <body>
  	

    <input type="checkbox" id="check">
    <!--header area start-->
    <header>
      <label for="check">
        <i class="fas fa-bars" id="sidebar_btn"></i>
      </label>
      <div class="left_area">
        <h3>Meeting <span>Dashboard</span></h3>
      </div>
      <div class="right_area">
        <a href="logoutfunc" class="logout_btn">Logout</a>
      </div>
    </header>
    <!--header area end-->
    <!--sidebar start-->
    <div class="sidebar">
      <center>
        <!-- <img src="1.png" class="profile_image" alt=""> -->
        <h4>Welcome </h4>
        <h3 style="color:white;">${ username }</h3>
      </center>
      <a href="#"><i class="fas fa-desktop"></i><span>Dashboard</span></a>
      <a href="#"><i class="fas fa-cogs"></i><span>Components</span></a>
      <a href="#"><i class="fas fa-table"></i><span>Tables</span></a>
      <a href="#"><i class="fas fa-th"></i><span>Forms</span></a>
      <a href="#"><i class="fas fa-info-circle"></i><span>About</span></a>
      <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>
    </div>
    <!--sidebar end-->

    <div class="content">
    
    <h2> </h2>
    <h1> </h1>
    <form action="createchannel">
    	<button type="submit">create channel</button>
    </form>
    	
    	
    </div>

  </body>
</html>


