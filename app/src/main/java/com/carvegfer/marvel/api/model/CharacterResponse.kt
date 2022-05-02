package com.carvegfer.marvel.api.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail,

	@field:SerializedName("urls")
	val urls: List<UrlsItem?>,

	@field:SerializedName("stories")
	val stories: Stories,

	@field:SerializedName("series")
	val series: Series,

	@field:SerializedName("comics")
	val comics: Comics,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("modified")
	val modified: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("resourceURI")
	val resourceURI: String,

	@field:SerializedName("events")
	val events: Events
)

data class Events(

	@field:SerializedName("collectionURI")
	val collectionURI: String,

	@field:SerializedName("available")
	val available: Int,

	@field:SerializedName("returned")
	val returned: Int,

	@field:SerializedName("items")
	val items: List<Any?>
)

data class Stories(

	@field:SerializedName("collectionURI")
	val collectionURI: String,

	@field:SerializedName("available")
	val available: Int,

	@field:SerializedName("returned")
	val returned: Int,

	@field:SerializedName("items")
	val items: List<ItemsItem?>
)

data class Comics(

	@field:SerializedName("collectionURI")
	val collectionURI: String,

	@field:SerializedName("available")
	val available: Int,

	@field:SerializedName("returned")
	val returned: Int,

	@field:SerializedName("items")
	val items: List<ItemsItem?>
)

data class UrlsItem(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String
)

data class Series(

	@field:SerializedName("collectionURI")
	val collectionURI: String,

	@field:SerializedName("available")
	val available: Int,

	@field:SerializedName("returned")
	val returned: Int,

	@field:SerializedName("items")
	val items: List<ItemsItem?>
)

data class Thumbnail(

	@field:SerializedName("path")
	val path: String,

	@field:SerializedName("extension")
	val extension: String
)

data class ItemsItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("resourceURI")
	val resourceURI: String,

	@field:SerializedName("type")
	val type: String
)
