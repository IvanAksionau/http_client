window.addEventListener('load', () => {
    const original = document.getElementById('original-code');
    const pretty = document.getElementById('pretty-code');

    if (original) {
        hljs.highlightBlock(original);
    }

    if (pretty) {
        if (pretty.className.includes('json')) {
            const text = pretty.innerText;
            const code = JSON.stringify(JSON.parse(text), null, 2);
            pretty.innerText = code;
        }
        hljs.highlightBlock(pretty);
    }
});
