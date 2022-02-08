/*
package com.example.repast.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.model.Yemekler
import com.example.repast.utils.Constants.Companion.UNSPLASH_STARTING_PAGE_INDEX
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodsPagingSource @Inject constructor(
    private val foodsApi: FoodsApiService
): PagingSource<Int,Yemekler>(){

    override fun getRefreshKey(state: PagingState<Int, Yemekler>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Yemekler> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = foodsApi.getAllFoods()
            val tidings = response.yemekler

            LoadResult.Page(
                data = tidings,
                prevKey = if ( position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if ( tidings.isEmpty()) null else position + 1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

}*/
