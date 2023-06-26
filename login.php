<?php
$idDatabase = "root";
$namaAtauIpServer = "localhost";
$namaDatabase = "stmik011100862";
$passwordDatabase = "password";

$connection = new mysqli($namaAtauIpServer, $idDatabase, $passwordDatabase, $namaDatabase);

if($connection->connect_errno){
    echo $connection->connect_error;
    exit;
}

$id = $_GET["id"];
$password = $_GET["password"];
$sql = "SELECT COUNT(id) AS idCount FROM pemakai WHERE id ='$id' AND password = '$password'";
$recordList = $connection->query($sql);

$connection->close();

if(!$recordList){
    echo $connection->error;
    exit;
}

if ($recordList->num_rows == 0){
    echo "Tidak Ada Data.";
    exit;
}

while($record = $recordList->fetch_assoc()){
    $arrOutput[] = $record;
}

echo json_encode($arrOutput);

$recordList->free();
?>