<?php 

	
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'practical_exam');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$stmt = $conn->prepare("SELECT id, room_title, quantity, total, username FROM bookings;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id, $room_title, $quantity, $total, $username);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['room_title'] = $room_title; 
		$temp['quantity'] = $quantity;  
		$temp['total'] = $total; 
		$temp['username'] = $username;
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);

$fp = fopen('C:\\xampp\\htdocs\\Android\\bookings.json','w');
	fwrite($fp, json_encode($products));
	fclose($fp);