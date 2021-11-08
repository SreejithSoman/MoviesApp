package com.example.moviesapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.ui.base.BaseFragment
import com.example.moviesapp.utils.GridSpacingItemDecoration
import com.example.moviesapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MoviesFragment : BaseFragment<MoviesViewModel>(), (CombinedLoadStates) -> Unit, SearchView.OnQueryTextListener {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: Provider<GridLayoutManager>

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override val viewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val mSearchMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = mSearchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.isIconifiedByDefault = false
        searchView.isIconified = false
//        searchView.onActionViewExpanded()
//        val closeIcon = searchView.findViewById<ImageView>(R.id.search_close_btn)
//        closeIcon.setBackgroundResource(R.drawable.search_cancel)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        mGridLayoutManager.get()?.let {
            it.reverseLayout = false
            it.isItemPrefetchEnabled = false
            moviesRecycler.layoutManager = it
        }
        moviesRecycler.apply {
            setHasFixedSize(true)
            addItemDecoration(mGridSpacingItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mMovieAdapter.withLoadStateFooter(
                footer = MovieStateAdapter { mMovieAdapter.retry() }
            )
        }
        listenForAdapterStates()
    }

    private fun listenForAdapterStates() {
        viewModel.movies.observe(viewLifecycleOwner,
            { paging -> lifecycleScope.launch { mMovieAdapter.submitData(paging) } })
        btn_retry.setOnClickListener { mMovieAdapter.retry() }
        mMovieAdapter.addLoadStateListener(this)
    }

    override fun invoke(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            btn_retry.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    btn_retry.visibility = View.VISIBLE
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.error?.localizedMessage?.let { showMessage(it) }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            if(it.length >= 3) {
                viewModel.getSearchLiveData().postValue(it)
                mMovieAdapter.searchEnabled(it)
                if(mMovieAdapter.itemCount <= 0) showMessage("No movies found")
            }
            if(it.isBlank()) {
                viewModel.getSearchLiveData().postValue(it)
                mMovieAdapter.searchEnabled(it)
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
}

