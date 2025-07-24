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
class NewsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel
    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var newsObserver: Observer<List<News>>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

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

        viewModel = NewsViewModel()
        viewModel.news.observeForever(newsObserver)
        viewModel.isLoading.observeForever(loadingObserver)
        viewModel.errorMessage.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getNews_success() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[{\"id\":\"1\",\"title\":\"Test News\",\"content\":\"Test Content\",\"imageUrl\":\"\",\"createdAt\":\"\"}]")
        mockWebServer.enqueue(mockResponse)

        viewModel.getNews()

        verify(loadingObserver).onChanged(true)
        // Add a small delay to allow the background thread to finish
        Thread.sleep(100)
        verify(loadingObserver).onChanged(false)
        verify(newsObserver).onChanged(listOf(News("1", "Test News", "Test Content", "", 0)))
    }

    @Test
    fun getNews_error() {
        val mockResponse = MockResponse().setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        viewModel.getNews()

        verify(loadingObserver).onChanged(true)
        // Add a small delay to allow the background thread to finish
        Thread.sleep(100)
        verify(loadingObserver).onChanged(false)
        verify(errorObserver).onChanged("Gagal memuat berita: Client Error")
    }
}
