package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityReportBinding

import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import android.view.View

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private lateinit var reportViewModel: ReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)

        observeViewModel()

        binding.submitReport.setOnClickListener {
            val title = binding.reportTitle.text.toString()
            val description = binding.reportDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val report = Report(title, description, null, System.currentTimeMillis())
                reportViewModel.submitReport(report)
            } else {
                Snackbar.make(binding.root, "Judul dan deskripsi tidak boleh kosong", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        reportViewModel.isSubmitting.observe(this, { isSubmitting ->
            binding.progressBar.visibility = if (isSubmitting) View.VISIBLE else View.GONE
            binding.submitReport.isEnabled = !isSubmitting
        })

        reportViewModel.submissionStatus.observe(this, { isSuccess ->
            if (isSuccess) {
                Snackbar.make(binding.root, "Laporan berhasil dikirim", Snackbar.LENGTH_LONG).show()
                finish()
            }
        })

        reportViewModel.errorMessage.observe(this, { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        })
    }
}
