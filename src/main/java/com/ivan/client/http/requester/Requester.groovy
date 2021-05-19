package com.ivan.client.http.requester

import com.fasterxml.jackson.databind.ObjectMapper
import com.ivan.client.http.requester.context.IRequestHeadersContext
import com.ivan.client.http.requester.log.IHttpLogger
import groovy.util.logging.Slf4j
import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpHead
import org.apache.http.client.methods.HttpOptions
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.ByteArrayEntity
import org.apache.http.entity.FileEntity
import org.apache.http.entity.StringEntity
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.ContentBody
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Autowired

@SuppressWarnings('MethodCount')
@Slf4j
class Requester implements IRequester {

    private final ObjectMapper objectMapper = new ObjectMapper()
    private final Closure modelSupplier = { byte[] body, Class type ->
        try {
            objectMapper.readValue(body, type)
        } catch (IOException e) {
            throw new HttpResponseException(e)
        }
    }

    private final IRequestHeadersContext requestHeadersContext
    private final CloseableHttpClient httpClient
    private final IHttpLogger httpLogger

    @Autowired
    Requester(IRequestHeadersContext requestHeadersContext, CloseableHttpClient httpClient, IHttpLogger httpLogger) {
        this.requestHeadersContext = requestHeadersContext
        this.httpClient = httpClient
        this.httpLogger = httpLogger
    }

    @Override
    HttpResponse options(String url, int ... validCodes) {
        options(url, [], validCodes)
    }

    @Override
    HttpResponse options(String url, List<Header> headers, int ... validCodes) {
        execute(new HttpOptions(url), headers, validCodes)
    }

    @Override
    <T> T options(String url, Class<T> responseType, int ... validCodes) {
        options(url, [], responseType, validCodes)
    }

    @Override
    <T> T options(String url, List<Header> headers, Class<T> responseType, int ... validCodes) {
        options(url, headers, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse get(String url, int ... validCodes) {
        get(url, [], validCodes)
    }

    @Override
    HttpResponse get(String url, List<Header> headers, int ... validCodes) {
        execute(new HttpGet(url), headers, validCodes)
    }

    @Override
    <T> T get(String url, Class<T> responseType, int ... validCodes) {
        get(url, [], responseType, validCodes)
    }

    @Override
    <T> T get(String url, List<Header> headers, Class<T> responseType, int ... validCodes) {
        get(url, headers, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse head(String url, int ... validCodes) {
        head(url, [], validCodes)
    }

    @Override
    HttpResponse head(String url, List<Header> headers, int ... validCodes) {
        execute(new HttpHead(url), headers, validCodes)
    }

    @Override
    HttpResponse post(String url, String body, int ... validCodes) {
        post(url, [], body, validCodes)
    }

    @Override
    HttpResponse post(String url, List<Header> headers, String body, int ... validCodes) {
        executePost(url, headers, new StringEntity(body), validCodes)
    }

    @Override
    <T> T post(String url, String body, Class<T> responseType, int ... validCodes) {
        post(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T post(String url, List<Header> headers, String body, Class<T> responseType, int ... validCodes) {
        post(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse post(String url, byte[] body, int ... validCodes) {
        post(url, [], body, validCodes)
    }

    @Override
    HttpResponse post(String url, List<Header> headers, byte[] body, int ... validCodes) {
        executePost(url, headers, new ByteArrayEntity(body), validCodes)
    }

    @Override
    <T> T post(String url, byte[] body, Class<T> responseType, int ... validCodes) {
        post(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T post(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes) {
        post(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse post(String url, File body, int ... validCodes) {
        post(url, [], body, validCodes)
    }

    @Override
    HttpResponse post(String url, List<Header> headers, File body, int ... validCodes) {
        executePost(url, headers, new FileEntity(body), validCodes)
    }

    @Override
    <T> T post(String url, File body, Class<T> responseType, int ... validCodes) {
        post(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T post(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes) {
        post(url, headers, body).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse post(String url, Map<String, ContentBody> multipartData, int ... validCodes) {
        post(url, [], multipartData, validCodes)
    }

    @Override
    HttpResponse post(String url, List<Header> headers, Map<String, ContentBody> multipartData, int ... validCodes) {

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create()
        multipartData.each { k, v ->
            entityBuilder.addPart(k, v)
        }
        executePost(url, headers, entityBuilder.build(), validCodes)
    }

    @Override
    <T> T post(String url, Map<String, ContentBody> multipartData, Class<T> responseType, int ... validCodes) {
        post(url, [], multipartData, responseType, validCodes)
    }

    @Override
    <T> T post(String url,
               List<Header> headers,
               Map<String, ContentBody> multipartData,
               Class<T> responseType,
               int ... validCodes) {
        post(url, headers, multipartData, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse post(String url, int ... validCodes) {
        post(url, [], validCodes)
    }

    @Override
    HttpResponse post(String url, List<Header> headers, int ... validCodes) {
        execute(new BodilessHttpPost(url), headers, validCodes)
    }

    @Override
    <T> T post(String url, Class<T> responseType, int ... validCodes) {
        post(url, [], responseType, validCodes)
    }

    @Override
    <T> T post(String url, List<Header> headers, Class<T> responseType, int ... validCodes) {
        post(url, headers, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse put(String url, String body, int ... validCodes) {
        put(url, [], body, validCodes)
    }

    @Override
    HttpResponse put(String url, List<Header> headers, String body, int ... validCodes) {
        executePut(url, headers, new StringEntity(body), validCodes)
    }

    @Override
    <T> T put(String url, String body, Class<T> responseType, int ... validCodes) {
        put(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T put(String url, List<Header> headers, String body, Class<T> responseType, int ... validCodes) {
        put(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse put(String url, byte[] body, int ... validCodes) {
        put(url, [], body, validCodes)
    }

    @Override
    HttpResponse put(String url, List<Header> headers, byte[] body, int ... validCodes) {
        executePut(url, headers, new ByteArrayEntity(body), validCodes)
    }

    @Override
    <T> T put(String url, byte[] body, Class<T> responseType, int ... validCodes) {
        put(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T put(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes) {
        put(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse put(String url, File body, int ... validCodes) {
        put(url, [], body, validCodes)
    }

    @Override
    HttpResponse put(String url, List<Header> headers, File body, int ... validCodes) {
        executePut(url, headers, new FileEntity(body), validCodes)
    }

    @Override
    <T> T put(String url, File body, Class<T> responseType, int ... validCodes) {
        put(url, [], body, responseType, validCodes)
    }

    @Override
    <T> T put(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes) {
        put(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse put(String url, int ... validCodes) {
        put(url, [], validCodes)
    }

    @Override
    HttpResponse put(String url, List<Header> headers, int ... validCodes) {
        execute(new BodilessHttpPut(url), headers, validCodes)
    }

    @Override
    <T> T put(String url, Class<T> responseType, int ... validCodes) {
        put(url, [], responseType, validCodes)
    }

    @Override
    <T> T put(String url, List<Header> headers, Class<T> responseType, int ... validCodes) {
        put(url, headers, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse delete(String url, int ... validCodes) {
        delete(url, [], validCodes)
    }

    @Override
    HttpResponse delete(String url, List<Header> headers, int ... validCodes) {
        execute(new HttpDelete(url), headers, validCodes)
    }

    @Override
    HttpResponse delete(String url, List<Header> headers, String body, int ... validCodes) {
        execute(new BodifullHttpDelete(url), headers, new StringEntity(body), validCodes)
    }

    @Override
    <T> T delete(String url, Class<T> responseType, int ... validCodes) {
        delete(url, [], responseType, validCodes)
    }

    @Override
    <T> T delete(String url, List<Header> headers, Class<T> responseType, int ... validCodes) {
        delete(url, headers, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse patch(String url, String body, int ... validCodes) {
        patch(url, [], body, validCodes)
    }

    @Override
    HttpResponse patch(String url, List<Header> headers, String body, int ... validCodes) {
        executePatch(url, headers, new StringEntity(body), validCodes)
    }

    @Override
    HttpResponse patch(String url, byte[] body, int ... validCodes) {
        patch(url, [], body, validCodes)
    }

    @Override
    HttpResponse patch(String url, List<Header> headers, byte[] body, int ... validCodes) {
        executePatch(url, headers, new ByteArrayEntity(body), validCodes)
    }

    @Override
    <T> T patch(String url, List<Header> headers, byte[] body, Class<T> responseType, int ... validCodes) {
        patch(url, headers, body, validCodes).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse patch(String url, File body, int ... validCodes) {
        patch(url, [], body, validCodes)
    }

    @Override
    HttpResponse patch(String url, List<Header> headers, File body, int ... validCodes) {
        executePatch(url, headers, new FileEntity(body), validCodes)
    }

    @Override
    <T> T patch(String url, List<Header> headers, File body, Class<T> responseType, int ... validCodes) {
        patch(url, headers, body).getResponseBodyAs(responseType)
    }

    @Override
    HttpResponse patch(String url, int ... validCodes) {
        patch(url, [], validCodes)
    }

    @Override
    HttpResponse patch(String url, List<Header> headers, int ... validCodes) {
        execute(new BodilessHttpPatch(url), headers, validCodes)
    }

    private HttpResponse executePost(String url, List<Header> headers, HttpEntity body, int ... validCodes) {
        execute(new HttpPost(url), headers, body, validCodes)
    }

    private HttpResponse executePut(String url, List<Header> headers, HttpEntity body, int ... validCodes) {
        execute(new HttpPut(url), headers, body, validCodes)
    }

    private HttpResponse executePatch(String url, List<Header> headers, HttpEntity body, int ... validCodes) {
        execute(new HttpPatch(url), headers, body, validCodes)
    }

    private HttpResponse execute(HttpEntityEnclosingRequestBase request, List<Header> headers, HttpEntity body,
                                 int ... validCodes) {
        request.entity = body
        execute(request, headers, validCodes)
    }

    private HttpResponse execute(HttpRequestBase request, List<Header> headers, int ... validCodes) {
        request.headers = mergeHeaders(headers)
        execute(request, validCodes)
    }

    private HttpResponse execute(HttpUriRequest request, int ... validCodes) {
        httpLogger.logRequest(request)
        HttpResponse response = new HttpResponse(modelSupplier)
        response.method = request.method
        response.from = request.URI
        try {
            httpClient.execute(request).withCloseable {
                HttpEntity entity = it.entity
                if (entity != null) {
                    response.responseBody = EntityUtils.toByteArray(entity)
                }
                Header[] headers = it.allHeaders
                response.responseHeaders = headers
                response.statusLine = it.statusLine
            }
        } catch (IOException e) {
            log.error('Request: [{}] failed with exception', request, e)
            throw new HttpRequestException(e)
        }
        httpLogger.logResponse(response)
        if (validCodes.size() > 0) {
            return response.verifyStatusCode(validCodes)
        }
        response
    }

    private List<Header> mergeHeaders(List<Header> externalHeaders) {
        List<Header> baseHeaders = requestHeadersContext.allHeaders
        externalHeaders.each { external ->
            baseHeaders.removeIf { base -> base.name == external.name }
        }
        [*baseHeaders, *externalHeaders]
    }
}
