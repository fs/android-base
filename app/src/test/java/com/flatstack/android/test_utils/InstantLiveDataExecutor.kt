package com.flatstack.android.test_utils

import androidx.arch.core.executor.TaskExecutor

object InstantLiveDataExecutor : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean = true
}
