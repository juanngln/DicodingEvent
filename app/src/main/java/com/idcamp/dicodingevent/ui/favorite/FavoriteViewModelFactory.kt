package com.idcamp.dicodingevent.ui.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteViewModelFactory private constructor(
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: FavoriteViewModelFactory? = null

        fun getInstance(application: Application): FavoriteViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteViewModelFactory(application).also { INSTANCE = it }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(application) as T
            }
            modelClass.isAssignableFrom(FavoriteAddDeleteViewModel::class.java) -> {
                FavoriteAddDeleteViewModel(application) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
