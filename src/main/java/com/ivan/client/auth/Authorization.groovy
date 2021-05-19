package com.ivan.client.auth

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.http.Header
import org.apache.http.HttpHeaders
import org.apache.http.message.BasicHeader

class Authorization {

    @JsonProperty('access_token')
    String accessToken

    @JsonProperty('refresh_token')
    String refreshToken

    @JsonProperty('expires_in')
    String expiresIn

    String scope

    @JsonProperty('token_type')
    String tokenType

    Header getAuthorizationHeader() {
        new BasicHeader(HttpHeaders.AUTHORIZATION, "${tokenType} ${accessToken}")
    }
}
