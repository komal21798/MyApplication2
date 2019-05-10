<?php

	class ProductOperations{

		private $con;

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();
		}

		public function createProduct($title, $shortdesc, $price, $image) {

             $stmt = $this->con->prepare("INSERT INTO `products` (`id`, `title`, `shortdesc`, `price`, `image`) VALUES (NULL, ?, ?, ?, ?);");
                $stmt->bind_param("ssss",$title,$shortdesc,$price,$image);
 
                if($stmt->execute()){
                    return 1; 
                }else{
                    return 2; 
                }

        }

        public function deleteProduct($room_title,$quantity,$total,$username) {

            $stmt = $this->con->prepare("INSERT INTO `bookings` (`id`, `room_title`, `quantity`, `total`,`username`) VALUES (NULL, ?, ?, ?, ?);");
            $stmt->bind_param("ssss",$room_title,$quantity,$total,$username);


            if($stmt->execute()){
                return 1; 
            }else{
                return 2; 
            }
            
        }
    }