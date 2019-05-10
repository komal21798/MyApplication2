<?php 
 
require_once '../includes/DbOperations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['phone']) and isset($_POST['password'])){
        $db = new DbOperations(); 
 
        if($db->userLogin($_POST['phone'], $_POST['password'])){
            $user = $db->getUserByPhone($_POST['phone']);
            $response['error'] = false; 
            $response['id'] = $user['id'];
            $response['phone'] = $user['phone'];
        }else{
            $response['error'] = true; 
            $response['message'] = "Invalid username or password";          
        }
 
    }else{
        $response['error'] = true; 
        $response['message'] = "Required fields are missing";
    }
}
 
echo json_encode($response);