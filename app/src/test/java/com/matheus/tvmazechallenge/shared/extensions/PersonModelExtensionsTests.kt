package com.matheus.tvmazechallenge.shared.extensions

import com.matheus.tvmazechallenge.features.people.entity.PersonEntity
import com.matheus.tvmazechallenge.shared.Constants
import com.matheus.tvmazechallenge.shared.model.ImageModel
import com.matheus.tvmazechallenge.shared.model.PersonModel
import org.junit.Assert
import org.junit.Test

class PersonModelExtensionsTests {

    @Test
    fun `should create PersonModel with valid image`() {
        val personModel = PersonModel(
            id = 1,
            name = "name",
            url = "url",
            image = ImageModel(medium = "medium", original = null)
        )
        val expectedEntity = PersonEntity(
            id = 1,
            name = "name",
            url = "url",
            image = "medium"
        )

        Assert.assertEquals(expectedEntity, personModel.toEntity())
    }

    @Test
    fun `should create PersonModel with null image`() {
        val personModel = PersonModel(
            id = 1,
            name = "name",
            url = "url",
            image = null
        )
        val expectedEntity = PersonEntity(
            id = 1,
            name = "name",
            url = "url",
            image = Constants.DEFAULT_TVMAZE_IMAGE
        )

        Assert.assertEquals(expectedEntity, personModel.toEntity())
    }
}