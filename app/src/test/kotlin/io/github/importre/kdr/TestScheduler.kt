package io.github.importre.kdr

import io.github.importre.kdr.intf.IScheduler
import rx.Scheduler
import rx.schedulers.Schedulers

class TestScheduler : IScheduler {

    override fun main(): Scheduler = Schedulers.immediate()

    override fun background(): Scheduler = Schedulers.immediate()
}