const express = require('express');
const userController = require('../controllers/UserController');
const userRouter = express.Router();

userRouter.get('/get_all_user', userController().getAllUser);

module.exports = userRouter;