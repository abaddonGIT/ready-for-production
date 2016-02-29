package io.github.importre.kdr.presenter

import io.github.importre.kdr.TestScheduler
import io.github.importre.kdr.api.Github
import io.github.importre.kdr.api.repo.Repository
import io.github.importre.kdr.view.MainView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable
import java.util.*
import org.mockito.Mockito.`when` as When

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    lateinit var presenter: MainPresenter

    @Mock lateinit var view: MainView
    @Mock lateinit var api: Github
    @Mock lateinit var repos: ArrayList<Repository>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(api, TestScheduler())
        presenter.setView(view)
    }

    @After
    fun tearDown() {
        presenter.stop()
    }

    @Test
    fun shouldBeOk() {
        val user = "importre"
        val error = RuntimeException()

        When(api.getRepos(user)).thenReturn(Observable.create {
            it?.run {
                onNext(repos)
                onCompleted()
            }
        })

        presenter.loadRepos("importre", true)

        verify(view, times(1)).showLoading(true)
        verify(repos, times(1)).sort()
        verify(view, times(1)).showRepos(repos)
        verify(view, never()).showError(error)
        verify(view, times(1)).showLoading(false)
    }

    @Test
    fun shouldShowErrorIfFailure() {
        val user = "importre"
        val error = RuntimeException()

        When(api.getRepos(user)).thenReturn(Observable.create {
            it?.run {
                onError(error)
                onCompleted()
            }
        })

        presenter.loadRepos("importre", true)

        verify(view, times(1)).showLoading(true)
        verify(repos, never()).sort()
        verify(view, never()).showRepos(repos)
        verify(view, times(1)).showError(error)
        verify(view, times(1)).showLoading(false)
    }
}