import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import com.lightningkite.kotlin.anko.materialStyleAction",
        "import lk.kotlin.android.style.materialStyleAction"
    ),
    (
        "import com.lightningkite.kotlin.anko.materialStylePrimary",
        "import lk.kotlin.android.style.materialStylePrimary"
    ),
    (
        "import com.lightningkite.kotlin.anko.materialStyleSecondary",
        "import lk.kotlin.android.style.materialStyleSecondary"
    ),
    (
        "import com.lightningkite.kotlin.anko.materialStyleTertiary",
        "import lk.kotlin.android.style.materialStyleTertiary"
    ),
    (
        "import com.lightningkite.kotlin.anko.*",
        "import com.lightningkite.kotlin.anko.*\nimport lk.kotlin.android.style.*"
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
