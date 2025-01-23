package com.example.displaylistidcompose.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.left
import arrow.core.right
import com.example.displaylistidcompose.model.HiringListItem
import com.example.displaylistidcompose.model.Response
import com.example.displaylistidcompose.network.HiringRepository
import com.example.displaylistidcompose.viewmodel.HiringViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class HiringViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: HiringRepository
    private lateinit var viewModel: HiringViewModel
    var testDispatcher= StandardTestDispatcher()
    @Before
    fun setUp() {
        //Initialized repository and viewmodel
        Dispatchers.setMain(testDispatcher)
        repository = mock(HiringRepository::class.java)
        viewModel = HiringViewModel(repository)

    }

    @Test
    fun `test the error case `() = runTest {

        val error = Throwable("error hiring")
        `when`(repository.getHiringList()).thenReturn(error.left())
        val job = launch {
            viewModel.hiringState.collect { response ->
                testDispatcher.scheduler.advanceUntilIdle()
                if (response is Response.Error) {
                    assertEquals(Response.Error(error), response)
                }

            }
        }
        viewModel.getList()

        job.cancel()

    }



    @Test
    fun `test the success case `() = runTest {

        val hiringList = listOf(
            HiringListItem(id = 1, listId = 1, name = "John Doe"),
            HiringListItem(id = 2, listId = 2, name = "Jane Smith"),
            HiringListItem(id = 3, listId = 3, name = "Bob Johnson"),
            HiringListItem(id = 4, listId = 4, name = "Alice Brown"),
            HiringListItem(id = 5, listId = 1, name = "John Doe"),
            HiringListItem(id = 6, listId = 2, name = "Jane Smith"),
            HiringListItem(id = 7, listId = 3, name = "Bob Johnson"),
            HiringListItem(id = 8, listId = 4, name = "Alice Brown"),
            HiringListItem(id = 9, listId = 1, name = "John Doe"),
            HiringListItem(id = 10, listId = 2, name = "Jane Smith"),
            HiringListItem(id = 11, listId = 3, name = "Bob Johnson"),
            HiringListItem(id = 12, listId = 4, name = "Alice Brown"),
            HiringListItem(id = 13, listId = 1, name = "John Doe"),
            HiringListItem(id = 14, listId = 2, name = "Jane Smith"),
            HiringListItem(id = 15, listId = 3, name = "Bob Johnson"),
            HiringListItem(id = 16, listId = 4, name = "Alice Brown"),
        )
        `when`(repository.getHiringList()).thenReturn(hiringList.right())

        val job1 = launch {
            viewModel.hiringState.collect { response ->
                testDispatcher.scheduler.advanceUntilIdle()
                if (response is Response.Success) {
                    assertEquals(Response.Success(hiringList), response)
                }
            }
        }
        viewModel.getList()
        job1.cancel()

    }


}







