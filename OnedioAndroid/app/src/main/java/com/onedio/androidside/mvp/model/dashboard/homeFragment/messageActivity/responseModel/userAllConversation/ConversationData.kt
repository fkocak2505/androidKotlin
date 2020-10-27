package com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.DeletedByKnowledge

class ConversationData {

    @SerializedName("_id")
    @Expose
    val id : String? = null

    @SerializedName("updateDate")
    @Expose
    val updateDate: UpdateDate? = null

    @SerializedName("messages")
    @Expose
    val messaage: List<MessageData>? = null

    @SerializedName("target")
    @Expose
    val target: TargetData? = null

    @SerializedName("me")
    @Expose
    val me: MeData? = null

    @SerializedName("deletedByKnowledge")
    @Expose
    val deletedByKnowledge: DeletedByKnowledge? = null



}