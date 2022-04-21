package me.thekusch.ozancaseproject.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import me.thekusch.ozancaseproject.R
import me.thekusch.ozancaseproject.presentation.navigation.DefaultNavigation
import me.thekusch.ozancaseproject.presentation.navigation.Navigation
import java.lang.ref.WeakReference

abstract class BaseActivity<BIND : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BIND

    protected lateinit var navigation: Navigation

    @IdRes
    open val containerId = R.id.frameLayoutMain

    abstract fun initBind(): BIND

    open fun getFragment(): Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBind()
        navigation = DefaultNavigation(WeakReference(this))
        setContentView(binding.root)
        getFragment()?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.
                beginTransaction()
                    .replace(containerId, it)
                    .commit()

            }
        }

    }


}