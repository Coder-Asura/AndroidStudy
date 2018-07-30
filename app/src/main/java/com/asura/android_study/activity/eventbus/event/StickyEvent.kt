package com.asura.android_study.activity.eventbus.event

/**
 * 黏性事件
 * @author Created by Asura on 2018/7/23 16:01.
 *
 * 在Android开发中，Sticky事件只指事件消费者在事件发布之后才注册的也能接收到该事件的特殊类型。如粘性广播
 */
class StickyEvent(var msg: String)