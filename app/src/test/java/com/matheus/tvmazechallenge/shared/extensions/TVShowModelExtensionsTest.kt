package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowScheduleEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowModel
import com.matheus.tvmazechallenge.shared.model.TVShowScheduleModel
import org.junit.Assert
import org.junit.Test

class TVShowModelExtensionsTest {

    @Test
    fun `should create TVShowEntity with valid image`() {
        val tvShow = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = ""),
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )
        val expectedTvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = "medium",
            schedule = TVShowScheduleEntity("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        Assert.assertEquals(expectedTvShowEntity, tvShow.toEntity())
    }

    @Test
    fun `should create TVShowEntity with invalid image`() {
        val tvShow = TVShowModel(
            id = 1,
            name = "name",
            url = "url",
            image = null,
            schedule = TVShowScheduleModel("", emptyList()),
            genres = emptyList(),
            summary = ""
        )
        val expectedTvShowEntity = TVShowEntity(
            id = 1,
            name = "name",
            url = "url",
            poster = Constants.DEFAULT_TVMAZE_IMAGE,
            schedule = TVShowScheduleEntity("", emptyList()),
            genres = emptyList(),
            summary = ""
        )

        Assert.assertEquals(expectedTvShowEntity, tvShow.toEntity())
    }
}