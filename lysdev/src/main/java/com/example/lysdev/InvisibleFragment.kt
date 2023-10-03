package com.example.lysdev

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

typealias callBackType = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {
    private var callback: callBackType? = null

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
            val deniedPermissions = ArrayList<String>()
            permissions.filter { !it.value }.forEach {
                deniedPermissions.add(it.key)
            }
            val allGranted = deniedPermissions.isEmpty()
            callback?.let { it(allGranted, deniedPermissions) }
        }

    fun requestNow(cb: callBackType, vararg _permissions: String) {
        callback = cb
        val permissions = ArrayList<String>()
        permissions.addAll(_permissions)
        requestPermission.launch(permissions.toTypedArray())
    }
}