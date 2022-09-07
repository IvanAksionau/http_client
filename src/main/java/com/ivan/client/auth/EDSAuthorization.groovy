package com.ivan.client.auth

import com.ivan.client.auth.AuthRequest.GrantType
import com.ivan.client.context.IContext
import com.ivan.client.http.requester.HttpResponse
import com.ivan.client.http.requester.HttpResponseException
import com.ivan.client.http.requester.IRequester
import com.ivan.client.http.requester.context.IRequestHeadersContext
import com.ivan.client.utils.HttpRetryExecutor
import com.ivan.client.utils.RetryExecutor
import groovy.util.logging.Slf4j
import org.apache.http.Header
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus
import org.apache.http.entity.ContentType
import org.apache.http.message.BasicHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

import java.time.Duration

@Slf4j
@Lazy
@Component
class EDSAuthorization implements IEDSAuthorization {
    private static final String EXCEPTION_MESSAGE = 'Authorization failed.'

    @Value('${com.tr.eds.qaframework.gatewaycommons.auth.EDSAuthorization/clientId}')
    private String clientId

    @Value('${com.tr.eds.qaframework.gatewaycommons.auth.EDSAuthorization/authPath}')
    private String authPath

    @Value('${com.tr.eds.qaframework.gatewaycommons.auth.EDSAuthorization/scope}')
    private String scope

    @Autowired
    private IContext context

    @Autowired
    private IRequestHeadersContext requestHeadersContext

    @Autowired
    private IRequester requester

    @Override
    void authorize(String url, String user, String password) {
        Authorization authorization = performAuthorizationRequest(url, user, password)
        context.put(Authorization, authorization)
        requestHeadersContext.putAuthHeader(authorization.authorizationHeader)
    }

    @Override
    Authorization performAuthorizationRequest(String url, String user, String password) {
        performAuthorizationRequest(url, buildAuthRequest(authorization, user, password))
    }

    @Override
    Authorization performAuthorizationRequest(String url, AuthRequest authRequest) {
        try {
            performAuthorizationRequest(url, authRequest, HttpStatus.SC_OK).getResponseBodyAs(Authorization)
        } catch (HttpResponseException e) {
            throw new EDSAuthorizationException(EXCEPTION_MESSAGE, e)
        }
    }

    @Override
    HttpResponse performAuthorizationRequest(String url, AuthRequest authRequest, int ... validCodes) {
        log.info('Performing EDS authorization as user: {}', authRequest.username)
        requestHeadersContext.removeAuthHeader()

        executeAuthorization(url, authRequest.toBody(), validCodes)
    }

    private HttpResponse executeAuthorization(String url, String body, int ... validCodes) {
        Integer duration = 10
        Integer interval = 3000
        try {
            RetryExecutor executor = new HttpRetryExecutor()

            HttpResponse authorizationResponse = executor
                    .setPool(Duration.ofSeconds(duration), interval)
                    .setExpression { requester.post(url + authPath, buildHeaders(), body, validCodes) }
                    .setCondition { it.statusCode == HttpStatus.SC_OK }
                    .execute()

            log.info('Authorization complete.')
            return authorizationResponse
        } catch (HttpResponseException | InterruptedException e) {
            throw new EDSAuthorizationException(EXCEPTION_MESSAGE, e)
        }
    }

    private AuthRequest buildAuthRequest(Authorization authorization, String user, String password) {
        if (authorization == null) {
            return buildAuthRequest(user, password)
        }
        log.info('Refresh token is present. Session will be refreshed')
        buildRefreshRequest(user, authorization.refreshToken)
    }

    private AuthRequest buildAuthRequest(String user, String password) {
        buildRequest(GrantType.PASSWORD, user, scope).withPassword(password)
    }

    private AuthRequest buildRefreshRequest(String user, String refreshToken) {
        buildRequest(GrantType.REFRESH_TOKEN, user, '').withRefreshToken(refreshToken)
    }

    private AuthRequest buildRequest(String grantType, String user, String scope) {
        new AuthRequest()
                .withGrantType(grantType)
                .withUsername(user)
                .withClientId(clientId)
                .withScope(scope)
                .withTakeExclusiveSignOnControl(true)
    }

    private Authorization getAuthorization() {
        context.get(Authorization)
    }

    private static List<Header> buildHeaders() {
        [new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.mimeType)]
    }

    IContext getContext() {
        context
    }
}
