<?php
    require '../_shared/DB_Manage.php';
    
    $modelId   = $_REQUEST["modelId"];
	$modelName   = $_REQUEST["modelName"];
 	$companyName   = $_REQUEST["$companyName"];
	
    $sql        = "INSERT INTO `takeNGo.models`( `modelId`, `modelName`,`companyName` ) VALUES ('$modelId', '$modelName', '$companyName')";
    
    if ($conn->query($sql) === TRUE) {
        echo "INSERTED OK";
    } else {
        echo "Error: " . $sql . "\n" . $conn->error;
    }