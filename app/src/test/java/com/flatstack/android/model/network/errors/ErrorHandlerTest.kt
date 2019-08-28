package com.flatstack.android.model.network.errors

import com.flatstack.android.model.network.ErrorBody
import com.flatstack.android.model.network.ErrorResponse
import com.flatstack.android.util.StringResource
import com.flatstack.android.util.userMessage
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek
import retrofit2.HttpException
import java.net.HttpURLConnection.*

object ErrorHandlerTest : Spek({
    group("proceed") {
        beforeGroup {
            mockkStatic("com.flatstack.android.util.HumanViewExtensionsKt")
        }

        test("unknown") {
            val expectedString = "Hey, error is here"
            val mockThrowable = mockk<Throwable>()
            val mockkStringResource = mockk<StringResource>()
            every { mockThrowable.userMessage(mockkStringResource) } returns expectedString

            val errorHandler = spyk(ErrorHandler(mockk(), mockkStringResource, mockk(), mockk()))

            val actualString = runBlocking { errorHandler.proceed(mockThrowable) }

            Assert.assertEquals(expectedString, actualString)
            coVerify(inverse = true) { errorHandler.unAuthorize() }
        }

        test("HttpException UNAUTHORIZED") {
            val expectedString = "Hey, error is here"
            val expectedCode = HTTP_UNAUTHORIZED
            val mockThrowable = mockk<HttpException>()
            val mockkStringResource = mockk<StringResource>()
            every { mockThrowable.userMessage(mockkStringResource) } returns expectedString
            every { mockThrowable.code() } returns expectedCode

            val errorHandler = spyk(ErrorHandler(mockk(), mockkStringResource, mockk(), mockk()))
            coEvery { errorHandler.unAuthorize() } just Runs

            val actualString = runBlocking { errorHandler.proceed(mockThrowable) }

            Assert.assertEquals(expectedString, actualString)
            coVerify { errorHandler.unAuthorize() }
        }

        test("HttpException") {
            val expectedString = "Hey, error is here"
            val expectedCode = HTTP_BAD_REQUEST
            val mockThrowable = mockk<HttpException>()
            val mockkStringResource = mockk<StringResource>()
            every { mockThrowable.userMessage(mockkStringResource) } returns expectedString
            every { mockThrowable.code() } returns expectedCode

            val errorHandler = spyk(ErrorHandler(mockk(), mockkStringResource, mockk(), mockk()))

            val actualString = runBlocking { errorHandler.proceed(mockThrowable) }

            Assert.assertEquals(expectedString, actualString)
            coVerify(inverse = true) { errorHandler.unAuthorize() }
        }

        test("ServerError UNAUTHORIZED") {
            val expectedString = "Hey, error is here"
            val expectedCode = HTTP_UNAUTHORIZED
            val expectedErrorBodyText = "{Hey, I'm Json}"
            val expectedErrorBody = ErrorBody(expectedCode, expectedString)
            val expectedErrorResponse = ErrorResponse(expectedErrorBody)
            val mockThrowable = mockk<ServerError>()
            val mockkStringResource = mockk<StringResource>()
            val mockGson = mockk<Gson>()
            every { mockThrowable.code } returns expectedCode
            every { mockThrowable.errorBody } returns expectedErrorBodyText
            every { mockGson.fromJson(expectedErrorBodyText, ErrorResponse::class.java) } returns expectedErrorResponse

            val errorHandler = spyk(ErrorHandler(mockGson, mockkStringResource, mockk(), mockk()))
            coEvery { errorHandler.unAuthorize() } just Runs

            val actualString = runBlocking { errorHandler.proceed(mockThrowable) }

            Assert.assertEquals(expectedString, actualString)
            coVerify { errorHandler.unAuthorize() }
        }

        test("ServerError unparsed") {
            val expectedString = "Hey, error is here"
            val expectedErrorBodyText = "{Hey, I'm bad Json}"
            val mockThrowable = mockk<ServerError>()
            val mockkStringResource = mockk<StringResource>()
            val mockGson = mockk<Gson>()
            val mockkException = mockk<JsonSyntaxException>()
            every { mockThrowable.userMessage(mockkStringResource) } returns expectedString
            every { mockThrowable.errorBody } returns expectedErrorBodyText
            every { mockThrowable.code } returns HTTP_OK
            every { mockGson.fromJson(expectedErrorBodyText, ErrorResponse::class.java) } throws mockkException

            val errorHandler = spyk(ErrorHandler(mockGson, mockkStringResource, mockk(), mockk()))

            val actualString = runBlocking { errorHandler.proceed(mockThrowable) }

            Assert.assertEquals(expectedString, actualString)
            coVerify(inverse = true) { errorHandler.unAuthorize() }
        }
    }
})
