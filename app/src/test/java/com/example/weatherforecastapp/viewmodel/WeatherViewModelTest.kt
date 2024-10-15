package com.example.weatherforecastapp.viewmodel

import android.os.Looper
import com.example.weatherforecastapp.api.FakeApiService
import com.example.weatherforecastapp.data.model.WeatherResponseModel
import com.example.weatherforecastapp.data.network.ApiRepository
import com.example.weatherforecastapp.presentation.model.UIState
import com.example.weatherforecastapp.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class WeatherViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiRepository: ApiRepository

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    @Mock
    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        weatherViewModel = WeatherViewModel(apiRepository)
    }

    @Test
    fun `test User data success`() = runBlockingTest {
        weatherViewModel.getWeatherData("london")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = weatherViewModel.weatherFlow.value
        assertTrue(value is UIState.Success<WeatherResponseModel>)
        assertNotNull(value.data)
        assertEquals("London", value.data?.location?.name)
        assertTrue(value.data?.current?.tempC!! > 0.0)
    }

    @Test
    fun `test User data api failure`() = runBlockingTest {
        fakeApiService.failUserApi = true
        weatherViewModel.getWeatherData("london")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = weatherViewModel.weatherFlow.value
        assertTrue(value is UIState.Error)
        assertNull(value.data)
    }

    @Test
    fun `test User wrong data`() = runBlockingTest {
        fakeApiService.wrongResponse = true
        weatherViewModel.getWeatherData("london")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = weatherViewModel.weatherFlow.value
        assertTrue(value is UIState.Success<WeatherResponseModel>)
        assertNotNull(value.data)
        assertEquals(null, value.data?.location)
        assertEquals(null, value.data?.current)
    }
}