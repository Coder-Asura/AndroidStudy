package com.asura.android_study.ui.permission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.asura.android_study.R
import com.asura.android_study.ui.permission.PermissionBottomFragment
import com.asura.android_study.ui.permission.PermissionDialogFragment


class PermissionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_permission, container, false)
        view.findViewById<Button>(R.id.button1).setOnClickListener {
            PermissionDialogFragment().show(childFragmentManager, "PermissionDialogFragment")
        }

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            PermissionBottomFragment().show(childFragmentManager, "PermissionBottomFragment")
        }
        return view
    }
}