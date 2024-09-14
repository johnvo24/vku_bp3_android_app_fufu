<?php
if (!empty($_POST["name"]) && !empty($_POST["gender"]) && !empty($_POST["dob"]) && !empty($_POST["phone"])) {
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    $name = $_POST["name"];
    $gender = $_POST["gender"];
    $dob = $_POST["dob"];
    $phone = $_POST['phone'];
    if ($con) {
        $sql = "UPDATE user SET userFullName = '$name', userGender = '$gender', userDob = '$dob' WHERE userPhone = '$phone'";
        if (mysqli_query($con, $sql)) {
            echo "success";
        } else {
            echo "fail";
        }
    } else {
        echo "Couldn't connect to server";
    }
} else if (!empty($_POST["email"]) && !empty($_POST["address"]) && !empty($_POST["phone"])) {
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    $email = $_POST["email"];
    $address = $_POST["address"];
    $phone = $_POST['phone'];
    if ($con) {
        $sql = "UPDATE user SET userEmail = '$email', userAddress = '$address' WHERE userPhone = '$phone'";
        if (mysqli_query($con, $sql)) {
            echo "success";
        } else {
            echo "fail";
        }
    } else {
        echo "Couldn't connect to server";
    }
} else if (!empty($_POST["bio"]) && !empty($_POST["phone"])) {
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    $bio = $_POST["bio"];
    $phone = $_POST['phone'];
    if ($con) {
        $sql = "UPDATE user SET userBio = '$bio' WHERE userPhone = '$phone'";
        if (mysqli_query($con, $sql)) {
            echo "success";
        } else {
            echo "fail";
        }
    } else {
        echo "Couldn't connect to server";
    }
} else if (!empty($_POST["pass"]) && !empty($_POST["passNew"]) && !empty($_POST["passNewRepeat"]) && !empty($_POST["phone"])) {
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    $pass = $_POST["pass"];
    $passNew = $_POST['passNew'];
    $passNewRepeat = $_POST['passNewRepeat'];
    $phone = $_POST['phone'];
    if ($passNew != $passNewRepeat) {
        echo "different";
    } else {
        $sql = "SELECT * FROM user WHERE userPhone = '$phone'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            if (password_verify($pass, $row['userPass'])) {
                $passMoi = password_hash($passNew, PASSWORD_DEFAULT);
                $sqlUpdate = "UPDATE user SET userPass = '$passMoi' WHERE userPhone = '$phone'";
                if (mysqli_query($con, $sqlUpdate)) {
                    echo "success";
                } else echo "failed";
            } else echo "wrong";
        }
    }
} else {
    echo "Vui lòng điền thông tin!";
}
?>