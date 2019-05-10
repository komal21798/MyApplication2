<?php

    class DbOperations{

        private $con;

        function __construct(){

            require_once dirname(__FILE__).'/DbConnect.php';

            $db = new DbConnect();

            $this->con = $db->connect();
        }

        public function createUser($email, $phone) {

            if($this->isUserExist($email)){
                return 0; 
            }else{
                
                $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `email`, `phone`) VALUES (NULL, ?, ?);");
                $stmt->bind_param("ss",$email,$phone);
 
                if($stmt->execute()){
                    return 1; 
                }else{
                    return 2; 
                }
            }
        }


        public function userLogin($phone, $pass){
            
            $stmt = $this->con->prepare("SELECT id FROM users WHERE phone = ? AND password = ?");
            $stmt->bind_param("ss",$phone,$pass);
            $stmt->execute();
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }

        public function getUserByPhone($phone){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE phone = ?");
            $stmt->bind_param("s",$phone);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        private function isUserExist($email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute(); 
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }

        public function setOtp($phone, $password){
            $stmt = $this->con->prepare("UPDATE `users` SET `password` = ? WHERE $phone = ?");
            $stmt->bind_param("ss",$password,$phone);
 
                if($stmt->execute()){
                    return 1; 
                }else{
                    return 2; 
                }
                
        }

    }