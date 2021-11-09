package com.asura.android_study.model

/**
 * Author: Asuraliu
 * Date: 2021/11/1 10:46
 * Description: 灯段
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
 */
data class LightSection(
    //RecyclerView中的位置
    var position: Int = 0,
    //灯段的位置
    var index: Int = 0,
    var shape: SectionShape = SectionShape.SHAPE_START,
    var check: Boolean = false,
    var hsv: String = "",
    var byClick: Boolean = false
) {
    override fun toString(): String {
        //        return "$index,$shape,$check,$hsv\n"
        return "$position,$index,$shape\n"
    }
}

enum class SectionShape {
    SHAPE_START,
    SHAPE_END_LEFT, //同SHAPE_START
    SHAPE_END_RIGHT,
    SHAPE_TURNING_LEFT,
    SHAPE_TURNING_RIGHT,
    SHAPE_NORMAL,
    SHAPE_EMPTY
}
