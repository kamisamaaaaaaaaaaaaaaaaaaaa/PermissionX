package com.example.lysdev

import androidx.fragment.app.FragmentActivity

object PermissionX {
    private val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: callBackType) {
        val fragmentManger = activity.supportFragmentManager
        val existedFragment = fragmentManger.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManger.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}