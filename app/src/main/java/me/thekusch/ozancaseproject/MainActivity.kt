package me.thekusch.ozancaseproject

import androidx.fragment.app.Fragment
import me.thekusch.ozancaseproject.core.BaseActivity
import me.thekusch.ozancaseproject.databinding.ActivityMainBinding
import me.thekusch.ozancaseproject.presentation.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initBind(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getFragment() = HomeFragment.newInstance()

}