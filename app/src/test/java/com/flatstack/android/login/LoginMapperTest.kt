package com.flatstack.android.login

import com.flatstack.android.graphql.mutation.LoginMutation
import com.flatstack.android.model.entities.Session
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class LoginMapperTest {
    @Test
    fun mapLogin() {
        val expectedAccessToken = "access_token"
        val expectedSignin = LoginMutation.Signin(accessToken = expectedAccessToken)
        val expectedSession = Session(accessToken = expectedAccessToken)

        val actualSession = LoginMapper().mapLogin(expectedSignin)

        assertEquals(actualSession, expectedSession)
    }
}