package com.asura.android_study.utils

/**
 * Author: Asuraliu
 * Date: 2021/11/9 16:41
 * Description: 扩展函数
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/9 1.0 首次创建
 */


/**
 * @param anyList 需要判null的所有元素集合
 * @param allNotNullBlock 所有元素都不为null时执行的操作
 * @param hasAnyNull 存在为null的元素时执行的操作
 */
inline fun Any.ifAllNotNull(vararg anyList: Any?, crossinline allNotNullBlock: () -> Unit, crossinline hasAnyNull: () -> Unit = {}) {
    if (anyList.isNotEmpty()) {
        var allNotNull = true
        anyList.forEach {
            if (it == null) {
                allNotNull = false
                return@forEach
            }
        }
        if (allNotNull) {
            allNotNullBlock.invoke()
        } else {
            hasAnyNull.invoke()
        }
    } else {
        allNotNullBlock.invoke()
    }
}