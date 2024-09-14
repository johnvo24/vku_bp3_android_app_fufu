const userModel = require("../model/User");

const userController = () => {
    const userControllerObj = {};

    userControllerObj.getAllUser = async (req, res) => {
        res.status(200);
        res.send(await userModel().getAllUser());
    }

    return userControllerObj;
}

module.exports = userController;