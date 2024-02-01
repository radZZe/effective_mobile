package ru.radzze.effective_mobile.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


const val PREFERENCE_NAME = "user_preference"
class UserStore(private val context:Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    private val dataStore = context.dataStore

    companion object PreferenceKeys {
        val isAuthenticated: Preferences.Key<Boolean> = booleanPreferencesKey("is_authenticated")
        val name : Preferences.Key<String> = stringPreferencesKey("accessToken")
        val surname : Preferences.Key<String> = stringPreferencesKey("refreshToken")
        val phone: Preferences.Key<String> = stringPreferencesKey("user_name")

    }

    suspend fun setPref(prefValue: Boolean, prefKey: Preferences.Key<Boolean>) {
        dataStore.edit { pref->
            pref[prefKey] = prefValue
        }
        Log.d("DATASTORE","setPref отработал Bool")
    }

    suspend fun setPref(prefValue: String, prefKey: Preferences.Key<String>) {
        dataStore.edit { pref->
            pref[prefKey] = prefValue
        }
        Log.d("DATASTORE","setPref отработал String ${prefValue}")
    }


    fun getPrefBool(prefKey: Preferences.Key<Boolean>) : Flow<Boolean> {
        return dataStore.data
            .catch { exception->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref->
                val prefMode = pref[prefKey] ?: false
                prefMode
            }

    }

    fun getPref(prefKey: Preferences.Key<String>) : Flow<String> {
        return dataStore.data
            .catch { exception->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref->
                val prefMode = pref[prefKey] ?: ""
                prefMode
            }

    }
}