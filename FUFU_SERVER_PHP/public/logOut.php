<?php
if (!empty($_POST["phone"]) && !empty($_POST["userStatus"])) {
    $phone = $_POST["phone"];
    $userStatus = $_POST["userStatus"];
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    if ($con) {
        $sql = "SELECT * FROM user WHERE userPhone = '$phone'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            $sqlUpdateUser = "UPDATE user SET userStatus = 0 WHERE userPhone = '$phone'";
            if (mysqli_query($con, $sqlUpdateUser)) {
                echo "success";
            } else echo "LogOut Failed";
        } else echo "ahuhu dongu";
    } else echo "Couldn't connect to server";
} else echo "All fields are required";

?>