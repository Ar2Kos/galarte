<!DOCTYPE HTML>
<html>
<head>
<title> Register at Galarte</title>
<link rel="icon" href="assets/gallery_icon.png">
<link rel = "stylesheet" type = "text/css" href = "s1.css">
</head>
<body>
<header>
<div class ="navContainer">
		<a href = "Homepage.html"><img src = "assets/gallery_icon.png" alt = "logo" class = "logo"></a>
		<nav>
			<ul>
			<li><a href = "pwebhp.php">Sign up</a></li>
			<li><a href = "tester.php">Sign in</a></li>
			<li><a>Events</a></li>
			</ul>
		</nav>
	
	</div>
</header>
<div class = "centreform">
<h1> Galarte </h1>

<form action = "Registration.php" method = "POST">
<table>

<tr>
<td> Desired Username: </td>
<td><input type = "text" name = "username" required></td>
</tr>

<tr>
<td>Gallery Name :</td>
<td><input type="text" name = "galleryName" required></td>
</tr>

<tr>
<td> Password :</td>
<td><input type="text" name="password" required></td>
</tr>


<tr>
<td> Address :</td>
<td><input type="text" name= "address" required></td>
</tr>


<tr>
<td>Phone Number :</td>
<td><input type= "text" name="phoneNumber" required></td>
</tr>

<tr>
<td>Email :</td>
<td><input type="text" name="email" required></td>
</tr>


<td><input type = "submit" value = "submit"></td>
</div>
</body>
</html>