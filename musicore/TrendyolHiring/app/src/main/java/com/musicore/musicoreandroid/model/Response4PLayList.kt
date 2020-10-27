package com.musicore.musicoreandroid.model

import com.google.gson.annotations.SerializedName

class Response4PLayList(
    @SerializedName("playlists")
    var playlists: List<PlayLists>,
    @SerializedName("meta")
    var meta: Meta
) {

}

data class Meta(
    @SerializedName("returnedCount")
    var returnedcount: Int
)

data class PlayLists(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("trackCount")
    var trackcount: Int,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("images")
    var images: List<Images>,
    @SerializedName("description")
    val description: String,
    @SerializedName("favoriteCount")
    var favoritecount: Int,
    @SerializedName("freePlayCompliant")
    var freeplaycompliant: Boolean,
    @SerializedName("links")
    var links: Links
)

data class Links(
    @SerializedName("members")
    var members: Members,
    @SerializedName("tracks")
    var tracks: Tracks,
    @SerializedName("tags")
    var tags: Tags,
    @SerializedName("sampleArtists")
    var sampleartists: Sampleartists
)

data class Sampleartists(
    @SerializedName("ids")
    var ids: List<String>,
    @SerializedName("href")
    val href: String
)

data class Tags(
    @SerializedName("ids")
    var ids: List<String>,
    @SerializedName("href")
    val href: String
)

data class Tracks(
    @SerializedName("href")
    val href: String
)

data class Members(
    @SerializedName("ids")
    var ids: List<String>,
    @SerializedName("href")
    val href: String
)

data class Images(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("contentId")
    val contentid: String,
    @SerializedName("width")
    var width: Int,
    @SerializedName("height")
    var height: Int,
    @SerializedName("isDefault")
    var isdefault: Boolean,
    @SerializedName("version")
    var version: Int,
    @SerializedName("imageType")
    val imagetype: String
)