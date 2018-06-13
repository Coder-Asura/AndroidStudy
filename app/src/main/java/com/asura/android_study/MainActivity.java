package com.asura.android_study;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asura.android_study.adapter.MailAppAdapter;
import com.asura.android_study.service.music.MessengerActivity;
import com.asura.android_study.service.music.MusicActivity;
import com.asura.android_study.view.CameraLiveWallpaper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasePermissionActivity {

    @BindView(R.id.btn_open_qq)
    Button mBtnOpenQq;
    @BindView(R.id.btn_open_email)
    Button mBtnOpenEmail;
    @BindView(R.id.btn_open_email_app)
    Button mBtnOpenEmailApp;
    @BindView(R.id.btn_set_wallpaper)
    Button mBtnSetWallpaper;
    @BindView(R.id.btn_music_bind_service)
    Button mBtnMusicBindService;
    @BindView(R.id.btn_messenger_service)
    Button mBtnMessengerService;
    @BindView(R.id.tv_drawable)
    TextView mTvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(new Rect(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()));
        mTvDrawable.setCompoundDrawables(drawable, null, null, null);

        checkSelfPermission();
    }

    @OnClick({R.id.btn_open_qq, R.id.btn_open_email, R.id.btn_open_email_app, R.id.btn_set_wallpaper, R.id.btn_music_bind_service, R.id.btn_messenger_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_open_qq:
                openQQ(MainActivity.this);
                break;
            case R.id.btn_open_email:
                openMailAppSend();
                break;
            case R.id.btn_open_email_app:
                openMailAppReceive();
                break;
            case R.id.btn_set_wallpaper:
                setTransparentWallpaper();
                startWallpaper();
                break;
            case R.id.btn_music_bind_service:
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                break;
            case R.id.btn_messenger_service:
                startActivity(new Intent(MainActivity.this, MessengerActivity.class));
                break;
            default:
        }
    }

    private void checkSelfPermission() {
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA});
    }

    @Override
    public void onPermissionResult(boolean isAllow) {
        if (isAllow) {
            Toast.makeText(MainActivity.this, "权限ok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "权限不ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void startWallpaper() {
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper, "选择壁纸");
        startActivity(chooser);
    }

    /**
     * 设置透明壁纸
     */
    private void setTransparentWallpaper() {
        startService(new Intent(MainActivity.this, CameraLiveWallpaper.class));
    }

    /**
     * 打开QQ
     *
     * @param context
     */
    public static void openQQ(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(new ComponentName("com.tencent.mobileqq",
                    "com.tencent.mobileqq.activity.SplashActivity"));
            //打开自带邮箱App
//            intent.setComponent(new ComponentName("com.android.email",
//                    "com.android.email.activity.EmailActivity"));
            if (!(context instanceof Activity)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "未安装QQ", Toast.LENGTH_SHORT).show();
        }
    }

    public void openMailAppSend() {
        Uri uri = Uri.parse("mailto:");
//        Uri uri = Uri.parse("http:");//选择浏览器App
        Intent intent = new Intent(Intent.ACTION_VIEW, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(uri);
        startActivity(Intent.createChooser(intent, "请选择邮箱App"));
    }

    public void openMailAppReceive() {
        PackageManager pm = getPackageManager();
        Uri uri = Uri.parse("mailto:");
        Intent intent = new Intent(Intent.ACTION_VIEW, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(uri);
        // 通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
//        final ArrayList<String> stringArrayList = new ArrayList<>();
        final ArrayList<Intent> intents = new ArrayList<>();
        for (ResolveInfo reInfo : resolveInfos) {
            String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
//            String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
//            Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
            Intent appIntent = pm.getLaunchIntentForPackage(pkgName);
            intents.add(appIntent);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(new MailAppAdapter(this, resolveInfos), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = intents.get(which);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.show();

    }
}
