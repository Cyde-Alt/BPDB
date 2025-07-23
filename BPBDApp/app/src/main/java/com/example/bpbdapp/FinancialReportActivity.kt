package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityFinancialReportBinding

class FinancialReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Load financial report URL into WebView
        // val reportUrl = "http://your-api-url/finance/report"
        // binding.webView.loadUrl(reportUrl)
    }
}
