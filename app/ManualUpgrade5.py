import os
import re

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import lk.android.activity.access.ViewGenerator",
        "import lk.android.mighty.view.ViewGenerator"
    ),
    (
        "import lk.anko.activity.access.anko",
        "import lk.anko.extensions.anko"
    ),
    (
        "import lk.anko.view.controllers.deprecated.ViewController",
        ""
    ),
    (
        "import lk.everything.deprecated.anko.*",
        ""
    ),
    (
        "import lk.everything.deprecated.async.invokeAsync",
        "import lk.kotlin.jvm.utils.async.invokeOn\nimport lk.kotlin.jvm.utils.async.thenOn\nimport lk.kotlin.jvm.utils.async.Async\nimport lk.android.ui.thread.UIThread"
    ),
    (
        ".invokeAsync()",
        ".invokeOn(Async)"
    ),
    (
        ".invokeAsync {",
        ".thenOn(UIThread){ /*TODO actually invoke*/"
    ),
    (
        ".invokeUI()",
        ".invokeOn(UIThread)"
    ),
    (
        "override fun invoke(access: ActivityAccess): View = access.anko().",
        "override fun invoke(access: ActivityAccess): View = access.context.anko()."
    ),
    (
        "ViewController.EMPTY",
        "{View(it.context)}"
    ),
    (
        "import lk.anko.view.controllers.deprecated.dialogs.",
        "import lk.android.dialogs."
    ),
    (
        "override fun unmake(view: View)",
        "/*TODO: REMOVE*/ fun unmake(view: View)"
    ),
    (
        "import lk.everything.deprecated.anko.full.bindUri",
        "import lk.android.image.loading.observable.bindUri"
    ),
    (
        ".mapReadOnly {",
        ".transform {"
    ),
    (
        ".mapReadOnly(",
        ".transform("
    ),
    (
        "import lk.everything.deprecated.lambda.transform",
        "import lk.kotlin.utils.lambda.then"
    ),
    (
        "import lk.android.extensions.textColorResource",
        ""
    ),
]
regexRep = [
    (
        r"import lk\.anko\.view\.controllers\.deprecated[^\n]*",
        ""
    ),
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

        do_replace_on_file(path)
