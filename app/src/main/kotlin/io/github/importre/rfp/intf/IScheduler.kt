package io.github.importre.rfp.intf

import rx.Scheduler

interface IScheduler {

    fun main(): Scheduler

    fun background(): Scheduler
}
