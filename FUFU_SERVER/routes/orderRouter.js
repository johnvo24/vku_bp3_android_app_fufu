const express = require('express');
const orderController = require('../controllers/Order/OrderController');
const orderRouter = express.Router();

orderRouter.post('/add/:userId/:totalPrice', orderController().insert);

module.exports = orderRouter;