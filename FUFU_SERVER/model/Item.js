const db = require('../config/db');

const itemModel = () => {
    const itemModelObj = {};

    itemModelObj.getAllItem = async (resId) => {
        let itemList = [];
        await new Promise((resolve, reject) => {
            let sql = "select * from item where resId=?";
            db.query(
                sql,
                [resId],
                (err, result) => {
                    if(err) reject(err);
                    resolve(result);
                }
            )
        })
        .then((result) => itemList = result)
        .catch((err) => console.log(err));
        return itemList;
    }
    itemModelObj.insertItem = (newItem) => {
        let sql = "INSERT INTO `item` (`resId`, `itemImg`, `itemName`, `itemDes`, `itemPrice`) VALUES ?";
        db.query(
            sql,
            [[newItem]],
            (err, _) => {
                if(err) throw err;
            }
        )
    }
    itemModelObj.deleteItem = (itemId) => {
        let sql = "delete from item where itemId=?";
        db.query(
            sql,
            [itemId],
            (err, _) => {
                if(err) throw err
            }
        )
    }
    itemModelObj.updateItem = (itemChanged) => {
        let sql = "update item set resId=?, itemImg=?, itemName=?, itemDes=?, itemPrice=? where itemId=?";
        db.query(
            sql,
            itemChanged,
            (err, _) => {
                if(err) throw err
            }
        )
    }

    return itemModelObj;
}

module.exports = itemModel;