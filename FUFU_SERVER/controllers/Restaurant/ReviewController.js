const reviewModel = require("../../model/Review")

const reviewController = () => {
    const reviewControllerObj = {}

    reviewControllerObj.getReviewByUserId = async (req, res) => {
        res.status(200);
        res.send(await reviewModel().getReviewByUserId(req.params.resId, req.params.userId));
    }
    reviewControllerObj.getReviewByResId = async (req, res) => {
        res.status(200);
        res.send(await reviewModel().getReviewByResId(req.params.resId, req.params.userId));
    }
    reviewControllerObj.deleteReview = (req, res) => {
        reviewModel().deleteReview(req.params.reviewId);
        res.status(200);
        res.send("Completed")
    }
    reviewControllerObj.insertReview = (req, res) => {
        const review = req.body
        reviewModel().insertReview([
            review.resId,
            review.userId,
            review.revPoint,
            review.revComment
        ]);
        res.status(200);
        res.send("Completed")
    }

    return reviewControllerObj;
}

module.exports = reviewController;