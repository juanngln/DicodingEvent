package com.idcamp.dicodingevent.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idcamp.dicodingevent.database.FavoriteEvent
import com.idcamp.dicodingevent.repository.FavoriteEventRepository

class FavoriteAddDeleteViewModel(application: Application) : ViewModel() {
    private val mFavoriteEventRepository: FavoriteEventRepository = FavoriteEventRepository(application)

    fun getFavoriteEventById(id: Int): LiveData<FavoriteEvent> {
        return mFavoriteEventRepository.getFavoriteEventById(id)
    }

    fun insert(favoriteEvent: FavoriteEvent) {
        mFavoriteEventRepository.insert(favoriteEvent)
    }

    fun delete(favoriteEvent: FavoriteEvent) {
        mFavoriteEventRepository.delete(favoriteEvent)
    }
}
