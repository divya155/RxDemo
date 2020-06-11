package com.divya.rx.viewmodel

import androidx.lifecycle.ViewModel
import com.divya.rx.datalayer.model.BaseResponse
import com.divya.rx.datalayer.model.User
import com.divya.rx.datalayer.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class SearchViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    fun getUsers(searchQuery: String): Observable<BaseResponse<List<User>>>? {

        return apiService.searchUser(searchQuery).toObservable()
    }
}