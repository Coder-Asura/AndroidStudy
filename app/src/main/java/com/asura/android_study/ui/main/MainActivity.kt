package com.asura.android_study.ui.main

import android.Manifest
import android.app.Activity
import android.content.*
import android.content.pm.ResolveInfo
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.asura.android_study.R
import com.asura.android_study.receiver.NetWorkReceiver
import com.asura.android_study.ui.base.BasePermissionActivity
import com.asura.android_study.ui.behavior.CoordinatorLayoutActivity
import com.asura.android_study.ui.behavior.UCBehaviorActivity
import com.asura.android_study.ui.bottomnav.BottomNavActivity
import com.asura.android_study.ui.constranitlayout.ConstraintLayoutActivity
import com.asura.android_study.ui.constranitlayout.ConstraintLayoutTransitionActivity
import com.asura.android_study.ui.customdata.CustomDataActivity
import com.asura.android_study.ui.eventbus.SubscribeActivity
import com.asura.android_study.ui.floatwindow.FloatWindowActivity
import com.asura.android_study.ui.font.FontActivity
import com.asura.android_study.ui.fragtofrag.Fragment2Activity
import com.asura.android_study.ui.guessnumber.GuessNumberActivity
import com.asura.android_study.ui.horizontallistview.HorizontalListViewActivity
import com.asura.android_study.ui.itemtype.ItemTypeActivity
import com.asura.android_study.ui.leafloading.LeafLoadingActivity
import com.asura.android_study.ui.permission.PermissionActivity
import com.asura.android_study.ui.rxjava.RxJavaActivity
import com.asura.android_study.ui.section.SectionActivity
import com.asura.android_study.ui.service.music.MessengerActivity
import com.asura.android_study.ui.service.music.MusicActivity
import com.asura.android_study.ui.slidingchecklayout.SlidingCheckLayoutActivity
import com.asura.android_study.ui.threadpool.step1.ThreadPool2Activity
import com.asura.android_study.ui.threadpool.threadpool.ThreadPoolActivity
import com.asura.android_study.ui.viewpager.ViewPagerActivity
import com.asura.android_study.ui.viewpager.multi.MultiViewPagerActivity
import com.asura.android_study.view.CameraLiveWallpaper
import com.asura.android_study.view.slide.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BasePermissionActivity() {
    private var mNetWorkReceiver: NetWorkReceiver? = null

    private var mainAdapter: MainAdapter? = null

    private var dataList: MutableList<MainItem> = mutableListOf(
        MainItem("打开QQ", null) {
            openQQ(this@MainActivity)
        },
        MainItem("打开邮箱App发邮件", null) {
            openMailAppSend()
        },
        MainItem("打开邮箱App", null) {
            openMailAppReceive()
        },
        MainItem("设置透明壁纸", null) {
            setTransparentWallpaper()
            startWallpaper()
        },
        MainItem("猜数字游戏", GuessNumberActivity::class.java, null),
        MainItem("修改layout parent字体", FontActivity::class.java, null),
        MainItem("横向ListView", HorizontalListViewActivity::class.java, null),
        MainItem("Bind Service 音乐", MusicActivity::class.java, null),
        MainItem("Messenger Service 音乐", MessengerActivity::class.java, null),
        MainItem("Rxjava", RxJavaActivity::class.java, null),
        MainItem("leafAnim进度条叶子动效", LeafLoadingActivity::class.java, null),
        MainItem("协调布局", CoordinatorLayoutActivity::class.java, null),
        MainItem("UC首页", UCBehaviorActivity::class.java, null),
        MainItem("fragment_activity通信", Fragment2Activity::class.java, null),
        MainItem("RecyclerView 多itemType", ItemTypeActivity::class.java, null),
        MainItem("ViewPager 懒加载fragment", ViewPagerActivity::class.java, null),
        MainItem("多个ViewPager展示", MultiViewPagerActivity::class.java, null),
        MainItem("底部导航栏", BottomNavActivity::class.java, null),
        MainItem("自定义接收特定类型文件(.bin)", CustomDataActivity::class.java, null),
        MainItem("ConstraintLayout约束布局", ConstraintLayoutActivity::class.java, null),
        MainItem("ConstraintLayout约束布局动画", ConstraintLayoutTransitionActivity::class.java, null),
        MainItem("线程、线程池1", ThreadPoolActivity::class.java, null),
        MainItem("线程、线程池2", ThreadPool2Activity::class.java, null),
        MainItem("EventBus", SubscribeActivity::class.java, null),
        MainItem("悬浮窗", FloatWindowActivity::class.java, null),
        MainItem("滑动选择", SlidingCheckLayoutActivity::class.java, null),
        MainItem("灯段View", SectionActivity::class.java, null),
        MainItem("申请权限", PermissionActivity::class.java, null)
    )

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mainAdapter = MainAdapter(this, dataList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_main.layoutManager = layoutManager
        rv_main.adapter = mainAdapter
        mNetWorkReceiver = NetWorkReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(mNetWorkReceiver, intentFilter)

        checkSelfPermission()
    }

    private fun checkSelfPermission() {
        requestPermission(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            )
        )
    }

    override fun onPermissionResult(isAllow: Boolean) {
        if (isAllow) {
            Toast.makeText(this@MainActivity, "权限ok", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "权限不ok", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWallpaper() {
        val pickWallpaper = Intent(Intent.ACTION_SET_WALLPAPER)
        val chooser = Intent.createChooser(pickWallpaper, "选择壁纸")
        startActivity(chooser)
    }

    /**
     * 设置透明壁纸
     */
    private fun setTransparentWallpaper() {
        startService(Intent(this@MainActivity, CameraLiveWallpaper::class.java))
    }

    private fun openMailAppSend() {
        val uri = Uri.parse("mailto:")
        //        Uri uri = Uri.parse("http:");//选择浏览器App
        val intent = Intent(Intent.ACTION_VIEW, null)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = uri
        startActivity(Intent.createChooser(intent, "请选择邮箱App"))
    }

    private fun openMailAppReceive() {
        val pm = packageManager
        val uri = Uri.parse("mailto:")
        val intent = Intent(Intent.ACTION_VIEW, null)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = uri
        // 通过查询，获得所有ResolveInfo对象.
        val resolveInfos = pm.queryIntentActivities(intent, 0)
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, ResolveInfo.DisplayNameComparator(pm))
        //        final ArrayList<String> stringArrayList = new ArrayList<>();
        val intents = ArrayList<Intent?>()
        for (reInfo in resolveInfos) {
            val pkgName = reInfo.activityInfo.packageName // 获得应用程序的包名
            //            String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
            //            Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
            val appIntent = pm.getLaunchIntentForPackage(pkgName)
            intents.add(appIntent)
        }
        val builder = AlertDialog.Builder(this)
        builder.setAdapter(MailAppAdapter(this, resolveInfos)) { dialog, which ->
            val intent = intents[which]
            intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        builder.show()
    }

    /**
     * 打开QQ
     *
     * @param context
     */
    private fun openQQ(context: Context) {
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.component = ComponentName(
                "com.tencent.mobileqq",
                "com.tencent.mobileqq.activity.SplashActivity"
            )
            //打开自带邮箱App
            //            intent.setComponent(new ComponentName("com.android.email",
            //                    "com.android.email.activity.EmailActivity"));
            if (context !is Activity) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "未安装QQ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetWorkReceiver)
    }

}