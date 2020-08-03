package com.flatstack.android.login

import com.flatstack.android.graphql.mutation.LoginMutation
import com.flatstack.android.model.entities.Session

object LoginMapper {
    fun mapLogin(signin: LoginMutation.Signin?) = Session(accessToken = signin?.accessToken ?: "")
}
