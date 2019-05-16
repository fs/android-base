package com.flatstack.android

import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExtendWith(InstantExecutorExtension::class)
class ExampleUnitTest : Spek({
    describe("Example test") {
        it("should return 5") {
            val mockInt = mockk<MutableLiveData<Int>>()
            every { mockInt.value } returns 5
            assertEquals(5, mockInt.value)
        }
    }
})
