const itemModel = require('../../model/Item');

const menuController = () => {
    const menuControllerObj = {};

    menuControllerObj.index = async (req, res) => {
        res.status(200);
        res.send(await itemModel().getAllItem(req.params.resId));
    }
    menuControllerObj.create = (req, res) => {
        const newItem = req.body;
        itemModel().insertItem([
            newItem.resId,
            newItem.itemImg,
            newItem.itemName,
            newItem.itemDes,
            newItem.itemPricex
        ]);
        res.status(200);
        res.send("Successfully inserted item.");
    }
    menuControllerObj.delete = (req, res) => {
        itemModel().deleteItem(req.params.itemId);
        res.status(200);
        res.send("Successfully deleted item.");
    }
    menuControllerObj.update = (req, res) => {
        const itemChanged = req.body;
        itemModel().updateItem([
            itemChanged.resId,
            itemChanged.itemImg,
            itemChanged.itemName,
            itemChanged.itemDes,
            itemChanged.itemPrice,
            itemChanged.itemId
        ]);
        res.status(200);
        res.send("Successfully updated item");
    }

    return menuControllerObj;
}

module.exports = menuController;