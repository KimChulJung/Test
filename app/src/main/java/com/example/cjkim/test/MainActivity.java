package com.example.cjkim.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

//    private int test;

    private Pattern mPattern = Pattern.compile("(^[0-9]*$)");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText text = (EditText) findViewById(R.id.test_ed);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = text.getText().toString();
                boolean abc = mPattern.matcher(test).matches();

                Log.d("cjkim",String.valueOf(abc));
            }
        });
        Button test = findViewById(R.id.test);

        /* Layout Test
        RelativeLayout test = (RelativeLayout) findViewById(R.id.li_test);

        TextView tvJoin = new TextView(this);
        tvJoin.setText("회원가입.................");
        tvJoin.setTextColor(Color.RED);
        tvJoin.setIncludeFontPadding(false);
        tvJoin.setTextSize(15);
        tvJoin.setBackgroundColor(Color.BLUE);

//        slBody.setScale_TextSize(tvJoin, 15);

        float width;
        float textRealSize = tvJoin.getPaint().measureText(tvJoin.getText().toString());
//        float textWidth = (640 * textRealSize) / this.getResources().getDisplayMetrics().widthPixels;//getContext().getResources().getDisplayMetrics().widthPixels;

        tvJoin.setLayoutParams(new LinearLayout.LayoutParams((int) textRealSize, LinearLayout.LayoutParams.WRAP_CONTENT));
        test.addView(tvJoin);

        Log.d("cjkim",String.valueOf(textRealSize));
//        tvJoin.setLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
        */
        /* 지원국가 코드
        for (Locale locale : Locale.getAvailableLocales()) {
            Log.d("LOCALES", locale.getLanguage() + "_" + locale.getCountry() + " [" + locale.getDisplayName() + "]");
        }
        */
        getPackageList();
    }

    public String[] getPackageList() {
        PackageManager pkgMgr = MainActivity.this.getPackageManager();
        List<ResolveInfo> mApps;
        String[] arrayPkgName;

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0); //실행 가능한 Package

        arrayPkgName = new String[mApps.size()];
        Collections.sort(mApps, new ResolveInfo.DisplayNameComparator(pkgMgr));

        for (ResolveInfo info : mApps) {
            ActivityInfo ai = info.activityInfo;
            Log.d("app name", ai.loadLabel(pkgMgr).toString());
            Log.d("app package Name",""+ai.packageName);
            int resId = ai.applicationInfo.icon; //icon
        }

//        for (int i = 0; i < mApps.size(); i++) {
//            arrayPkgName[i] = mApps.get(i).activityInfo.packageName;
//            Log.d("cjkim package","앱["+ i +"] " + arrayPkgName[i] +" / 앱이름 :"+ mApps.get(i).activityInfo.loadLabel(pkgMgr).toString());
//        }

//        List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.PERMISSION_GRANTED);
//        for (PackageInfo pack : packs) {
//            Log.d("TAG", "| name    : " + pack.applicationInfo.loadLabel(pkgMgr).toString());
//            Log.d("TAG", "| package : " + pack.packageName);
//            Log.d("TAG", "| version : " + pack.versionName);
//        }

        return arrayPkgName;
    }
}
