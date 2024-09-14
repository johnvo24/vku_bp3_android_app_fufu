const PORT = 4000;
const restaurantRouter = require('./RestaurantRouter');
const orderRouter = require('./orderRouter');
const userRouter = require('./userRouter')

function routes(app) {
    app.use('/api/restaurant/', restaurantRouter);
    app.use('/api/user/', userRouter);
    app.use('/api/order/', orderRouter)
    app.listen(PORT);
}

module.exports = routes;