package com.example.repast.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var mContext: Context) {

    val Context.ds: DataStore<Preferences> by preferencesDataStore("Bilgiler")

    companion object{
        val USERNAME_KEY = stringPreferencesKey("USERNAME")
        val COUNT_KEY = intPreferencesKey("COUNT")
    }

    suspend fun getUsername(username: String){
        mContext.ds.edit{
            it[USERNAME_KEY] = username
        }
    }

    suspend fun readUsername():String {
        val pref = mContext.ds.data.first()
        return pref[USERNAME_KEY] ?: "isim yok"
    }

    suspend fun getCount(count: Int){
        mContext.ds.edit{
            it[COUNT_KEY] = count
        }
    }
    suspend fun readCount():Int {
        val pref = mContext.ds.data.first()
        return pref[COUNT_KEY] ?: 0
    }
}