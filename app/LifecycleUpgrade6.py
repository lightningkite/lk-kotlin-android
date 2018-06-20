import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import lk.kotlin.lifecycle.LifecycleConnectable",
        "import lk.kotlin.observable.property.ObservableProperty"
    ),
    (
        "import lk.kotlin.lifecycle.LifecycleListener",
        ""
    ),
    (
        "LifecycleConnectable",
        "ObservableProperty<Boolean>"
    ),
    (
        "lifecycle.connect(object : LifecycleListener {",
        "lifecycle.openCloseBinding("
    ),
    (
        "override fun onStart() {",
        "onOpen = {"
    ),
    (
        "override fun onStop() {",
        "onClose = {"
    ),
    (
        "import lk.kotlin.lifecycle.listen",
        "import lk.kotlin.observable.property.lifecycle.listen"
    ),
]
regexRep = [
    # (
    #     r"import lk\.anko\.view\.controllers\.deprecated[^\n]*",
    #     ""
    # ),
]


def do_replace_kt(text):
    for pattern, replace in plainRep:
        text = text.replace(pattern, replace)
    for pattern, replace in regexRep:
        text = re.compile(pattern).sub(replace, text)
    return text


def do_replace_on_file(path):
    if path.endswith('.kt'):
        text = open(path).read()
        afterText = do_replace_kt(text)
        open(path, 'w').write(afterText)


for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        path = os.path.join(subdir, file)
        if 'build' in path:
            continue
        print path
        do_replace_on_file(path)
