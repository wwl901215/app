package com.wwl.can.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wwl.can.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.KeyEvent.KEYCODE_BACK;

//https://www.jianshu.com/p/b9164500d3fb
//https://www.jianshu.com/p/2b2e5d417e10
//https://www.jianshu.com/p/44b977907e51
public class WebViewActivity extends AppCompatActivity {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.back)
    Button back;
    @Bind(R.id.forword)
    Button forword;
    @Bind(R.id.webview_layout)
    LinearLayout webviewLayout;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.getjs)
    Button getjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        iniView();
    }

    private void iniView() {
        webview.onResume();
//        webview.loadUrl("http://10.115.5.186:8080/aaa.html");//加载网络url
//        webview.loadUrl("file:///android_asset/nio.html");//加载本地html文件
        webview.loadUrl("file:///android_asset/dist/index.html#/module_10009/detail?spuCode=XG20181030000007&version=2&header=%7B%22isShow%22%3Afalse%7D&wv=do");//加载本地html文件

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);// 优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不适用缓存
        //设置自适应屏幕，两者合用（下面这两个方法合用）
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        //参数1：Javascript对象名
        //参数2：Java对象名
        webview.addJavascriptInterface(new JsGetAndroidFun(WebViewActivity.this), "jsObject");//android的JsGetAndroidFun类映射到js的jsObject对象上；
        //处理各种通知、请求事件的
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (!TextUtils.isEmpty(url)) {
                    if (url.startsWith("tel:") || url.startsWith("nio://")) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            return true;
                        }
                    }
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                switch (errorCode) {
                    //该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
//                    case HttpStatus.SC_NOT_FOUND:
//                        view.loadUrl("file:///android_assets/error_handle.html");
//                        break;
                }

            }
        });
        //主要处理解析，渲染网页等浏览器做的事情
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            //(WebView上alert是弹不出来东西的，需要定制你的WebChromeClient处理弹出)
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(WebViewActivity.this, message, Toast.LENGTH_SHORT).show();
                result.cancel();//必须设置 不然alert只能调用一次，并且会导致退出webview再次进入的时候出现bug；
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Confirm");
                b.setMessage(message);
                b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    b.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            result.cancel();
                        }
                    });
                }
                b.create().show();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressbar.setVisibility(View.INVISIBLE);
                    progressbar.setProgress(0);
                }
            }
        });

//        Display display = getWindowManager().getDefaultDisplay();
//        int width = display.getWidth();
//        int height = display.getHeight();
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height * 2);
//        webview.setLayoutParams(lp);
//        webview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                ((WebView) view).requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });
    }


    @OnClick({R.id.back, R.id.forword, R.id.getjs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (webview.canGoBack()) {
                    webview.goBack();
                }
                break;
            case R.id.forword:
                if (webview.canGoForward()) {
                    webview.goForward();
                }
                break;
            case R.id.getjs:
//                webview.post(new Runnable() {//有人说需要把下面的代码放到这里，不然不会执行，但是不妨到里面也执行的啊
//                    @Override
//                    public void run() {
//
//                    }
//                });
                //方法2，效率高，不会刷新页面， android4.4以上版本，推荐
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webview.evaluateJavascript("javascript:getConfirm()",
                            new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String value) {
                                    Log.e("jsresult:", value);
                                }
                            });
                } else {
                    //方法1，效率低，会刷新页面，不推荐
                    webview.loadUrl("javascript:getConfirm()");
                }
        }
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.onPause();//停止动画 地理位置等内核处理，但是不能停止js；
            webview.pauseTimers();//全局停止js；
            webview.clearHistory();
//            webviewLayout.removeView(webview);//或者下面这个方法
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }

    //    问题：在不做任何处理前提下 ，浏览网页时点击系统的“Back”键,整个 Browser 会调用 finish()而结束自身
//    目标：点击返回后，是网页回退而不是推出浏览器
//    解决方案：在当前Activity中处理并消费掉该 Back 事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
