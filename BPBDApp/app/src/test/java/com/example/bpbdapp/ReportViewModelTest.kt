package com.example.bpbdapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(RobolectricTestRunner::class)
class ReportViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ReportViewModel
    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var submittingObserver: Observer<Boolean>

    @Mock
    private lateinit var statusObserver: Observer<Boolean>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ApiClient.instance = retrofit.create(ApiService::class.java)

        viewModel = ReportViewModel()
        viewModel.isSubmitting.observeForever(submittingObserver)
        viewModel.submissionStatus.observeForever(statusObserver)
        viewModel.errorMessage.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun submitReport_success() {
        val mockResponse = MockResponse().setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        viewModel.submitReport(Report("Test Title", "Test Description", null, 0))

        verify(submittingObserver).onChanged(true)
        // Add a small delay to allow the background thread to finish
        Thread.sleep(100)
        verify(submittingObserver).onChanged(false)
        verify(statusObserver).onChanged(true)
    }

    @Test
    fun submitReport_error() {
        val mockResponse = MockResponse().setResponseCode(500)
        mockWebServer.enqueue(mockResponse)

        viewModel.submitReport(Report("Test Title", "Test Description", null, 0))

        verify(submittingObserver).onChanged(true)
        // Add a small delay to allow the background thread to finish
        Thread.sleep(100)
        verify(submittingObserver).onChanged(false)
        verify(errorObserver).onChanged("Gagal mengirim laporan: Server Error")
    }
}
