package com.idcamp.dicodingevent.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.idcamp.dicodingevent.database.FavoriteEvent
import com.idcamp.dicodingevent.database.FavoriteEventDao
import com.idcamp.dicodingevent.database.FavoriteEventRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteEventRepository(application: Application) {
    private val mFavoriteEventDao: FavoriteEventDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteEventRoomDatabase.getDatabase(application)
        mFavoriteEventDao = db.eventDao()
    }

    fun getAllFavoriteEvents(): LiveData<List<FavoriteEvent>> = mFavoriteEventDao.getAllFavoriteEvents()

    fun getFavoriteEventById(id: Int): LiveData<FavoriteEvent> {
        return mFavoriteEventDao.getFavoriteEventById(id)
    }

    fun insert(event: FavoriteEvent) {
        executorService.execute { mFavoriteEventDao.insert(event) }
    }

    fun delete(event: FavoriteEvent) {
        executorService.execute { mFavoriteEventDao.delete(event) }
    }
}
