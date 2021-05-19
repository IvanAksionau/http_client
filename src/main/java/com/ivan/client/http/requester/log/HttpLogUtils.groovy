package com.ivan.client.http.requester.log

import com.ivan.client.http.requester.HttpResponse

import java.nio.charset.StandardCharsets

import org.apache.http.Header
import org.apache.http.HeaderElement
import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.entity.ContentType
import org.apache.http.util.EntityUtils
import org.springframework.lang.NonNull

final class HttpLogUtils {

    private static final String[] PRINTABLE_MIME_TYPES = [
            ContentType.APPLICATION_FORM_URLENCODED.mimeType,
            ContentType.APPLICATION_ATOM_XML.mimeType,
            ContentType.APPLICATION_JSON.mimeType,
            ContentType.APPLICATION_XML.mimeType,
            ContentType.MULTIPART_FORM_DATA.mimeType,
            ContentType.TEXT_HTML.mimeType,
            ContentType.TEXT_PLAIN.mimeType,
            ContentType.TEXT_XML.mimeType
    ]

    private HttpLogUtils() {
        //Utility class
    }

    static Optional<String> convertToString(HttpEntity entity) {
        if (entity != null && entity.contentLength > 0) {
            return Optional.of(EntityUtils.toString(entity, StandardCharsets.UTF_8)
                    .replaceAll('password=[^&]*', 'password=*****'))
        }
        Optional.empty()
    }

    static boolean isPrintable(HttpResponse response) {
        Optional<String> mimeType = response.getHeaderByName(HttpHeaders.CONTENT_TYPE)
                .flatMap { header -> extractMimeType(header) }
        mimeType.isPresent() && mimeType.get() in PRINTABLE_MIME_TYPES
    }

    static Optional<String> extractMimeType(@NonNull Header contentTypeHeader) {
        HeaderElement[] elements = contentTypeHeader.elements
        if (elements.length > 0) {
            return Optional.of(elements[0].name)
        }
        Optional.empty()
    }
}
