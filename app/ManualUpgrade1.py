import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import com.lightningkite.kotlin.invokeAll",
        "import com.lightningkite.kotlin.lambda.invokeAll"
    ),
    (
        "import com.lightningkite.kotlin.networking.mapResult",
        "import com.lightningkite.kotlin.networking.transformResult"
    ),
    (
        "import com.lightningkite.kotlin.networking.mapResult",
        "import com.lightningkite.kotlin.networking.transformResult"
    ),
    (
        ".mapResult {",
        ".transformResult {"
    ),
    (
        "import com.lightningkite.kotlin.async.doUiThread",
        "import lk.android.ui.thread.UIThread\nimport lk.kotlin.jvm.utils.async.execute"
    ),
    (
        "doUIThread {",
        "UIThread.execute {"
    ),
    (
        "import com.lightningkite.kotlin.networking.toJsonElement",
        "import com.lightningkite.kotlin.networking.jackson.toJackson"
    ),
    (
        "import com.lightningkite.kotlin.networking.toJsonArray",
        "import com.lightningkite.kotlin.networking.jackson.toJacksonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToJson",
        "import com.lightningkite.kotlin.networking.jackson.jacksonToNode"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToString",
        "import com.lightningkite.kotlin.networking.jackson.jacksonToString"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonFrom",
        "import com.lightningkite.kotlin.networking.jackson.jacksonFromString"
    ),
    (
        "import com.lightningkite.kotlin.networking.jsonObject",
        "import com.lightningkite.kotlin.networking.jackson.jacksonObject"
    ),
    (
        "import com.lightningkite.kotlin.networking.jsonArray",
        "import com.lightningkite.kotlin.networking.jackson.jacksonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaGson",
        "import com.lightningkite.kotlin.networking.jackson.lambdaJackson"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToRequestBody",
        "import com.lightningkite.kotlin.networking.jackson.jacksonToRequestBody"
    ),
    (
        "toJsonElement",
        "toJackson"
    ),
    (
        "toJsonArray",
        "toJacksonArray"
    ),
    (
        "gsonToJson",
        "jacksonToNode"
    ),
    (
        "gsonToString",
        "jacksonToString"
    ),
    (
        "gsonFrom",
        "jacksonFromString"
    ),
    (
        "jsonObject",
        "jacksonObject"
    ),
    (
        "jsonArray",
        "jacksonArray"
    ),
    (
        "lambdaGson",
        "lambdaJackson"
    ),
    (
        "gsonToRequestBody",
        "jacksonToRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.VCContext",
        "import lk.android.activity.access.ActivityAccess"
    ),
    (
        "VCContext",
        "ActivityAccess"
    ),
    (
        "import com.lightningkite.kotlin.runAll",
        "import com.lightningkite.kotlin.lambda.invokeAll"
    ),
    (
        "runAll",
        "invokeAll"
    ),
    (
        "import com.google.gson.JsonElement",
        "import com.fasterxml.jackson.databind.JsonNode"
    ),
    (
        "JsonElement",
        "JsonNode"
    ),
    (
        ".dispose()",
        ".close()"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.DisposeLifecycle",
        "import lk.kotlin.lifecycle.CloseableLifecycle"
    ),
    (
        "onClick {",
        "setOnClickListener {"
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
        print path
        do_replace_on_file(path)
