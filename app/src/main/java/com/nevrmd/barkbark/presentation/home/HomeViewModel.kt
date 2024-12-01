package com.nevrmd.barkbark.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.data.source.remote.ApiRequest
import com.nevrmd.barkbark.data.source.remote.BASE_URL
import com.nevrmd.barkbark.domain.BreedEntity
import com.nevrmd.barkbark.presentation.BaseViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_MAX_PAGES = 28
const val API_MAX_BREEDS_PER_PAGE = 10

class HomeViewModel(private val repository: BreedRepository) : BaseViewModel<HomeState>(
    initialState = HomeState()
) {

    fun getBreeds() = repository.breeds

    fun checkIfBreedPresent(breedName: String) {

    }

    fun insertAllBreeds() {
        var currentPageNumber = 0
        while (currentPageNumber != API_MAX_PAGES) {
            currentPageNumber++
            insertBreedsFromPage(currentPageNumber)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun insertBreedsFromPage(pageNumber: Int) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getBreedsByPageNumber(pageNumber).data

                var currentBreedNumber = -1

                while (currentBreedNumber != API_MAX_BREEDS_PER_PAGE) {
                    currentBreedNumber++
                    insert(BreedEntity(
                        response[currentBreedNumber].id,
                        response[currentBreedNumber].attributes.name,
                        response[currentBreedNumber].attributes.description,
                        response[currentBreedNumber].attributes.hypoallergenic,
                        response[currentBreedNumber].attributes.life.min,
                        response[currentBreedNumber].attributes.life.max,
                        pageNumber,
                    ))
                }
            } catch (e: Exception) {
                Log.e("Home", "Error ${e.message}")
            }
        }
    }

    private fun insert(breed: BreedEntity) = viewModelScope.launch {
        repository.insert(breed)
    }
}