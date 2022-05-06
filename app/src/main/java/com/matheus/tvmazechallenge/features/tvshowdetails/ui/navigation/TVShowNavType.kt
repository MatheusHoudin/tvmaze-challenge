package com.matheus.tvmazechallenge.features.tvshowdetails.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.matheus.tvmazechallenge.features.tvshows.entity.TVShowEntity

class TVShowNavType : NavType<TVShowEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TVShowEntity? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): TVShowEntity {
        return Gson().fromJson(value, TVShowEntity::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: TVShowEntity) {
        bundle.putParcelable(key, value)
    }
}