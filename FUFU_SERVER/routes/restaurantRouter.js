const express = require('express');
const menuController = require('../controllers/Restaurant/MenuController');
const restaurantController = require('../controllers/Restaurant/RestaurantController');
const reviewController = require('../controllers/Restaurant/ReviewController');
const galleryController = require('../controllers/Restaurant/GalleryController');
const restaurantRouter = express.Router();

const fileUploader = require('../middleware/cloudinaryUploader')

restaurantRouter.get('/:resId', restaurantController().index);

restaurantRouter.get('/menu/item/:resId', menuController().index);
restaurantRouter.post('/menu/item/add', menuController().create);
restaurantRouter.delete('/menu/item/delete/:itemId', menuController().delete);
restaurantRouter.post('/menu/item/update', menuController().update);

restaurantRouter.get('/review/me/:resId/:userId', reviewController().getReviewByUserId);
restaurantRouter.get('/review/:resId/:userId', reviewController().getReviewByResId);
restaurantRouter.delete('/review/delete/:reviewId', reviewController().deleteReview);
restaurantRouter.post('/review/insert', reviewController().insertReview);

restaurantRouter.post('/gallery/upload/:resId', fileUploader.single('gallery'), galleryController().uploadFile);
restaurantRouter.get('/gallery/:resId', galleryController().getResources)

module.exports = restaurantRouter;