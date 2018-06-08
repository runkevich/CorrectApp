package com.vironit.correctapp.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vironit.correctapp.mvp.model.repository.db.CorrectDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Provides
    @Singleton
    CorrectDatabase provideCorrectDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), CorrectDatabase.class, "AppDatabase.db")
                .fallbackToDestructiveMigration()
                .build();
    }
}
