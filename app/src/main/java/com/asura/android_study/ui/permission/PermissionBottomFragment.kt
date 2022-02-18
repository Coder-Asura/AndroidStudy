package com.asura.android_study.ui.permission

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import com.asura.android_study.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PermissionBottomFragment : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_bottom_dialog_permission,
            container,
            true
        )
        view.findViewById<Button>(R.id.button6).setOnClickListener {
            Log.d("lxdlxd", "点击申请权限")
            Toast.makeText(requireContext(), "点击申请权限", Toast.LENGTH_SHORT).show()
            requestPermission()
        }

//        // 隐藏标题栏, 不加弹窗上方会一个透明的标题栏占着空间
//        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//
//        // 必须设置这两个,才能设置宽度
//        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog!!.window!!.decorView.setBackgroundColor(Color.TRANSPARENT)
//
//        // 遮罩层透明度
//        dialog!!.window!!.setDimAmount(0f)
//
//        // 设置宽度
//        val params: WindowManager.LayoutParams = dialog!!.window!!.attributes
//        params.width = WindowManager.LayoutParams.MATCH_PARENT
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT
//        params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
//        dialog!!.window!!.attributes = params

        return view
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.BODY_SENSORS
            ), 100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            val notAllowList = mutableListOf<String>()
            grantResults.forEachIndexed { index, i ->
                if (i != PackageManager.PERMISSION_GRANTED) {
                    notAllowList.add(permissions[index])
                }
            }
            if (notAllowList.isEmpty()) {
                Toast.makeText(requireContext(), "BottomSheetDialogFragment 全部允许", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "BottomSheetDialogFragment $notAllowList 没允许", Toast.LENGTH_SHORT).show()
            }
        }

    }

}