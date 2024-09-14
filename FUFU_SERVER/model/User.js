const db =  require('../config/db');

const userModel = () => {
    const userModelObj = {};

    userModelObj.getAllUser = async () => {
        let sql = "select * from user";
        return await new Promise((resolve, reject) => {
            db.query(
                sql,
                (err, result) => {
                    if (err) reject(err);
                    resolve(result);
                }
            )
        }); 
    }
    
    return userModelObj;
}

module.exports = userModel;