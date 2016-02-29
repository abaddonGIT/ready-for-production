package io.github.importre.rfp

import io.github.importre.rfp.intf.IScheduler
import rx.Scheduler
import rx.schedulers.Schedulers

class TestScheduler : IScheduler {

    override fun main(): Scheduler = Schedulers.immediate()

    override fun background(): Scheduler = Schedulers.immediate()
}