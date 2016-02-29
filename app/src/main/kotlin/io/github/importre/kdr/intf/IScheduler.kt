package io.github.importre.kdr.intf

import rx.Scheduler

interface IScheduler {

    fun main(): Scheduler

    fun background(): Scheduler
}
