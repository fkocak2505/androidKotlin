package com.musicore.nestedrecyclerview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response4ArticleDetail(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("updateDate")
	val updateDate: String? = null,

	@field:SerializedName("redirectUrl")
	val redirectUrl: Any? = null,

	@field:SerializedName("comments")
	val comments: MutableList<CommentsItem?>? = null,

	@field:SerializedName("flags")
	val flags: Flags? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("isUserFavorite")
	val isUserFavorite: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("tags")
	val tags: MutableList<String?>? = null,

	@field:SerializedName("layout")
	val layout: String? = null,

	@field:SerializedName("badge")
	val badge: MutableList<Badge?>? = null,

	@field:SerializedName("entries")
	val entries: MutableList<EntriesItem?>? = null,

	@field:SerializedName("stats")
	val stats: Stats? = null,

	@field:SerializedName("legacyId")
	val legacyId: Int? = null,

	@field:SerializedName("reactions")
	val reactions: MutableList<ReactionsItem?>? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: MutableList<CategoriesItem?>? = null,

	@field:SerializedName("showInWebview")
	val showInWebview: Boolean? = null,

	@field:SerializedName("authors")
	val authors: MutableList<AuthorsItem?>? = null,

	@field:SerializedName("createDate")
	val createDate: String? = null
)

data class Replies(

	@field:SerializedName("data")
	val data: MutableList<Any?>? = null
)

data class CommentsItem(

	@field:SerializedName("parent")
	val parent: Any? = null,

	@field:SerializedName("replies")
	val replies: Replies? = null,

	@field:SerializedName("ratings")
	val ratings: Ratings? = null,

	@field:SerializedName("replyTo")
	val replyTo: Any? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("target")
	val target: Target? = null,

	@field:SerializedName("createDate")
	val createDate: String? = null
)

data class Image(

	@field:SerializedName("alternates")
	val alternates: Alternates? = null
)

data class Urls(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("source")
	val source: String? = null
)

data class BadgeItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("icons")
	val icons: Icons? = null
)

data class Alternates(

	@field:SerializedName("standardResolution")
	val standardResolution: StandardResolution? = null,

	@field:SerializedName("highResolution")
	val highResolution: HighResolution? = null
)

data class HighResolution(

	@field:SerializedName("url")
	val url: String? = null
)

data class CategoriesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("icons")
	val icons: Icons? = null
)

data class Target(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Metadata(

	@field:SerializedName("unordered")
	val unordered: Boolean? = null,

	@field:SerializedName("no_taglinks")
	val noTaglinks: Boolean? = null,

	@field:SerializedName("hidein")
	val hidein: Hidein? = null,

	@field:SerializedName("fittoscreen")
	val fittoscreen: Boolean? = null,

	@field:SerializedName("extra_media")
	val extraMedia: String? = null
)

data class Stats(

	@field:SerializedName("shares")
	val shares: Int? = null,

	@field:SerializedName("comments")
	val comments: Int? = null,

	@field:SerializedName("reactions")
	val reactions: Int? = null,

	@field:SerializedName("views")
	val views: Int? = null
)

data class ReactionsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("icons")
	val icons: Icons? = null
)

data class Ratings(

	@field:SerializedName("dislikes")
	val dislikes: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)

data class User(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("country")
	val country: Any? = null,

	@field:SerializedName("birthdate")
	val birthdate: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("city")
	val city: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("flags")
	val flags: Flags? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("slogan")
	val slogan: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Flags(

	@field:SerializedName("approvedEmail")
	val approvedEmail: Boolean? = null
)

data class EmbedArticle(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("updateDate")
	val updateDate: String? = null,

	@field:SerializedName("redirectUrl")
	val redirectUrl: Any? = null,

	@field:SerializedName("flags")
	val flags: Flags? = null,

	@field:SerializedName("publishDate")
	val publishDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("isUserFavorite")
	val isUserFavorite: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("layout")
	val layout: String? = null,

	@field:SerializedName("badge")
	val badge: MutableList<BadgeItem?>? = null,

	@field:SerializedName("stats")
	val stats: Stats? = null,

	@field:SerializedName("legacyId")
	val legacyId: Int? = null,

	@field:SerializedName("reactions")
	val reactions: MutableList<ReactionsItem?>? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: MutableList<CategoriesItem?>? = null,

	@field:SerializedName("showInWebview")
	val showInWebview: Boolean? = null,

	@field:SerializedName("authors")
	val authors: MutableList<AuthorsItem?>? = null,

	@field:SerializedName("createDate")
	val createDate: String? = null
)

data class AuthorsItem(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("city")
	val city: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("flags")
	val flags: Flags? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("slogan")
	val slogan: Any? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("occupation")
	val occupation: String? = null

)

data class Size(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class StandardResolution(

	@field:SerializedName("url")
	val url: String? = null
)

data class Icons(

	@field:SerializedName("svg")
	val svg: String? = null,

	@field:SerializedName("png")
	val png: String? = null
)

data class Badge(
	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icons")
	val icons: Icons? = null

)

data class EntriesItem(

	@field:SerializedName("mode")
	val mode: String? = null,

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("internaldata")
	val internaldata: Internaldata? = null,

	@field:SerializedName("embedArticle")
	val embedArticle: EmbedArticle? = null,

	@field:SerializedName("embedSource")
	val embedSource: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("html")
	val html: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class Internaldata(

	@field:SerializedName("embedfrom")
	val embedfrom: String? = null,

	@field:SerializedName("size")
	val size: Size? = null,

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("file")
	val file: String? = null
)

data class Hidein(
	val any: Any? = null
)
