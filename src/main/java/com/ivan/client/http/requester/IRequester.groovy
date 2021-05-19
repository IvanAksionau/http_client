package com.ivan.client.http.requester

import org.apache.http.Header
import org.apache.http.entity.mime.content.ContentBody

@SuppressWarnings('MethodCount')
interface IRequester {

    /**
     * Performs the HTTP OPTIONS request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse options(String url, int ... validCodes)

    /**
     * Performs the HTTP OPTIONS request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse options(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP OPTIONS request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T options(String url, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP OPTIONS request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T options(String url, List<Header> headers, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP GET request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse get(String url, int ... validCodes)

    /**
     * Performs the HTTP GET request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse get(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP GET request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T get(String url, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP GET request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T get(String url, List<Header> headers, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP HEAD request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse head(String url, int ... validCodes)

    /**
     * Performs the HTTP HEAD request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse head(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, String body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, List<Header> headers, String body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, String body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, List<Header> headers, String body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, List<Header> headers, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, byte[] body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, File body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, List<Header> headers, File body, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, File body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies response status code against expected valid codes (if any passed)
     * and returns response.
     * @param url an url to which request should be addressed.
     * @param multipartData a collection of data which will be sent as multipart/form-data content.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, Map<String, ContentBody> multipartData, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies response status code against expected valid codes (if any passed)
     * and returns response.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param multipartData a collection of data which will be sent as multipart/form-data content.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, List<Header> headers, Map<String, ContentBody> multipartData, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param multipartData a collection of data which will be sent as multipart/form-data content.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, Map<String, ContentBody> multipartData, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param multipartData a collection of data which will be sent as multipart/form-data content.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url,
                      List<Header> headers,
                      Map<String, ContentBody> multipartData,
                      Class<T> responseType,
                      int ... validCodes)

    /**
     * Performs the HTTP POST request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, int ... validCodes)

    /**
     * Performs the HTTP POST request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse post(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP POST request without body,
     * verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP POST request without body,
     * verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T post(String url, List<Header> headers, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, String body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, List<Header> headers, String body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, String body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, List<Header> headers, String body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, List<Header> headers, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, byte[] body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, File body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, List<Header> headers, File body, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, File body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, int ... validCodes)

    /**
     * Performs the HTTP PUT request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse put(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP PUT request without body,
     * verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PUT request without body,
     * verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T put(String url, List<Header> headers, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP DELETE request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse delete(String url, int ... validCodes)

    /**
     * Performs the HTTP DELETE request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse delete(String url, List<Header> headers, int ... validCodes)

    /**
     * Performs the HTTP DELETE request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a string with possible body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse delete(String url, List<Header> headers, String body, int ... validCodes)

    /**
     * Performs the HTTP DELETE request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T delete(String url, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP DELETE request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T delete(String url, List<Header> headers, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, String body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, List<Header> headers, String body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, List<Header> headers, byte[] body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T patch(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, File body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed)
     * and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, List<Header> headers, File body, int ... validCodes)

    /**
     * Performs the HTTP PATCH request, verifies its status code against expected valid codes (if any passed),
     * converts response body to the specified type and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param body a body that should be sent in the request.
     * @param responseType a type to which response body should be converted.
     * @param validCodes a list of expected status codes.
     * @return response body converted to an expected type.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them or in case if response body can't be converted to an expected type.
     */
    public <T> T patch(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes)

    /**
     * Performs the HTTP PATCH request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, int ... validCodes)

    /**
     * Performs the HTTP PATCH request without body,
     * verifies its status code against expected valid codes (if any passed) and returns it.
     * @param url an url to which request should be addressed.
     * @param headers a list of additional headers that should be sent in the request.
     * @param validCodes a list of expected status codes.
     * @return http response.
     * @throws HttpRequestException in case if request wasn't performed by some reason.
     * @throws HttpResponseException in case if <b>validCodes</b> list is not empty and response code doesn't match any
     * of them.
     */
    HttpResponse patch(String url, List<Header> headers, int ... validCodes)
}
