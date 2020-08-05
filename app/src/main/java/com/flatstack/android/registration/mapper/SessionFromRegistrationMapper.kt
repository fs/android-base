package com.flatstack.android.registration

import com.flatstack.android.graphql.mutation.RegisterMutation
import com.flatstack.android.model.entities.Session

class SessionFromRegistrationMapper : (RegisterMutation.Signup?) -> Session {
    override fun invoke(sigunp: RegisterMutation.Signup?): Session =
        Session(accessToken = sigunp?.accessToken ?: "")
}
