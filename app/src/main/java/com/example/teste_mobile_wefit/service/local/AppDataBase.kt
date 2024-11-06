package com.example.teste_mobile_wefit.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teste_mobile_wefit.dao.CartDAO
import com.example.teste_mobile_wefit.entity.CartEntity
import com.example.teste_mobile_wefit.entity.CartItemEntity

@Database(entities = [CartEntity::class, CartItemEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cartDAO(): CartDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "wefit.db"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}