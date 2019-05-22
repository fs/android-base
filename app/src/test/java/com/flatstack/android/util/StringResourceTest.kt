package com.flatstack.android.util

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.spekframework.spek2.Spek

object StringResourceTest: Spek({
    test("getString") {
        // Arrange
        val expectedStringResource = 0
        val expectedString = "super string"
        val mockContext = mockk<Context>()
        every { mockContext.getString(expectedStringResource) } returns expectedString
        val stringResource = StringResource(mockContext)

        // Act
        val actualString = stringResource.getString(expectedStringResource)

        // Assert
        Assert.assertEquals(expectedString, actualString)
    }
})
