package com.example.repast.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var mContext: Context) {

    val Context.ds: DataStore<Preferences> by preferencesDataStore("username")

    companion object{
        val USERNAME_KEY = stringPreferencesKey("USERNAME")
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
}