package com.onedio.androidside.mvp.model.dashboard.homeFragment.commentModel

data class CommentModel(
    var commentId: String,
    var type: String,
    var userName: String,
    var userId: String,
    var profilePhoto: String,
    var text: String,
    var time: String,
    var commentLikeCount: String,
    var replyToId: String?,
    var isCommentLiked: Boolean
)