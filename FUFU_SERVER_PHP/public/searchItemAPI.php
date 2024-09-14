<?php
$con = mysqli_connect("localhost", "root", "", "fufu_db");

if ($con) {
    $sql = "SELECT item.itemId, item.resId, item.itemImg, item.itemName, item.itemDes, item.itemPrice, restaurant.resName 
    FROM item INNER JOIN restaurant ON item.resId = restaurant.resId";
    $result = mysqli_query($con, $sql);
    if (mysqli_num_rows($result) != 0) {
        $rows = mysqli_fetch_all($result, MYSQLI_ASSOC);
        echo json_encode($rows);
    }
} else {
    echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
}
?>