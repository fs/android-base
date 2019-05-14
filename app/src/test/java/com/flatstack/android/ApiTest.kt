package com.flatstack.android

import com.flatstack.android.di.modules.netModule
import com.flatstack.android.login.entities.LoginRequest
import com.flatstack.android.login.entities.LoginResponse
import com.flatstack.android.model.entities.Session
import com.flatstack.android.model.network.ApiErrorResponse
import com.flatstack.android.model.network.ApiSuccessResponse
import com.flatstack.android.model.network.IApi
import com.flatstack.android.model.network.errors.ServerError
import com.flatstack.android.profile.entities.Book
import com.flatstack.android.profile.entities.Profile
import com.flatstack.android.profile.entities.ProfileResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.with
import org.spekframework.spek2.Spek
import java.util.logging.Level
import java.util.logging.Logger

object ApiTest : Spek({
    val mockServer = MockWebServer()

    beforeGroup {
        Logger.getLogger(MockWebServer::class.java.name).level = Level.WARNING
        mockServer.start()
    }

    afterGroup {
        mockServer.shutdown()
    }

    group("login") {
        test("happy path") {
            val expectedToken = "token"
            val expectedJson = "\n" +
                    "{\n" +
                    "  \"session\": {\n" +
                    "    \"access_token\": \"$expectedToken\"\n" +
                    "  }\n" +
                    "}"

            val mockResponse = MockResponse()
                .setResponseCode(201)
                .setBody(expectedJson)
            mockServer.enqueue(mockResponse)

            val expectedLoginRequest =
                LoginRequest("TimurSuccess", "Flatstack1234!")
            val expectedSession = Session(0, expectedToken)
            val expectedLoginResponse = LoginResponse(expectedSession)
            val expectedApiResponse = ApiSuccessResponse(expectedLoginResponse)

            val url = mockServer.url("/v1/session/")
            val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
            val kodein = Kodein {
                import(netModule, allowOverride = true)
                constant("baseUrl", overrides = true) with expectedBaseUrl
            }
            val api: IApi by kodein.instance()

            // Act
            val actualApiResponse = runBlocking { api.loginAsync(expectedLoginRequest).await() }

            // Assert
            assertEquals(expectedApiResponse, actualApiResponse)
        }

        test("fail") {
            val expectedErrorMessage = "Authentication failed"
            val expectedErrorCode = 401
            val expectedJson = "{\n" +
                    "           \"error\": {\n" +
                    "               \"status\": $expectedErrorCode,\n" +
                    "               \"error\": \"$expectedErrorMessage\"\n" +
                    "           }\n" +
                    "       }"

            val mockResponse = MockResponse()
                .setResponseCode(401)
                .setBody(expectedJson)
            mockServer.enqueue(mockResponse)

            val expectedLoginRequest =
                LoginRequest("Timur", "Flatstack1234!")
            val expectedThrowable = ServerError(401, expectedJson)

            val expectedApiResponse = ApiErrorResponse<LoginResponse>(expectedThrowable)

            val url = mockServer.url("/v1/session/")
            val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
            val kodein = Kodein {
                import(netModule, allowOverride = true)
                constant("baseUrl", overrides = true) with expectedBaseUrl
            }
            val api: IApi by kodein.instance()

            // Act
            val actualApiResponse = runBlocking { api.loginAsync(expectedLoginRequest).await() }

            // Assert
            assertEquals(expectedApiResponse, actualApiResponse)
        }
    }

    group("profile") {
        test("happy path") {
            val expectedId = 0
            val expectedUserName = "John Snow"
            val expectedBook1 = Book(0, "Hey hey", 1)
            val expectedBook2 = Book(2, "GoT 5", 15)
            val expectedFavoriteBook = Book(4, "#$%^&*( ./.", 16)
            val expectedProfile = Profile(expectedId, expectedUserName, expectedFavoriteBook)
            expectedProfile.booksRead = listOf(expectedBook1, expectedBook2)
            val expectedProfileResponse = ProfileResponse(expectedProfile)
            val expectedApiResponse = ApiSuccessResponse(expectedProfileResponse)
            val expectedJson = "{\n" +
                    "        \"user\": {\n" +
                    "            \"username\": \"$expectedUserName\",\n" +
                    "            \"favorite_book\": {\n" +
                    "                \"id\": ${expectedFavoriteBook.id},\n" +
                    "                \"title\": \"${expectedFavoriteBook.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedFavoriteBook.numberOfTimesRead}\n" +
                    "            },\n" +
                    "        \"books_read\": [\n" +
                    "            {\n" +
                    "                \"id\": ${expectedBook1.id},\n" +
                    "                \"title\": \"${expectedBook1.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedBook1.numberOfTimesRead}\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": ${expectedBook2.id},\n" +
                    "                \"title\": \"${expectedBook2.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedBook2.numberOfTimesRead}\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}"

            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(expectedJson)
            mockServer.enqueue(mockResponse)

            val expectedToken = "Hey Token is here"

            val url = mockServer.url("/v1/profile/")
            val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
            val kodein = Kodein {
                import(netModule, allowOverride = true)
                constant("baseUrl", overrides = true) with expectedBaseUrl
            }
            val api: IApi by kodein.instance()

            // Act
            val actualApiResponse = runBlocking { api.loadUserAsync(expectedToken).await() }

            // Assert
            assertEquals(expectedApiResponse, actualApiResponse)
        }

        test("no favorite book") {
            val expectedId = 0
            val expectedUserName = "John Snow"
            val expectedBook1 = Book(0, "Hey hey", 1)
            val expectedBook2 = Book(2, "GoT 5", 15)
            val expectedProfile = Profile(expectedId, expectedUserName, null)
            expectedProfile.booksRead = listOf(expectedBook1, expectedBook2)
            val expectedProfileResponse = ProfileResponse(expectedProfile)
            val expectedApiResponse = ApiSuccessResponse(expectedProfileResponse)
            val expectedJson = "{\n" +
                    "        \"user\": {\n" +
                    "            \"username\": \"$expectedUserName\",\n" +
                    "        \"books_read\": [\n" +
                    "            {\n" +
                    "                \"id\": ${expectedBook1.id},\n" +
                    "                \"title\": \"${expectedBook1.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedBook1.numberOfTimesRead}\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": ${expectedBook2.id},\n" +
                    "                \"title\": \"${expectedBook2.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedBook2.numberOfTimesRead}\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}"

            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(expectedJson)
            mockServer.enqueue(mockResponse)

            val expectedToken = "No Favorite Book Token"

            val url = mockServer.url("/v1/profile/")
            val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
            val kodein = Kodein {
                import(netModule, allowOverride = true)
                constant("baseUrl", overrides = true) with expectedBaseUrl
            }
            val api: IApi by kodein.instance()

            // Act
            val actualApiResponse = runBlocking { api.loadUserAsync(expectedToken).await() }

            // Assert
            assertEquals(expectedApiResponse, actualApiResponse)
        }

        test("no books  to read") {
            val expectedId = 0
            val expectedUserName = "John Snow"
            val expectedFavoriteBook = Book(4, "#$%^&*( ./.", 16)
            val expectedProfile = Profile(expectedId, expectedUserName, expectedFavoriteBook)
            expectedProfile.booksRead = listOf()
            val expectedProfileResponse = ProfileResponse(expectedProfile)
            val expectedApiResponse = ApiSuccessResponse(expectedProfileResponse)
            val expectedJson = "{\n" +
                    "        \"user\": {\n" +
                    "            \"username\": \"$expectedUserName\",\n" +
                    "            \"favorite_book\": {\n" +
                    "                \"id\": ${expectedFavoriteBook.id},\n" +
                    "                \"title\": \"${expectedFavoriteBook.title}\",\n" +
                    "                \"number_of_times_read\": ${expectedFavoriteBook.numberOfTimesRead}\n" +
                    "            },\n" +
                    "        \"books_read\": [\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}"

            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(expectedJson)
            mockServer.enqueue(mockResponse)

            val expectedToken = "Hey Token is here"

            val url = mockServer.url("/v1/profile/")
            val expectedBaseUrl = url.scheme() + "://" + url.host() + ":" + url.port()
            val kodein = Kodein {
                import(netModule, allowOverride = true)
                constant("baseUrl", overrides = true) with expectedBaseUrl
            }
            val api: IApi by kodein.instance()

            // Act
            val actualApiResponse = runBlocking { api.loadUserAsync(expectedToken).await() }

            // Assert
            assertEquals(expectedApiResponse, actualApiResponse)
        }
    }
})