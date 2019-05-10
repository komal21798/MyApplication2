<?php

	require('textlocal.class.php');
	
	require('credential.php');

	$response = array(); 
	$textlocal = new Textlocal(false, false, API_KEY);

	$numbers = array(917600917121);
	$sender = 'TXTLCL';
	$otp = mt_rand(10000, 99999);
	$message = "Hello. This is your OTP: " . $otp;

	try {
    	$result = $textlocal->sendSms($numbers, $message, $sender);
    	print_r($result);
    	$response['error'] = false; 
        $response['message'] = "OTP sent.";
	} catch (Exception $e) {
    	die('Error: ' . $e->getMessage());
    	$response['error'] = true; 
        $response['message'] = "Error";
	}