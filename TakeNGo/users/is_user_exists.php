<?php
try {
	require '../_shared/DB_Manage.php';
	$email = $_REQUEST["email"];
	$password = $_REQUEST["password"];
	$sql = "SELECT * FROM `takeNGo.users` where email='$email' and password='$password'";
	$result = $conn->query($sql);
	$data = array();
	if ($result->num_rows > 0) {
		while ($row = $result->fetch_assoc()) {
			array_push($data, $row);
		}
		echo json_encode(array('users' => $data));
	}
	else {
		echo "0 results";
	}
}
catch(Exception $e) {
	echo "Exception Error See Log....";
	error_log($e->getMessage() , 0);
}

$conn->close();
?>

