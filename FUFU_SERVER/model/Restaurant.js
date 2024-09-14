const db = require('../config/db');

const RestaurantModel = () => {
    const restarantModel = {};

    restarantModel.getRestaurant = async (resId) => {
        let restaurantList = [];
        restaurantList = await new Promise((resolve, reject) => {
            let sql = "SELECT R.resId,R.resName,R.resAvt,R.resAddress,R.resLatitude,R.resLongitude,R.resPhone,R.resDescription,R.resDescription,R.resOpenTime,R.resSeat,R.createdAt,R.updatedAt,R.userId,COALESCE(AVG(V.revPoint), 0) as revAvg, COUNT(V.reviewId) as revAmount FROM restaurant R LEFT JOIN review V ON R.resId = V.resId WHERE R.userId=? GROUP BY R.resId,R.resName,R.resAvt,R.resAddress,R.resLatitude,R.resLongitude,R.resPhone,R.resDescription,R.resDescription,R.resOpenTime,R.resSeat,R.createdAt,R.updatedAt,R.userId;";
            db.query(
                sql,
                [resId],
                (err, result) => {
                    if(err) throw err;
                    resolve(result)
                }
            )
        })
        .then((result) => restaurantList = result)
        .catch((err) => console.log(err));

        return restaurantList;
    }
    restarantModel.createRestaurant = (newRestaurant) => {
        let sql = "INSERT INTO `restaurant` (`resName`, `resAvt`, `resAddress`, `resLatitude`, `reslongitude`, `resPhone`, `resDescription`, `resOpenTime`, `resSeat`, `userId`) VALUES ?";
        db.query(
            sql,
            [[newRestaurant]],
            (err, _ ) => {
                if (err) throw err;
            }
        );
    }

    return restarantModel;
}

module.exports = RestaurantModel;