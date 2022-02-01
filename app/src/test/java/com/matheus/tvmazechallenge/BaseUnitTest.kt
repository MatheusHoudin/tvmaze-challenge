package com.matheus.tvmazechallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
abstract class BaseUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setupTest() {
        initialize()
        Dispatchers.setMain(testDispatcher)
    }

    abstract fun initialize()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}