<?php
if (!empty($_POST["phone"]) && !empty($_POST["pass"])) {
    $phone = $_POST["phone"]; // post email
    $pass = $_POST["pass"]; // post password
    $rs = array();
    $con = mysqli_connect("localhost", "root", "", "fufu_db");
    if ($con) {
        $sql = "SELECT * FROM user WHERE userPhone = '$phone'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            if ($phone == $row['userPhone'] && password_verify($pass, $row['userPass'])) {
                $sqlUpdate = "UPDATE user SET userStatus = 1 WHERE userPhone = '$phone'";
                if (mysqli_query($con, $sqlUpdate)) {
                    if ($row['userRole'] == 1) {
                        $getId = $row['userId'];
                        $sqlHaveRes = "SELECT * FROM user INNER JOIN restaurant ON user.userId = restaurant.userId WHERE user.userId = '$getId'";
                        $result = mysqli_query($con, $sqlHaveRes);
                        if (mysqli_num_rows($result) != 0) {
                            $rowUser = mysqli_fetch_assoc($result);
                            $rs = array("status" => "success",
                                        "userId" => $rowUser['userId'],
                                        "resId" => $rowUser['resId'],
                                        "email" => $rowUser['userEmail'], 
                                        "phone" => $rowUser['userPhone'],
                                        "name" => $rowUser['userFullName'],
                                        "address" => $rowUser['userAddress'],
                                        "gender" => $rowUser['userGender'],
                                        "dob" => $rowUser['userDob'],
                                        "bio" => $rowUser['userBio'],
                                        "role" => $rowUser['userRole'],
                                        "userStatus" => $rowUser['userStatus']);
                        }
                    } else {
                        $rs = array("status" => "success",
                                    "userId" => $row['userId'],
                                    "email" => $row['userEmail'], 
                                    "phone" => $row['userPhone'],
                                    "name" => $row['userFullName'],
                                    "address" => $row['userAddress'],
                                    "gender" => $row['userGender'],
                                    "dob" => $row['userDob'],
                                    "bio" => $row['userBio'],
                                    "role" => $row['userRole'],
                                    "userStatus" => $row['userStatus']);
                    }
                } else $rs = array("status" => "failed", "message" => "ko");
            } else $rs = array("status" => "failed", "message" => "Email or Password incorrect");
        } else $rs = array("status" => "failed", "message" => "Email or Password incorrect");
    } else $rs = array("status" => "failed", "message" => "Couldn't connect to server");
} else $rs = array("status" => "failed", "message" => "All fields are required");

echo json_encode($rs, JSON_PRETTY_PRINT);

?>