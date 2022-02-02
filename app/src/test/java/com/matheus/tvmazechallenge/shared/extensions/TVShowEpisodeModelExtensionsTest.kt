package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowEpisodeEntity
import com.matheus.tvmazechallenge.features.tvshowdetails.entity.TVShowSeasonEpisodesEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.TVShowEpisodeModel
import org.junit.Assert
import org.junit.Test

class TVShowEpisodeModelExtensionsTest {

    @Test
    fun `should return a list of TVShowSeasonEpisodesEntity`() {
        val tvShowEpisodes = listOf(
            TVShowEpisodeModel(
                name = "a",
                season = 1,
                number = 1,
                summary = null,
                image = null
            ),
            TVShowEpisodeModel(
                name = "b",
                season = 1,
                number = 2,
                summary = "summary",
                image = ImageModel(medium = "medium", original = null)
            ),
            TVShowEpisodeModel(
                name = "c",
                season = 2,
                number = 1,
                summary = null,
                image = null
            ),
        )
        val expectedTVShowSeasonEpisodes = listOf(
            TVShowSeasonEpisodesEntity(
                season = "1",
                episodes = mutableListOf(
                    TVShowEpisodeEntity(
                        name = "a",
                        season = "1",
                        number = "1",
                        summary = "",
                        image = Constants.DEFAULT_TVMAZE_IMAGE
                    ),
                    TVShowEpisodeEntity(
                        name = "b",
                        season = "1",
                        number = "2",
                        summary = "summary",
                        image = "medium"
                    )
                )
            ),
            TVShowSeasonEpisodesEntity(
                season = "2",
                episodes = mutableListOf(
                    TVShowEpisodeEntity(
                        name = "c",
                        season = "2",
                        number = "1",
                        summary = "",
                        image = Constants.DEFAULT_TVMAZE_IMAGE
                    )
                )
            )
        )

        Assert.assertEquals(
            expectedTVShowSeasonEpisodes,
            tvShowEpisodes.toTVShowSeasonEpisodesEntity()
        )
    }
}