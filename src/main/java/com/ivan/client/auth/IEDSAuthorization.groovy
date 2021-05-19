package com.ivan.client.auth

import com.ivan.client.http.requester.HttpResponse

interface IEDSAuthorization {

    /**
     * Gets an authentication token converts it into http header and saves to request headers context.
     * If authorization has been performed before and refresh token is present it will be used to refresh
     * authentication token.
     * Method expects response code to be 200.
     * @param url an url against which authorization should be performed.
     * @param user a username that will be used for authorization.
     * @param password a password that will be used for authorization.
     * @throws EDSAuthorizationException in case if authorization failed by any reason.
     */
    void authorize(String url, String user, String password)

    /**
     * Performs an authorization request and returns parsed response body.
     * This method is intended to be used in tests verifying authorization ability.
     * Method expects response code to be 200.
     * @param url an url against which authorization should be performed.
     * @param user a username that will be used for authorization.
     * @param password a password that will be used for authorization.
     * @return a POJO describing response body
     * @throws EDSAuthorizationException in case if authorization failed by any reason.
     */
    Authorization performAuthorizationRequest(String url, String user, String password)

    /**
     * Performs an authorization request and returns parsed response body.
     * This method is intended to be used in tests verifying authorization ability.
     * Method expects response code to be 200.
     * @param url an url against which authorization should be performed.
     * @param authRequest a POJO describing request body to be sent.
     * @return a POJO describing response body
     * @throws EDSAuthorizationException in case if authorization failed by any reason.
     */
    Authorization performAuthorizationRequest(String url, AuthRequest authRequest)

    /**
     * Performs an authorization request and returns http response.
     * This method is intended to be used in tests verifying authorization ability.
     * @param url an url against which authorization should be performed.
     * @param authRequest a POJO describing request body to be sent.
     * @param validCodes an expected response codes.
     * @return Http response.
     */
    HttpResponse performAuthorizationRequest(String url, AuthRequest authRequest, int ... validCodes)
}
