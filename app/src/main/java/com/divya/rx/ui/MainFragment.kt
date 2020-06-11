package com.divya.rx.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.divya.rx.R
import com.divya.rx.datalayer.model.BaseResponse
import com.divya.rx.datalayer.model.User
import com.divya.rx.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable


class MainFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val disposables = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    lateinit var searchView: SearchView
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)


        disposables.add( Observable
            .create(ObservableOnSubscribe<String> { emitter ->
                // Listen for text input into the SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        if (!emitter.isDisposed) {
                            emitter.onNext(newText) // Pass the query to the emitter
                        }
                        return false
                    }
                })
            })
            .filter { s -> !TextUtils.isEmpty(s.trim()) }
            .debounce(1000, TimeUnit.MILLISECONDS)// Apply Debounce() operator to limit requests
            .distinctUntilChanged()
            .switchMap { s ->
                makeServerCall(s)?.doOnDispose { Log.d("D::","disposed") }?.subscribeOn(Schedulers.io())?.doOnError {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }.observeOn(AndroidSchedulers.mainThread()).subscribe {
                    if (it != null) {
                        if (it.result != null && !it.result.isNullOrEmpty()) {
                            adapter.mValues = it.result!!
                        } else {
                            adapter.mValues = ArrayList()
                            Toast.makeText(context, "No user found", Toast.LENGTH_SHORT).show()
                        }
                        adapter.notifyDataSetChanged()
                    }

            })


    }

    private fun initViews(view: View) {
        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
    private fun makeServerCall(newText: String): Observable<BaseResponse<List<User>>>? {
        return viewModel.getUsers(newText)
    }

}
