const restaurantModel = require("../../model/Restaurant");

const restaurantController = () => {
    const resController = {};

    resController.index = async (req, res) => {
        res.status(200);
        res.send(await restaurantModel().getRestaurant(req.params.resId));
    }

    return resController;
}

module.exports = restaurantController;