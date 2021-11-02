package com.asura.android_study.utils

import android.util.Log
import com.asura.android_study.model.LightSection
import com.asura.android_study.model.SectionShape

/**
 * Author: Asuraliu
 * Date: 2021/11/1 10:53
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
 */
object LightSectionsUtils {
    //    fun getLightSections(count:Int) {
    //        getLightSections(count = count)
    //    }

    private const val MIN_SECTIONS_COUNT = 2
    private const val MAX_SECTIONS_COUNT = 150
    private const val SPAN_COUNT = 5


    /**
     * 获取灯带段列表
     * @param max 最大几段
     * @param spanCount 每行几段
     * @param count 要几段
     *
     * //0  , 1  , 2  , 3  , 4 ,
     * //8  , 7  , 6  , 5  , X ,
     * //X  , 9  , 10 , 11 , 12,
     * //X  , X  , 14 , 13 , X ,
     */
    fun getLightSections(max: Int = MAX_SECTIONS_COUNT, spanCount: Int = SPAN_COUNT, count: Int): MutableList<LightSection>? {
        if (max < 0 || spanCount <= 0 || count < MIN_SECTIONS_COUNT || count > MAX_SECTIONS_COUNT) {
            Log.e("asuralxd", "please check input!")
            return null
        }
        val list: MutableList<LightSection> = mutableListOf()
        val rows = count / spanCount
        val more = count % spanCount
        //计算真实行数及最后一行个数
        var realRows = 0
        var realMore = 0
        if (rows == 1 && more == 0) {
            //正好占满一行
            realRows = 1
            realMore = 0
        } else if (rows == 0) {
            //一行未占满
            realRows = 1
            realMore = count
        } else {
            //多行情况，除第一行放SPAN_COUNT个外，其余都只能放(SPAN_COUNT-1)个
            //首行以外的个数 % 其余行个数
            realMore = (count - SPAN_COUNT) % (SPAN_COUNT - 1)
            //需要加上首行
            realRows = ((count - SPAN_COUNT) / (SPAN_COUNT - 1) + 1).let {
                if (realMore != 0) {
                    //余数还需要换行
                    it + 1
                } else {
                    it
                }
            }
        }
        Log.d("asuralxd", "------------------------------------")
        Log.d("asuralxd", "max $max, spanCount:$spanCount, count $count, realRows $realRows, realMore $realMore")
        if (realRows == 1) {
            getFirstSpan(list, count, false)
        } else {
            //先获取第一行
            getFirstSpan(list, SPAN_COUNT, true)


            //            =========well==================
            //奇数行位置一致，偶数行位置反序
            for (row in 1 until realRows) {
                val lastRow = row == (realRows - 1)
                //不是最后一行，都需要换行
                val needTurning = !lastRow

                if ((row + 1) % 2 == 1) {
                    //奇数行
                    //首行个数 + 除尾行外其他行行数*其他行最大个数
                    val positionStart = SPAN_COUNT + (row + 1 - 1 - 1) * (SPAN_COUNT - 1)
                    val positionEnd = if (lastRow && realMore != 0) {
                        //最后一行，且没有占满的情况
                        positionStart + realMore - 1
                    } else {
                        //占满了整行
                        positionStart + (SPAN_COUNT - 1) - 1
                    }

                    val indexStart = positionStart
                    val indexEnd = positionEnd
                    Log.d(
                        "asuralxd",
                        "count $count, 奇数 row $row, index:$indexStart -> $indexEnd ,needTurning $needTurning , position:$positionStart->$positionEnd"
                    )

                    for (position in positionStart..positionEnd) {
                        val shape = when (position) {
                            positionEnd -> if (needTurning) {
                                SectionShape.SHAPE_TURNING_LEFT
                            } else {
                                SectionShape.SHAPE_END_RIGHT
                            }
                            else -> SectionShape.SHAPE_NORMAL
                        }
                        val lightSection = LightSection(position, position, shape, false, "$position,$position,$position")
                        list.add(lightSection)
                    }
                    //                    Log.d("asuralxd", "奇数 \n $list")
                } else {
                    val positionStart = SPAN_COUNT + (row - 1) * (SPAN_COUNT - 1)
                    val positionEnd = positionStart + (SPAN_COUNT - 1) - 1
                    //偶数行
                    val indexStart = if (lastRow) {
                        if (realMore == 0) {
                            positionEnd
                        } else {
                            positionEnd - realMore
                        }
                    } else {
                        positionEnd
                    }
                    val indexEnd = if (lastRow) {
                        if (realMore == 0) {
                            positionEnd - realMore
                        } else {
                            positionStart
                        }
                    } else {
                        positionStart
                    }
                    Log.d(
                        "asuralxd",
                        "count $count, 偶数 row $row, index:$indexStart -> $indexEnd ,needTurning $needTurning , position:$positionStart->$positionEnd"
                    )

                    for (position in positionStart..positionEnd) {
                        val shape = when (position) {
                            positionStart -> {
                                if (realMore == 0) {
                                    //占满了
                                    SectionShape.SHAPE_END_LEFT
                                } else {
                                    SectionShape.SHAPE_EMPTY
                                }
                            }
                            positionEnd -> {
                                SectionShape.SHAPE_NORMAL
                            }
                            else -> {
                                SectionShape.SHAPE_NORMAL
                            }
                            //                            indexEnd -> if (needTurning) {
                            //                                SectionShape.SHAPE_TURNING_LEFT
                            //                            } else {
                            //                                SectionShape.SHAPE_END_RIGHT
                            //                            }
                            //                            else -> SectionShape.SHAPE_NORMAL
                        }
                        //                        val lightSection = LightSection(position, index, shape, false, "$index,$index,$index")
                        //                        list.add(lightSection)
                    }
                    //                    Log.d("asuralxd", "偶数 \n $list")
                }
            }

        }


        //        Log.d("asuralxd", "getLightSections \n $list")
        return list
    }

    private fun getFirstSpan(list: MutableList<LightSection>, count: Int, needTurning: Boolean) {
        val indexStart = 0
        val indexEnd = indexStart + count - 1
        Log.d("asuralxd", "row 0, index:$indexStart -> $indexEnd ,needTurning $needTurning , position:$indexStart->$indexEnd")
        for (index: Int in indexStart..indexEnd) {
            val shape = when (index) {
                indexStart -> SectionShape.SHAPE_START
                indexEnd -> if (needTurning) {
                    SectionShape.SHAPE_TURNING_LEFT
                } else {
                    SectionShape.SHAPE_END_RIGHT
                }
                else -> SectionShape.SHAPE_NORMAL
            }
            val lightSection = LightSection(index, index, shape, false, "$index,$index,$index")
            list.add(lightSection)
        }
        //        Log.d("asuralxd", "first \n $list")
    }

    //    private fun getLastSpan(list: MutableList<LightSection>, realRows: Int, realMore: Int) {
    //        //奇数行表示最后一个在右边，偶数行反之
    //        val lastIsRight: Boolean = realRows % 2 == 1
    //        //最后一行的最后一个是在右边
    //        if (lastIsRight) {
    //            //val start = if (rows >= 2) (rows - 1) * SPAN_COUNT else SPAN_COUNT
    //            //说明最少有3行
    //            //尾行起始位置 = 首行个数+中间行个数-1
    //            val start = SPAN_COUNT + (realRows - 2) * (SPAN_COUNT - 1)
    //            //realMore=0 表示尾行满了
    //            val end = if (realMore == 0) {
    //                start + (SPAN_COUNT - 1) - 1
    //            } else {
    //                start + realMore - 1
    //            }
    //            //不需要补充空格
    //            for (index: Int in start..end) {
    //                val shape = when (index) {
    //                    end -> SectionShape.SHAPE_END_RIGHT
    //                    else -> SectionShape.SHAPE_NORMAL
    //                }
    //                val lightSection = LightSection(index, shape, false, "$index,$index,$index")
    //                list.add(lightSection)
    //            }
    //        } else {
    //            //偶数行，结束位置在左边，需要在左边补充空格,需要倒序
    //            //尾行起始位置 = 首行个数+中间行个数
    //            val start = SPAN_COUNT + (realRows - 2) * (SPAN_COUNT - 1)
    //            val end = start + (SPAN_COUNT - 1) - 1
    //            val realStart = if (realMore == 0) {
    //                start
    //            } else {
    //                start + ((SPAN_COUNT - 1) - realMore + 1)
    //            }
    //            for (index: Int in end..start) {
    //                val shape = when (index) {
    //                    realStart -> SectionShape.SHAPE_END_LEFT
    //                    in start until realStart -> SectionShape.SHAPE_EMPTY
    //                    else -> SectionShape.SHAPE_NORMAL
    //                }
    //                val lightSection = LightSection(index, shape, false, "$index,$index,$index")
    //                list.add(lightSection)
    //            }
    //        }
    //    }


    private fun exchangeIndex(position: Int, count: Int, max: Int = MAX_SECTIONS_COUNT, spanCount: Int = SPAN_COUNT): Int {
        val rows = count / spanCount
        val more = count % spanCount
        //计算真实行数及最后一行个数
        var realRows = 0
        var realMore = 0
        if (rows == 0 || (rows == 1 && more == 0)) {
            realRows = 1
        } else {
            //首行以外的个数 % 其余行个数
            realMore = (count - SPAN_COUNT) % (SPAN_COUNT - 1)  //加上转弯重复的1个
            realRows = if (realMore == 0) {
                //除第一行放SPAN_COUNT个外，其余都只能放(SPAN_COUNT-1)个
                (count - SPAN_COUNT) / (SPAN_COUNT - 1) + 1 //加上首行
            } else {
                //除第一行放SPAN_COUNT个外，其余都只能放(SPAN_COUNT-1)个
                (count - SPAN_COUNT) / (SPAN_COUNT - 1) + 1 + 1 //加上首行,有余数需要换行
            }
        }








        return 0
    }

}