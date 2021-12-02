package com.asura.android_study.ui.main

/**
 * Author: Asuraliu
 * Date: 2021/12/2 11:33
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/12/2 1.0 首次创建
 */
data class MainItem(val title: String, val intentClass: Class<*>? = null, val block: (() -> Unit)? = null)
