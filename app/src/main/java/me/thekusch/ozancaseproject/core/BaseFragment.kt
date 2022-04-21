package me.thekusch.ozancaseproject.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import me.thekusch.ozancaseproject.presentation.navigation.DefaultNavigation
import me.thekusch.ozancaseproject.presentation.navigation.Navigation
import java.lang.ref.WeakReference

abstract class BaseFragment<BINDING: ViewBinding>: Fragment() {

    private var _binding: BINDING? = null

    protected val binding get() = _binding!!

    protected lateinit var navigation: Navigation

    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean = false
    ): BINDING?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = DefaultNavigation(WeakReference(requireActivity()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        fetch()
        observe()
    }

    open fun initView() {

    }

    open fun observe() {

    }

    open fun fetch() {

    }

}