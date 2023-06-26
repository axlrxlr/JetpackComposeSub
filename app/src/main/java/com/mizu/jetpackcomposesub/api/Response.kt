package com.mizu.jetpackcomposesub.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("paging")
	val paging: Paging
)

data class Node(

	@field:SerializedName("main_picture")
	val mainPicture: MainPicture,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)

data class Ranking(

	@field:SerializedName("rank")
	val rank: Int
)

data class Paging(

	@field:SerializedName("next")
	val next: String
)

data class DataItem(

	@field:SerializedName("node")
	val node: Node,

	@field:SerializedName("ranking")
	val ranking: Ranking
)

data class DetailResponse(

	@field:SerializedName("studios")
	val studios: List<StudiosItem>,

	@field:SerializedName("rating")
	val rating: String,

	@field:SerializedName("main_picture")
	val mainPicture: MainPicture,

	@field:SerializedName("synopsis")
	val synopsis: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("start_season")
	val startSeason: StartSeason,

	@field:SerializedName("pictures")
	val pictures: List<PicturesItem>,

	@field:SerializedName("alternative_titles")
	val alternativeTitles: AlternativeTitles,

	@field:SerializedName("media_type")
	val mediaType: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("num_episodes")
	val numEpisodes: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("status")
	val status: String
)

data class StudiosItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class MainPicture(

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("medium")
	val medium: String
)

data class AlternativeTitles(

	@field:SerializedName("synonyms")
	val synonyms: List<String>,

	@field:SerializedName("ja")
	val ja: String,

	@field:SerializedName("en")
	val en: String
)

data class StartSeason(

	@field:SerializedName("year")
	val year: Int,

	@field:SerializedName("season")
	val season: String
)

data class PicturesItem(

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("medium")
	val medium: String
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)


