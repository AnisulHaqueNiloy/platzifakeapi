package com.example.platzi_api.paging

import android.nfc.Tag
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.platzi_api.models.product.ResponseProductItem
import com.example.platzi_api.services.ProductService
import javax.inject.Inject

class ProductPagingSource @Inject constructor(private val service: ProductService)
: PagingSource<Int, ResponseProductItem>()
{


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseProductItem> {
        var page = params.key ?: 0
        Log.d("TAG","page:$page")
        Log.d("TAG","offset:${params.loadSize*page}")
        Log.d("TAG","limmit:${params.loadSize}")

        var response =   service.getPagingProducts(params.loadSize*page, params.loadSize)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if(page == 0) null else page-1,
                nextKey = if (response.isEmpty()) null else page + 1

            )
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }
    }






    override fun getRefreshKey(state: PagingState<Int, ResponseProductItem>): Int? {
        return  state.anchorPosition?.let { anchorPosition->

            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)

        }
    }


}