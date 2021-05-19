yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        title('Http Message')
        newLine()
        meta(charset: 'UTF-8')
        newLine()
        include template: 'com/tr/eds/qaframework/gatewaycommons/templates/externalStyles.tpl'
        newLine()
        style {
            include unescaped: 'com/tr/eds/qaframework/gatewaycommons/templates/styles.min.css'
        }
    }
    newLine()
    body {
        h3("HTTP ${type.capitalize()}")
        newLine()
        div("Method: ${method}")
        newLine()
        type == 'request' ?
            div {
                span('Destination: ')
                newLine()
                a(href: destination) { span(destination) }
            } :
            div {
                span('From: ')
                newLine()
                a(href: from) { span(from) }
            }
        newLine()
        hr()
        newLine()
        h4('Headers')
        table {
            thead {
                tr {
                    th('Name')
                    newLine()
                    th('Value')
                }
            }
            newLine()
            tbody {
                headers.each { header ->
                    tr {
                        td(header.name)
                        newLine()
                        td(header.value)
                    }
                }
            }
        }
        newLine()
        if(body) {
            h4('Body')
            div(class: 'tab') {
                button(class: 'tablinks active', onclick: "switchPreview(event, 'pretty')") { span('Pretty') }
                newLine()
                button(class: 'tablinks', onclick: "switchPreview(event, 'origin')") { span('Original') }
            }
            newLine()
            div(id: 'pretty', class: 'tabcontent active') {
                pre { code(id: 'pretty-code', class: bodySyntax) { span(body) } }
            }
            newLine()
            div(id: 'origin', class: 'tabcontent') {
                pre { code(id: 'original-code', class: bodySyntax) { span(body) } }
            }
            newLine()
            include template: 'com/tr/eds/qaframework/gatewaycommons/templates/scripts.tpl'
        }
    }
}
