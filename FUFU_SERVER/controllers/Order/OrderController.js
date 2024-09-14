const billModel = require("../../model/Bill");
const billDetailModel = require("../../model/BillDetail");


const orderController =  () => {
    const orderControllerObj = {};

    orderControllerObj.insert = async (req, res) => {
        const bill = [req.params.userId, req.params.totalPrice]
        let idBill = null;
        await billModel().insertBill(bill)
            .then((result) => idBill = result[0].lastBillId)
            .catch((err) => {throw err});
        if(idBill != null) {
            let billDetails = [];
            for(let i = 0; i < req.body.length; i++) {
                const billDetail = [
                    idBill,
                    req.body[i].itemId,
                    req.body[i].amount
                ];
                billDetails.push(billDetail);
            }
            billDetailModel().insertBillDetails(billDetails);
            res.status(200);
            res.send("The request has been completed")
        }
    }

    return orderControllerObj;
}

module.exports = orderController;