package com.asura.android_study.activity.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.asura.android_study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by Asura on 2017/12/19 10:18.
 */
public abstract class BasePermissionActivity extends BaseActivity {
    /**
     * 权限申请
     */
    private static final int PERMISSION_REQUEST_CODE = 0;

    protected void requestPermission(String[] needPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            for (String needPermission : needPermissions) {
                if (ContextCompat.checkSelfPermission(this, needPermission) != PackageManager.PERMISSION_GRANTED) {
                    permissions.add(needPermission);
                }
            }
            if (permissions.size() > 0) {
                StringBuilder permissionName = new StringBuilder();
                permissionName.append(getString(R.string.need_permission_tip));
                boolean isShouldShowTips = false;
                for (int i = 0; i < permissions.size(); i++) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions.get(i))) {
                        if (i != 0) {
                            permissionName.append("、");
                        }
                        isShouldShowTips = true;
                        switch (permissions.get(i)) {
                            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                permissionName.append(getString(R.string.write_permission));
                                break;
                            case Manifest.permission.ACCESS_FINE_LOCATION:
                                permissionName.append(getString(R.string.location_permission));
                                break;
                            case Manifest.permission.READ_PHONE_STATE:
                                permissionName.append(getString(R.string.phone_state_permission));
                                break;
                            case Manifest.permission.CAMERA:
                                permissionName.append(getString(R.string.camera_permission));
                                break;
                            default:
                                break;
                        }
                    }
                }
                final String[] permissionArray = permissions.toArray(new String[permissions.size()]);
                if (isShouldShowTips) {
                    createDialog(permissionName.toString(),getString(R.string.apply_permission), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(BasePermissionActivity.this,
                                    permissionArray, PERMISSION_REQUEST_CODE);
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(this, permissionArray, PERMISSION_REQUEST_CODE);
                }
            } else {
                onPermissionResult(true);
            }
        } else {
            onPermissionResult(true);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            StringBuilder permissionName = new StringBuilder();
            permissionName.append(getString(R.string.set_permission_tip));
            boolean isPermissionAllow = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (!isPermissionAllow) {
                        permissionName.append("、");
                    }
                    switch (permissions[i]) {
                        case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                            permissionName.append(getString(R.string.write_permission));
                            break;
                        case Manifest.permission.ACCESS_FINE_LOCATION:
                            permissionName.append(getString(R.string.location_permission));
                            break;
                        case Manifest.permission.READ_PHONE_STATE:
                            permissionName.append(getString(R.string.phone_state_permission));
                            break;
                        case Manifest.permission.CAMERA:
                            permissionName.append(getString(R.string.camera_permission));
                            break;
                        default:
                            break;
                    }
                    isPermissionAllow = false;
                }
            }
            if (isPermissionAllow) {
                onPermissionResult(true);
            } else {
                createDialog(permissionName.toString(), getString(R.string.I_know), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onPermissionResult(false);
                    }
                });
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    private void createDialog(String message, String posButtonMsg, DialogInterface.OnClickListener listener) {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.prompt))
                .setMessage(message)
                .setPositiveButton(posButtonMsg, listener)
                .create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 权限授予结果
     *
     * @param isAllow 是否全部允许
     */
    public abstract void onPermissionResult(boolean isAllow);

    public void goAppDetailSettingIntent(Context context) {
        // vivo 点击设置图标>加速白名单>我的app
        //      点击软件管理>软件管理权限>软件>我的app>信任该软件
        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        if (appIntent != null) {
            context.startActivity(appIntent);
            return;
        }

        // oppo 点击设置图标>应用权限管理>按应用程序管理>我的app>我信任该应用
        //      点击权限隐私>自启动管理>我的app
        appIntent = context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe");
        if (appIntent != null) {
            context.startActivity(appIntent);
            return;
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(intent);
    }
}
