package com.example.tinkofflabproject.data.repositories

import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.net.MovieApi
import com.example.tinkofflabproject.utils.TYPE

class MovieDataSource(private val apiService: MovieApi) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNum = params.key ?: 1
            val response = apiService.getPopularMoviesPage(TYPE, pageNum)
            LoadResult.Page(
                data = response.movies,
                prevKey = if (pageNum < 1) null else pageNum - 1,
                nextKey = if (pageNum > response.totalPages) null else pageNum + 1
            )
        }
        catch (e : Exception){
            Log.e("Response error", e.message!!)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
