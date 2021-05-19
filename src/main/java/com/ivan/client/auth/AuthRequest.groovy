package com.ivan.client.auth

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class AuthRequest {

    String grantType = ''
    String username
    String password
    String clientId
    String scope
    String multiFactorAuthenticationCode
    String newPassword
    String refreshToken
    String clientSecret
    boolean takeExclusiveSignOnControl

    String toBody() {
        StringBuilder builder = new StringBuilder()

        builder.append('grant_type=').append(grantType)
        if (username) {
            builder.append('&username=').append(username)
        }
        if (password) {
            builder.append('&password=').append(password)
        }
        if (clientId) {
            builder.append('&client_id=').append(clientId)
        }
        if (scope) {
            builder.append('&scope=').append(scope)
        }
        if (multiFactorAuthenticationCode) {
            builder.append('&multiFactorAuthenticationCode=').append(multiFactorAuthenticationCode)
        }
        if (newPassword) {
            builder.append('&newPassword=').append(newPassword)
        }
        if (refreshToken) {
            builder.append('&refresh_token=').append(refreshToken)
        }
        if (clientSecret) {
            builder.append('&client_secret=').append(clientSecret)
        }
        builder.append('&takeExclusiveSignOnControl=').append(takeExclusiveSignOnControl)
    }

    static final class GrantType {

        public static final String PASSWORD = 'password'
        public static final String REFRESH_TOKEN = 'refresh_token'
    }
}
