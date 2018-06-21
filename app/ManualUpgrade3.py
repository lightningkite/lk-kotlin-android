import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import lk.everything.deprecated.networking.captureFailure",
        "import lk.kotlin.okhttp.thenOnFailure"
    ),
    (
        ".captureFailure {",
        ".thenOnFailure(UIThread) {"
    ),
    (
        "import lk.everything.deprecated.networking.captureSuccess",
        "import lk.kotlin.okhttp.thenOnSuccess"
    ),
    (
        ".captureSuccess {",
        ".thenOnSuccess(UIThread) {"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.mapReadOnly",
        "import lk.kotlin.observable.property.transform"
    ),
    (
        ".mapReadOnly {",
        ".transform {"
    ),
    (
        "com.google.gson.annotations.SerializedName",
        "com.fasterxml.jackson.annotation.JsonProperty",
    ),
    (
        "@SerializedName",
        "@JsonProperty"
    ),
]


def do_replace_kt(text):
    for pattern, replace in plainRep:
        text = text.replace(pattern, replace)
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

        do_replace_on_file(path)
