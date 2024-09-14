const galleryModel = require("../../model/Gallery");

const galleryController = () => {
    const galleryControllerObj = {};

    galleryControllerObj.uploadFile = async (req, res) => {
        const file = await galleryModel().upLoadFile(req.file);
        console.log(file);
        if(file.path != null) {
            galleryModel().insertResource([
                req.params.resId,
                file.path,
                file.mimetype
            ]);
        }
        res.status(200);
    }
    galleryControllerObj.getResources = async (req, res) => {
        res.send(await galleryModel().getResources(req.params.resId));
        res.status(200);
    }
    // galleryControllerObj.dele
    
    return galleryControllerObj;
}

module.exports = galleryController;