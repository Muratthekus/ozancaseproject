package me.thekusch.ozancaseproject.presentation.detail

import androidx.fragment.app.Fragment
import me.thekusch.ozancaseproject.core.BaseActivity
import me.thekusch.ozancaseproject.databinding.ActivityMainBinding

class DetailActivity: BaseActivity<ActivityMainBinding>() {

    override fun initBind(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getFragment() = DetailFragment.newInstance()
}