package com.example.medicinereminderapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.medicinereminderapplication.R

class buymedicne : AppCompatActivity() {
    lateinit var page: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buymedicne)
        page=findViewById(R.id.page)
        page.webViewClient= WebViewClient()
        page.loadUrl(" https://www.goodlife.co.ke/?gad=1&gclid=CjwKCAjw44mlBhAQEiwAqP3eVnMR4-L6_0-jGEzRAvIeKAf4zSKrBcmQiewwaM-9Ju6LS-ftuGJmwhoCG6cQAvD_BwE ")
        page.settings.javaScriptEnabled=true
        page.settings.setSupportZoom(true)

    }
}