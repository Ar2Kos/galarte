<!DOCTYPE html> 
<html> 
	
<head>
<title>Galarte</title>
<link rel="icon" href="assets/gallery_icon.png">
<link rel = "stylesheet" type = "text/css" href = "s2.css">
</head> 

<body> 
<header>
<div class ="navContainer">
		<a href = "Homepage.html"><img src = "assets/gallery_icon.png" alt = "logo" class = "logo"></a>
		<nav>
			<ul>
			<li><a href = "pwebhp.php">Sign up</a></li>
			<li><a>Sign in</a></li>
			<li><a>Events</a></li>
			</ul>
		</nav>
	
	</div>
</header>

	
	<form method="post" style = "padding-top: 15px;"> 
		<input type = "input" name = "username"/>
		<input type="submit" name="login"
				value="Button1"/> 
		
	</form> 
	
	<?php
	
		if(!empty($_POST['login'])) { 
			$manager = $_POST['username'];
			
			$servername = "localhost";
			$un = "root";
			$password = "";
			$dbname = "ip20";

			// Create connection
			$conn = new mysqli($servername, $un, $password, $dbname);
			// Check connection
			if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			}

			$sql = "SELECT PLACE_ID FROM manager WHERE username = ?";
			$stmt = '';
			$rnum ='';
			if( $stmt = $conn->prepare($sql)){
				$stmt->bind_param("s",$manager);
				$stmt->execute();
				$stmt->bind_result($place);
				$stmt->store_result();
				$rnum = $stmt->num_rows; 
				$stmt->fetch();
			}
			if($rnum == 0){
			echo "login unavalable";
			}else{
			echo "Connected on: ".$place;}
			
			echo "<br>";
			
			$result ='';
			$sqlb = "SELECT Piece FROM location WHERE PLACE_ID = ?";
			$sqlc = "SELECT name,date,description FROM art_piece WHERE ID = ?";
			$stmtb ='';
			$stmtc= '';
			$rnumb = '';
			$pieces = array();
			if($stmtb = $conn->prepare($sqlb)){
				$stmtb->bind_param("i",$place);
				$stmtb->execute();
				$stmtb->store_result();
				$rnumb = $stmtb->num_rows;
				echo "found: ".$rnumb."<br>";
				$stmtb->bind_result($Piece);
				
				while($stmtb->fetch()){
					$stmtc = '';
					if($stmtc = $conn->prepare($sqlc)){
						$stmtc->bind_param("i",$Piece);
						$stmtc->execute();
						$stmtc->store_result();
						$stmtc->bind_result($Name,$Date,$Desc);
						$stmtc->fetch();
						$bffer = array($Name,$Date,$Desc);
						$pieces[] = $bffer;
						
						
						
					}
				}
				
				
				
				
			}
			if(isset($_POST['delete'])){
				console.log("Recieved delete request");
				$delQuery = 'DELETE FROM art_piece WHERE ID = :pieceID';
				mysql_query($delQuery,$con);
				
			}
			$conn->close();
		} 
		
	?> 
	<div id = "data" style = "display: none;">
	<?php 
	if(isset($_POST['login'])){
		echo json_encode($pieces);} 
	?>
	</div>
	
	
	
	
	<div id = "output">
		
	</div>
	<script>
		var out = document.getElementById('output');
		out.style["display"] = "inline-block";
		var inn = document.getElementById('data');
		var data = inn.textContent;
		console.log("the data is :" ,data);
		var br = document.createElement("br");
		if(data != ""){
		var pieces = JSON.parse(data);
		console.log(pieces);}
		
		var createform = document.createElement('form');
		createform.setAttribute("action","");
		createform.setAttribute("method","post");
		out.appendChild(createform);
		
		var namelabel = document.createElement('label');
		namelabel.innerHTML = "Piece Name: ";
		createform.appendChild(namelabel);
		var nameinput = document.createElement('input');
		nameinput.setAttribute("type","text");
		nameinput.setAttribute("name", "dname");
		createform.appendChild(nameinput);
		
		var linebreak = document.createElement('br');
		createform.appendChild(linebreak);
		
		var datelabel = document.createElement('label');
		datelabel.innerHTML = "Date of piece: ";
		createform.appendChild(datelabel);
		
		var dateinput = document.createElement('input');
		dateinput.setAttribute("type","text");
		dateinput.setAttribute("name","ddate");
		createform.appendChild(dateinput);
		
		var nextbreak = document.createElement('br');
		createform.appendChild(nextbreak);
		
		var desclabel = document.createElement('label');
		desclabel.innerHTML = "Description: ";
		createform.appendChild(desclabel);
		
		var descinput = document.createElement('textarea');
		descinput.setAttribute("name","descript");
		createform.appendChild(descinput);
		
		var lastbreak = document.createElement('br');
		createform.appendChild(lastbreak);
		
		var submitelement = document.createElement('input');
		submitelement.setAttribute("type","submit");
		submitelement.setAttribute("name","dsubmit");
		submitelement.setAttribute("value", "Submit");
		createform.appendChild(submitelement);
		
		
		
		pieces.forEach(getPiece);
		
		
		
		var id = 0;
		
		function addPiece(name,date,desc){
			const pad = "13%";
			
			var divbffer = document.createElement(id);
			var label = document.createTextNode("Name: ");
			label.textContent += name ;

			
			divbffer.style.height = "145px";
			divbffer.style.width = "99.85%";
			divbffer.style.border = "thin solid black";
			divbffer.style["display"] = "block";
			
			var t1c = document.createElement("div");
			var text = document.createTextNode("Date: ");
			text.textContent += date
			t1c.appendChild(text);
			var t2c = document.createElement("div");
			
			var text2 = document.createTextNode("Description: ");
			text2.textContent += desc;
			t2c.appendChild(text2);
			
			t1c.style.position = "relative";
			t1c.style.left = pad;
			t1c.style["margin-right"] = pad;
			t2c.style.position = "relative";
			t2c.style.left = pad;
			t2c.style["margin-right"] = pad;
			t2c.style.width = "80%";
			
			var delcontainer = document.createElement("FORM");
			delcontainer.method = "post";
			delcontainer.name = "delform";
			var delbtn = document.createElement("button");
			delbtn.innerHTML = "delete";
			
			divbffer.appendChild(label);
			divbffer.appendChild(t1c);
			divbffer.appendChild(t2c);
			divbffer.appendChild(delbtn);
			
			out.appendChild(divbffer);
			id++;
			
		}
		
		function getPiece(item){
			addPiece(item[0],item[1],item[2]);
		}
		
		
		//var print = document.createTextNode(testata);
		//out.appendChild(print);
	</script>
	
</head> 

</html> 
