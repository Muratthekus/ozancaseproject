package me.thekusch.ozancaseproject.presentation.navigation

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

class DefaultNavigation(override val activity: WeakReference<FragmentActivity>) :
    Navigation
