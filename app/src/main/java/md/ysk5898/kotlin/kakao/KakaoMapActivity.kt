/*
 * Create by SangKwon on 2019. 10. 2.
 */

package md.ysk5898.kotlin.kakao

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.*
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityKakaoMapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class KakaoMapActivity : AppCompatActivity() {

    lateinit var binding: ActivityKakaoMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_kakao_map)
        binding.activity = this

        val mapView = MapView(this)
        mapView.mapType = MapView.MapType.Standard
        MapView.setMapTilePersistentCacheEnabled(true)
        binding.mapView.addView(mapView)

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.8351122, 128.5441227), true)

        initWebView()
        binding.webView.loadUrl("http://192.168.15.56:3000/kakao")
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        val webSetting: WebSettings = binding.webView.settings
        webSetting.javaScriptEnabled = true
        webSetting.useWideViewPort = true
        webSetting.displayZoomControls = false
        binding.webView.webViewClient = viewClient(this, binding.webView)

    }
}

class viewClient(val mContext: Context, var webView: WebView) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.e("111111", "onPageStarted")
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.e("111111", "$String")
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.e("111111", "onPageFinished")
    }
}