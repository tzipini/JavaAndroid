<?php
    require '../_shared/DB_Manage.php';
    
    $name   = $_REQUEST["name"];
	
    $sql        = "INSERT INTO `takeNGo.branches`(`name`) VALUES ('$name')";
    
    if ($conn->query($sql) === TRUE) {
        echo "INSERTED OK";
    } else {
        echo "Error: " . $sql . "\n" . $conn->error;
    }