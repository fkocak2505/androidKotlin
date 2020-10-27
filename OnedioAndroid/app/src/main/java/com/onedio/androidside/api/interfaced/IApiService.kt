package com.onedio.androidside.api.interfaced

import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4ArticleComments
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4LikeAndUnLike
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleComment.response.Response4SendComment
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.emojiReaction.Response4EmojiReaction
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.newResponse.Response4ArticleFeedDetails
import com.onedio.androidside.mvp.model.dashboard.homeFragment.articleFeedDetail.responseModel.oldResponse.Response4ArticleFeedDetail
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.Response4ConversationIDMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.deleteConversation.Response4DeleteConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.sendMessage.Response4SendMessage
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.conversation.responseModel.startConversation.Response4StartConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.messageActivity.responseModel.userAllConversation.Response4UserAllConversation
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.addFavorite.Response4AddFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.feedModel.Response4FeedModel
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.newArticleFeedModel.Response4ArticlesFeed
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.sliderModel.Response4ArticleSlider
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetConfig.Response4WidgetConfig
import com.onedio.androidside.mvp.model.dashboard.homeFragment.responseModel.widgetDataWithTagId.Response4WidgetDataWithTagId
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.categoryDetail.responseModel.Response4CategoryDetailItems
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.response.Response4AllCategory
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFavoriteArticle
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UserFollowerAndFollowing
import com.onedio.androidside.mvp.model.dashboard.profil.Response4UsersProfile
import com.onedio.androidside.mvp.model.dashboard.profileDetail.changePassword.responseModel.Response4ChangePassword
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.Response4UpdateUserProfilePhoto
import com.onedio.androidside.mvp.model.dashboard.profileDetail.responseModel.countryList.Response4CountryList
import com.onedio.androidside.mvp.model.dashboard.responseModel.Response4UserProfileInfo
import com.onedio.androidside.mvp.model.dashboard.responseModel.content.Response4UserContentData
import com.onedio.androidside.mvp.model.loginAndRegister.login.forgotPassword.responseModel.Response4ForgotPassword
import com.onedio.androidside.mvp.model.loginAndRegister.login.responseModel.Response4Login
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4IsMailExist
import com.onedio.androidside.mvp.model.loginAndRegister.register.responseModel.Response4Register
import com.onedio.androidside.mvp.model.loginAndRegister.responseModel.Response4Token
import com.onedio.androidside.mvp.model.noDesigned.blockedUser.responseModel.Response4UserBlockedList
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.article.Response4Article
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.tags.Response4Tags
import com.onedio.androidside.mvp.model.welcomeDashboard.search.dataModel.users.Response4UserSearch
import com.onedio.androidside.mvp.model.welcomeDashboard.search.responseModel.Response4MostSharedArticle
import com.onedio.androidside.mvp.view.dashboard.homeFragment.profile.Response4UserArticle
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface IApiService {
    @POST("/v0.1.0/token")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getToken(@Body type: RequestBody): Call<Response4Token>

    @POST("/v0.1.0/oauth/authorize")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun doLogin(
        @Header("oio-token") oioToken: String,
        @Body type: RequestBody
    ): Call<Response4Login>

    @GET("/v0.1.0/isemailexist/{email}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun isMailExist(
        @Header("oio-token") oioToken: String,
        @Path("email") email: String
    ): Call<Response4IsMailExist>

    @GET("/v0.1.0/isusernameexist/{userName}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun isUserNameExist(
        @Header("oio-token") oioToken: String,
        @Path("userName") email: String
    ): Call<Response4IsMailExist>

    @POST("/v0.1.0/oauth/register")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun doRegister(
        @Header("oio-token") oioToken: String,
        @Body type: RequestBody
    ): Call<Response4Register>


    @GET("/v3/1/1/1/users/{userId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUsersProfileData(
        @Header("Authorization") authorization: String,
        @Path("userId") email: String
    ): Call<Response4UsersProfile>

    @GET("/v3/1/1/1/users/{userId}/favorites")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserFavoriteArticles(
        @Header("Authorization") authorization: String,
        @Path("userId") email: String
    ): Call<Response4UserFavoriteArticle>

    @GET("/v3/1/1/1/users/{userId}/favorites")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserFavoriteArticlesInfinite(
        @Header("Authorization") authorization: String, @Path("userId") email: String,
        @Query(
            "page"
        ) page: Int
    ): Call<Response4UserFavoriteArticle>


    @GET("/v3/1/1/1/users/{userId}/articles")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserArticles(
        @Header("Authorization") authorization: String,
        @Path("userId") email: String
    ): Call<Response4UserArticle>

    @GET("/v3/1/1/1/users/{userId}/articles")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserArticlesInfinite(
        @Header("Authorization") authorization: String, @Path("userId") email: String,
        @Query(
            "page"
        ) page: Int
    ): Call<Response4UserArticle>


    @GET("/v3/1/1/1/users/{userId}/followings")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserFollowings(
        @Header("Authorization") authorization: String, @Path("userId") email: String, @Query(
            "page"
        ) page: Int
    ): Call<Response4UserFollowerAndFollowing>

    @GET("/v3/1/1/1/users/{userId}/followers")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserFollowers(
        @Header("Authorization") authorization: String, @Path("userId") email: String, @Query(
            "page"
        ) page: Int
    ): Call<Response4UserFollowerAndFollowing>


    @GET("/v0.1.0/users/me")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserProfileInfo(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String
    ): Call<Response4UserProfileInfo>

    @GET("/v0.1.0/users/{userId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getAnotherUserProfile(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "userId"
        ) userId: String
    ): Call<Response4UserProfileInfo>

    @PUT("/v0.1.0/users/me")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun updateUserProfileInfo(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Body type: RequestBody
    ): Call<Response4UpdateUserProfileInfo>

    @PUT("/v3/1/1/1/users/me")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun updateUserProfileInfoNew(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Body type: RequestBody
    ): Call<Response4UpdateUserProfileInfo>

    @POST("/v0.1.0/users/me/photo")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun updateUserProfilePhoto(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Body type: RequestBody
    ): Call<Response4UpdateUserProfilePhoto>

    @POST("/v0.1.0/oauth/forgot-password")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun forgotPassword(
        @Header("oio-token") oioToken: String,
        @Body type: RequestBody
    ): Call<Response4ForgotPassword>


    @GET("/v2/1/1/4/articles/getUserArticles/{userId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserArticleData(@Path("userId") userId: String): Call<Response4UserContentData>

    @GET("/v2/1/1/4/articles/getUserFavorites/{userId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserFavoriteData(@Path("userId") userId: String): Call<Response4UserContentData>

    @PUT("/v0.1.0/users/me/changePasswordByOldPassword")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun changePassword(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Body type: RequestBody
    ): Call<Response4ChangePassword>

    @GET("/v0.1.0/users/me/blocked-users")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserBlockedListData(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String
    ): Call<Response4UserBlockedList>

    @PUT("/v0.1.0/users/{userName}/block")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun doUserBlocked(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "userName"
        ) userName: String
    ): Call<Response4DoBlockedUser>

    @GET("/v0.1.0/countries")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getCountryList(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String
    ): Call<Response4CountryList>

    @GET("/v0.1.0/user/conversation")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getUserAllConversation(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String
    ): Call<Response4UserAllConversation>

    @GET("/v0.1.0/user/conversation/{conversationID}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getConversationIDMessages(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "conversationID"
        ) conversationID: String
    ): Call<Response4ConversationIDMessage>


    @POST("/v0.1.0/user/conversation/{conversationID}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun sendMessage(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "conversationID"
        ) conversationID: String,
        @Body type: RequestBody
    ): Call<Response4SendMessage>

    @GET("/v0.1.0/user/conversation/with/{userID}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun startConversation(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "userID"
        ) userId: String
    ): Call<Response4StartConversation>

    @DELETE("/v0.1.0/user/conversation/{conversationID}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun deleteConversation(
        @Header("oio-token") oioToken: String,
        @Header("Authorization") authorization: String,
        @Path(
            "conversationID"
        ) conversationID: String
    ): Call<Response4DeleteConversation>

    @GET("/v2/1/1/5/mostSharedFeed?page=1&perPage=4")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getMostSharedArticle(): Call<Response4MostSharedArticle>

    @GET("/v3/1/1/1/feed?sort=popular&duration=1week&limit=4")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getMostPopularArticle(): Call<Response4ArticlesFeed>


    @GET("/api/mobile/search/{type}/{keyword}?sort=created desc")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getSearchData(
        @Path("type") type: String,
        @Path("keyword") keyword: String,
        @Query("page") page: Int,
        @Query(
            "perPage"
        ) perPage: Int
    ): Call<String>

    @GET("/v3/1/1/1/categories")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getAllCategoryAsTree(): Call<Response4AllCategory>

    @GET("/v3/1/1/1/tags")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getBadgeCategory(): Call<Response4AllCategory>

    @GET("/v2/1/1/5/feed")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticle(
        @Header("Authorization") authorization: String, @Query("page") page: Int, @Query(
            "perPage"
        ) perPage: Int
    ): Call<Response4FeedModel>

    @GET("/v3/1/1/1/trending")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getTrendData(
        @Header("Authorization") authorization: String, @Query("page") page: Int
    ): Call<Response4ArticlesFeed>

    /*@POST("/v2/1/1/4/articles/me/{legacyId}/favorites")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun addFavoriteArticle(@Header("Authorization") authorization: String, @Path("legacyId") legacyId: Long): Call<Response4AddFavoriteArticle>*/

    @POST("v3/1/1/1/articles/{legacyId}/favorite")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun addFavoriteArticle(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long
    ): Call<Response4AddFavoriteArticle>


    /*@DELETE("/v2/1/1/4/articles/me/{legacyId}/favorites")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun deleteFavoriteArticle(@Header("Authorization") authorization: String, @Path("legacyId") legacyId: Long): Call<Response4AddFavoriteArticle>*/

    @DELETE("v3/1/1/1/articles/{legacyId}/favorite")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun deleteFavoriteArticle(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long
    ): Call<Response4AddFavoriteArticle>


    @GET("/v2/1/1/5/category/feed/{categoryId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getCategoryItem(
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): Call<Response4CategoryDetailItems>

    @GET("/v2/1/1/5/articles/{legacyId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleFeedDetail(@Path("legacyId") legacyId: Long): Call<Response4ArticleFeedDetail>


    @GET("/v3/1/1/1/articles/{legacyId}/comments")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleComments(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long,
        @Query("page") page: Int
    ): Call<Response4ArticleComments>

    @GET("/v3/1/1/1/articles/{legacyId}/read")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun sendReadState(@Path("legacyId") legacyId: Long): Call<String>


    @GET("/v3/1/1/1/articles/{legacyId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleFeedDetailNew(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long
    ): Call<Response4ArticleFeedDetails>

    @GET("/v3/1/1/1/config")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getWidgetConfig(): Call<Response4WidgetConfig>

    @GET("/v3/1/1/1/headlines")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleSliderData(): Call<Response4ArticleSlider>

    @GET("/v3/1/1/1/feed")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticlesFeed(
        @Header("Authorization") authorization: String, @Query("page") page: Int, @Query(
            "limit"
        ) perPage: Int
    ): Call<Response4ArticlesFeed>

    @GET("/v2/1/1/3/feed/tag/{tagId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getWidgetDataWithTagId(
        @Path("tagId") tagId: String,
        @Query("page") page: Int,
        @Query("limit") perPage: Int
    ): Call<Response4WidgetDataWithTagId>

    @GET("/v2/1/1/3/feed/category/id/{categoryId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getCategoryWidgetDataWithTargetId(
        @Path("categoryId") categoryId: String, @Query("page") page: Int, @Query(
            "perPage"
        ) perPage: Int, @Query(
            "sort"
        ) dateFilterType: String, @Query("duration") sortType: String
    ): Call<Response4FeedModel>

    @GET("/v3/1/1/1/tags/{tagId}/feed")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleFeedCategoryWidgetDataWidthTagId(
        @Path("tagId") tagId: String, @Query("page") page: Int, @Query(
            "limit"
        ) perPage: Int
    ): Call<Response4ArticlesFeed>

    @GET("/v3/1/1/1/categories/{categoryId}/feed")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getArticleFeedCategoryWidgetDataWithCategoryId(
        @Path("categoryId") categoryId: String, @Query("page") page: Int,
        @Query(
            "limit"
        ) perPage: Int,
        @Query(
            "sort"
        ) dateFilterType: String, @Query("duration") sortType: String
    ): Call<Response4ArticlesFeed>

    @GET("/v2/1/1/5/category/feed/{categoryName}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getCategoryWidgetDataByName(
        @Path("categoryName") categoryName: String, @Query("page") page: Int, @Query(
            "perPage"
        ) perPage: Int
    ): Call<Response4FeedModel>

    @POST("/v2/1/1/3/articles/{articleId}/reactions/{emojiCode}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun upReaction(
        @Path("articleId") articleId: String,
        @Path("emojiCode") emojiCode: String
    ): Call<Response4EmojiReaction>

    @POST("/v3/1/1/1/articles/{legacyId}/reactions/{emojiCode}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun upReactionNew(
        @Path("legacyId") legacyId: Long,
        @Path("emojiCode") emojiCode: String
    ): Call<Response4LikeAndUnLike>

    @DELETE("/v2/1/1/3/articles/{articleId}/reactions/{emojiCode}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun downReaction(
        @Path("articleId") articleId: String,
        @Path("emojiCode") emojiCode: String
    ): Call<Response4EmojiReaction>

    @DELETE("/v3/1/1/1/articles/{legacyId}/reactions/{emojiCode}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun downReactionNew(
        @Path("legacyId") legacyId: Long,
        @Path("emojiCode") emojiCode: String
    ): Call<Response4LikeAndUnLike>

    @GET("/api/mobile/search/article/{searchedWord}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun filterSearchedContentByParams(
        @Path("searchedWord") searchedWord: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query(
            "date"
        ) date: String,
        @Query("sort") sort: String
    ): Call<Response4Article>

    @GET("/api/mobile/search/tag/{searchedWord}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun filterSearchedTagByParams(
        @Path("searchedWord") searchedWord: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): Call<Response4Tags>

    @GET("/api/mobile/search/user/{searchedWord}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun filterSearchedUserByParams(
        @Path("searchedWord") searchedWord: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): Call<Response4UserSearch>

    @POST("/v2/1/1/5/comment/me/{legacyId}")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun sendComment(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long,
        @Body type: RequestBody
    ): Call<Response4SendComment>

    @POST("/v3/1/1/1/articles/{legacyId}/comments")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun sendCommentNew(
        @Header("Authorization") authorization: String,
        @Path("legacyId") legacyId: Long,
        @Body type: RequestBody
    ): Call<Response4LikeAndUnLike>

    @POST("v3/1/1/1/comments/{commentId}/like")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun likeComment(
        @Header("Authorization") authorization: String,
        @Path("commentId") commentId: String
    ): Call<Response4LikeAndUnLike>

    @DELETE("v3/1/1/1/comments/{commentId}/like")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun unLike(
        @Header("Authorization") authorization: String,
        @Path("commentId") commentId: String
    ): Call<Response4LikeAndUnLike>

    @GET("/v3/1/1/1/tags/{tagId}/feed")
    @Headers("ContentNeYapmaliyim-Type: application/json;charset=UTF-8")
    fun getTagArticle(
        @Path("tagId") tagId: String, @Query("page") page: Int, @Query("limit") perPage: Int,
        @Query(
            "sort"
        ) dateFilterType: String, @Query("duration") sortType: String
    ): Call<Response4ArticlesFeed>


    @POST("/v1/onedio-feedback-collector")
    fun sendFeedBack(@Header("x-api-key") xApiKey: String, @Body type: RequestBody): Call<String>

}