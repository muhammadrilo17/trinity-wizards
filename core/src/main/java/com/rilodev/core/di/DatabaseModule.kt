package com.rilodev.core.di

import android.content.Context
import androidx.room.Room
import com.rilodev.core.BuildConfig
import com.rilodev.core.data.source.local.room.AppDao
import com.rilodev.core.data.source.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "trinity.db"
        )
            .openHelperFactory(SupportFactory(passphrase))
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }
}