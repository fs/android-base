package com.flatstack.android.login

import com.flatstack.android.graphql.mutation.LoginMutation
import com.flatstack.android.model.entities.Session

class LoginMapper {
    fun mapLogin(signIn: LoginMutation.Signin?) = Session(accessToken = signIn?.accessToken ?: "")
}
