const db = require("../config/db");

const galleryModel = () => {
    const galleryModelObj = {};

    galleryModelObj.insertResource = (newResource) => {
        let sql = "insert into restaurant_resource (`resId`, `resourcePath`, `resourceType`) values ?";
        db.query(
            sql,
            [[newResource]],
            (err, _) => {
                if(err) throw err;
            }
        );
    }
    galleryModelObj.upLoadFile = async (fileObject) => {
        try {
            return fileObject;
        } catch (error) {
            console.log(error);
            return null;
        }
    }
    galleryModelObj.getResources = async (resId) => {
        let resources = [];
        await new Promise((resolve, reject) => {
            let sql = "select * from restaurant_resource where resId=?";
            db.query(
                sql,
                [resId],
                (err, result) => {
                    if(err) throw err;
                    resolve(result);
                 }
            )
        })
        .then((result) => resources = result)
        .catch((err) => console.log(err));
        return resources;
    }
    galleryModelObj.deleteResource = (resourceId) => {
        let sql = "delete from restaurant_resource where resourceId=?";
        db.query(
            sql,
            [resourceId],
            (err, _) => {
                if(err) throw err;
            }
        )
    }



    return galleryModelObj;
}

module.exports = galleryModel;