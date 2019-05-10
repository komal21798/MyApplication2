<?php

	require_once '../includes/ProductOperations.php';
	
	$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(
        isset($_POST['room_title']) and
            isset($_POST['quantity']) and
                isset($_POST['total']) and
                    isset($_POST['username']))
        {
        //operate the data further 
 
        $db = new ProductOperations(); 
 
        $result = $db->deleteProduct(   $_POST['room_title'],
                                        $_POST['quantity'],
                                        $_POST['total'],
                                        $_POST['username']
                                
                                );
        if($result == 1){
            $response['error'] = false; 
            $response['message'] = "Product booked successfully";
        }elseif($result == 2){
            $response['error'] = true; 
            $response['message'] = "Some error occurred please try again";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
 
echo json_encode($response);