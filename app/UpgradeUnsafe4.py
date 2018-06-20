import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "ui.owner",
        "access"
    ),
    (
        "ui.ctx",
        "access.context"
    ),

    (
        "import lk.anko.view.controllers.deprecated.AnkoViewController",
        "import lk.android.activity.access.ViewGenerator\nimport lk.anko.activity.access.anko"
    ),
    (
        ": AnkoViewController()",
        ": ViewGenerator"
    ),
    (
        ":AnkoViewController()",
        ":ViewGenerator"
    ),
    (
        "override fun createView(ui: AnkoContext<VCContext>): View = ui.",
        "override fun invoke(access: ActivityAccess): View = access.anko()."
    ),
    (
        "override fun createView(ui: AnkoContext<VCContext>): View {",
        "override fun invoke(access: ActivityAccess): View { /*TODO: fix*/"
    ),
    (
        "override fun createView(ui: AnkoContext<ActivityAccess>): View = ui.",
        "override fun invoke(access: ActivityAccess): View = access.anko()."
    ),
    (
        "override fun createView(ui: AnkoContext<ActivityAccess>): View {",
        "override fun invoke(access: ActivityAccess): View { /*TODO: fix*/"
    ),
    (
        "import lk.anko.view.controllers.deprecated.containers.VCStack",
        "import lk.kotlin.observable.property.StackObservableProperty"
    ),
    (
        "VCStack",
        "StackObservableProperty<ViewGenerator>"
    ),
    (
        "import lk.anko.view.controllers.deprecated.containers.VCSwapper",
        "import lk.kotlin.observable.property.StandardObservableProperty"
    ),
    (
        "VCSwapper",
        "StandardObservableProperty<ViewGenerator>"
    ),
    (
        "override fun getTitle",
        "fun getTitle"
    ),
    (
        "override fun onBackPressed",
        "/*TODO: Use*/ fun onBackPressed"
    ),
    (
        "import lk.anko.view.controllers.deprecated.implementations.viewControllerDialog",
        "import lk.android.mighty.view.stackDialog"
    ),
    (
        "viewControllerDialog",
        "stackDialog"
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
