<?php	
	$manager = $_POST['username'];
	$gName = $_POST['galleryName'];
	$password = $_POST['password'];
	$address = $_POST['address'];
	$phoNum = $_POST['phoneNumber'];
	$email = $_POST['email'];
	
	
	if (!empty($manager) || !empty($gName) || !empty($password) ||!empty($address) || !empty($phoNum) || !empty($email))
	{
		$host = 'localhost';
		$username = 'root';
		$dbpw = '';
		$dbname = 'ip';		
		$conn = new mysqli($host,$username,$dbpw,$dbname);
		
		if (mysqli_connect_error()){ die('Connection error');}
		else{
			
			$SELECT = "SELECT GalleryName From gm1 WHERE GalleryName = ? Limit = 1";
			$INSERT = "INSERT Into gm1 (Username,GalleryName,Password,Address,PhoneNumber,Email) values(?,?,?,?,?,?)";
			$rnum = '';
			$stmt = '';
			
			
			if( $stmt = $conn->prepare($SELECT)){
				$stmt->bind_param("s",$gName);
				$stmt->execute();
				$stmt->bind_result($user);
				$stmt->store_result();
				$rnum = $stmt->num_rows; 
			}
								
				
			
			if( $rnum ==0 ){
				if($stmt = $conn->prepare($INSERT)){
					$stmt->bind_param("ssssss", $manager,$gName,$password,$address,$phoNum,$email);
					$stmt->execute();
					echo 'congratulations on joining our app!';
				}else{
					echo 'error';
				}	
			}else{
				echo 'Someone has already created an account for this gallery';
			}
			
			$stmt->close();
			$conn->close();
		}
	}else{ 	
		echo 'All fields are required';
		die();
	}
	
?>