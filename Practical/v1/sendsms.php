<?php

	require('textlocal.class.php');
	require('credential.php');
	require_once '../includes/DbOperations.php';
	
	$response = array(); 
	// Account details
	$apiKey = urlencode(API_KEY);
	
	// Message details
	$numbers = array(MOBILE);
	$sender = urlencode('TXTLCL');
	$otp = mt_rand(10000, 99999);
	$message = rawurlencode('This is your OTP:'.$otp);

	$db = new DbOperations(); 
    $result = $db->setOtp(MOBILE, $otp);
 
	$numbers = implode(',', $numbers);
 
	// Prepare data for POST request
	$data = array('apikey' => $apiKey, 'numbers' => $numbers, "sender" => $sender, "message" => $message);
 
	// Send the POST request with cURL
	$ch = curl_init('https://api.textlocal.in/send/');
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$response = curl_exec($ch);
	curl_close($ch);
	
	// Process your response here
	echo $response;

	
?>