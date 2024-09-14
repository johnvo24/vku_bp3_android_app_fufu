const db = require("../config/db");


const billDetailModel = () => {
    const billDetailModelObj = {};

    billDetailModelObj.insertBillDetails = (billDetail) => {
        let sql = "insert into billdetail (idBill, itemId, amount) values ?";
        db.query (
            sql,
            [billDetail],
            (err, _) => {
                if(err) throw (err);
            }
        )
    }

    return billDetailModelObj;
}

module.exports = billDetailModel;