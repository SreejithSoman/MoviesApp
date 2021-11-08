package com.example.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesapp.R
import com.example.moviesapp.ui.base.BaseActivity
import com.example.moviesapp.viewmodel.MoviesViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MoviesViewModel>(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MoviesViewModel = ViewModelProvider(this, mViewModelFactory).get(
        MoviesViewModel::class.java)
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController(R.id.nav_movies_host_fragment)
        navController.setGraph(R.navigation.movies_graph, intent.extras)
    }

    override fun onBackPressed() {
        val findNavController = NavHostFragment.findNavController(nav_movies_host_fragment)
        when (findNavController.currentDestination?.id) {
            R.id.movies -> finish()
            else -> super.onBackPressed()
        }
    }

}
