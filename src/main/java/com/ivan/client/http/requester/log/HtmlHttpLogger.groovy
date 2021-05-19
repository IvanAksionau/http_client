package com.ivan.client.http.requester.log

import com.ivan.client.http.requester.HttpResponse
import com.ivan.client.utils.ReportPortalUtils
import com.ivan.client.utils.resources.ResourceUtils
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import groovy.util.logging.Slf4j
import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.ContentType
import org.slf4j.event.Level
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Slf4j
@Lazy
@Component
class HtmlHttpLogger implements IHttpLogger {

    private static final REQUEST_TEMPLATE_PATH = '/templates/httpMessage.tpl'

    private static final String BODY = 'body'

    @SuppressWarnings(['PrivateFieldCouldBeFinal', 'SpaceAfterClosingBrace'])
    @Lazy
    private MarkupTemplateEngine markupTemplateEngine = {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration()
        templateConfiguration.autoIndent = true
        templateConfiguration.autoNewLine = true
        new MarkupTemplateEngine(templateConfiguration)
    }()

    @SuppressWarnings(['PrivateFieldCouldBeFinal', 'SpaceAfterClosingBrace'])
    @Lazy
    private template = {
        markupTemplateEngine.createTemplate(ResourceUtils.loadResource(this.getClass(), REQUEST_TEMPLATE_PATH))
    }()

    @Override
    void logRequest(HttpUriRequest request) {
        Map<String, Object> model = [
                type       : 'request',
                method     : request.method,
                destination: request.URI.toString(),
                headers    : request.allHeaders,
        ]
        if (request in HttpEntityEnclosingRequestBase) {
            HttpEntity entity = request.entity
            HttpLogUtils.convertToString(entity).ifPresent {
                model.put(BODY, it)
                appendBodySyntax(model, request.allHeaders)
            }
        }
        attach(model, 'Attaching HTTP Request description.')
    }

    @Override
    void logResponse(HttpResponse response) {
        Map<String, Object> model = [
                type   : 'response',
                method : response.method,
                from   : response.from,
                headers: response.responseHeaders,
        ]
        if (HttpLogUtils.isPrintable(response)) {
            model.put(BODY, response.responseBodyAsString)
            appendBodySyntax(model, response.responseHeaders)
        }
        attach(model, 'Attaching HTTP Response description.')
    }

    private static void appendBodySyntax(Map<String, Object> model, Header[] headers) {
        String mimeType = Optional.ofNullable(headers.find { HttpHeaders.CONTENT_TYPE == it.name })
                .flatMap { header -> HttpLogUtils.extractMimeType(header) }
                .orElseGet { ContentType.DEFAULT_TEXT.mimeType }
        model.put('bodySyntax', convertToBodySyntax(mimeType))
    }

    private static String convertToBodySyntax(String mimeType) {
        mimeType.split('/').last()
    }

    private void attach(Map<String, Object> model, String message) {
        String attachment = template.make(model).toString().bytes.encodeBase64()
        ReportPortalUtils.attachFile(attachment, message, Level.INFO)
    }
}
