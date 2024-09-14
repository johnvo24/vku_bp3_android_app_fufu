<?php
if (!empty($_POST["email"]) && !empty($_POST["phone"]) && !empty($_POST["pass"]) && !empty($_POST["name"])) {
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    $email = $_POST["email"]; // post email
    $phone = $_POST["phone"]; // post phone
    $name = $_POST["name"];
    $pass = password_hash($_POST["pass"], PASSWORD_DEFAULT); // post pass
    $checkPass = strlen($_POST['pass']);
    if ($con) {
        if ($checkPass < 6) {
            echo "not";
        } else {
            $sqlCheckPhone = "SELECT user.userPhone FROM user WHERE userPhone = '$phone'";
            $result = mysqli_query($con, $sqlCheckPhone);
            if (mysqli_num_rows($result) != 0) {
                echo "exist";
            } else {
                $sql = "INSERT INTO `user`(`userPass`, `userEmail`, `userPhone`, `userFullName`) 
                    VALUES ('$pass', '$email', '$phone', '$name')";
                if (mysqli_query($con, $sql)) {
                    echo "success";
                } else {
                    echo "fail";
                }
            }
        }
    } else echo "Couldn't connect to server";
    
} else {
    echo "Fill out data. Please!";
}
?>