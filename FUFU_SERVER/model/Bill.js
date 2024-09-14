const db = require("../config/db");


const billModel = () => {
    const billModelObj = {};

    billModelObj.insertBill = (bill) => {
        let sql = "insert into bill (userId, totalPrice) values ?;";
        return new Promise((resolve, reject) => {
            db.query (
                sql,
                [[bill]],
                (err, _) => {
                    if(err) reject(err);
                }
            );
            db.query (
                "select LAST_INSERT_ID() as lastBillId;",
                (err, result) => {
                    if(err) reject(err);
                    resolve(result);
                }
            );
        })
    }

    return billModelObj;
}

module.exports = billModel;