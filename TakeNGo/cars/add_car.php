<?php
    require '../_shared/DB_Manage.php';
    
    $carNo   = $_REQUEST["carNo"];
	$branchNo   = $_REQUEST["branchNo"];
	$modelId   = $_REQUEST["modelId"];
	$kilometers   = $_REQUEST["kilometers"];

    $sql        = "INSERT INTO `takeNGo.cars`( `carNo`, `branchNo`,`modelId`, `kilometers` ) VALUES ('$carNo', '$branchNo', '$modelId', '$kilometers')";
    
    if ($conn->query($sql) === TRUE) {
        echo "INSERTED OK";
    } else {
        echo "Error: " . $sql . "\n" . $conn->error;
    }