package com.idcamp.dicodingevent.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idcamp.dicodingevent.database.FavoriteEvent
import com.idcamp.dicodingevent.repository.FavoriteEventRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteEventRepository: FavoriteEventRepository = FavoriteEventRepository(application)

    fun getAllFavoriteEvents(): LiveData<List<FavoriteEvent>> = mFavoriteEventRepository.getAllFavoriteEvents()
}
