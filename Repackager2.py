import os

rootdir = os.path.dirname(os.path.abspath(__file__))

plainRep = [
    (
        "import com.lightningkite.kotlin.anko.animation.*",
        "import lk.anko.animations.*\nimport lk.android.animations.*\nimport lk.everything.deprecated.anko.animation.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.*",
        "import lk.android.dialogs.*"
    ),
    (
        "import com.lightningkite.kotlin.files.*",
        "import lk.kotlin.jvm.utils.files.*"
    ),
    (
        "import com.lightningkite.kotlin.observable.*",
        "import lk.everything.deprecated.observable.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.*",
        "import lk.android.observable.validation.*"
    ),
    (
        "import org.jetbrains.anko.*",
        "import org.jetbrains.anko.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.image.*",
        "import lk.android.image.loading.image.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.*",
        "import lk.anko.view.controllers.deprecated.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.full.*",
        "import lk.everything.deprecated.anko.full.*\nimport lk.android.image.loading.observable.*\nimport lk.kotlin.observable.property.jvm.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.*",
        "import lk.anko.adapters.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.*",
        "import lk.android.design.extensions.*\nimport lk.everything.deprecated.anko.*\nimport lk.android.animations.observable.*\nimport lk.android.lifecycle.*\nimport lk.android.extensions.*\nimport lk.anko.extensions.*"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.*",
        "import lk.kotlin.observable.property.lifecycle.*\nimport lk.everything.deprecated.observable.property.*\nimport lk.kotlin.observable.property.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.*",
        "import lk.anko.view.controllers.deprecated.image.*"
    ),
    (
        "import com.lightningkite.kotlin.*",
        "import lk.everything.deprecated.*\nimport lk.kotlin.utils.*"
    ),
    (
        "import com.lightningkite.kotlin.math.*",
        "import lk.kotlin.utils.math.*"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.*",
        "import lk.kotlin.okhttp.jackson.*\nimport lk.kotlin.jackson.*"
    ),
    (
        "import com.lightningkite.kotlin.bytes.*",
        "import lk.kotlin.utils.bytes.*"
    ),
    (
        "import com.lightningkite.kotlin.async.*",
        "import lk.everything.deprecated.async.*\nimport lk.kotlin.jvm.utils.async.*"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.*",
        "import lk.kotlin.observable.list.lifecycle.*\nimport lk.kotlin.observable.list.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.*",
        "import lk.anko.view.controllers.deprecated.implementations.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.net.*",
        "import lk.anko.view.controllers.deprecated.net.*"
    ),
    (
        "import com.lightningkite.kotlin.range.*",
        "import lk.kotlin.jvm.utils.range.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.async.*",
        "import lk.everything.deprecated.anko.async.*\nimport lk.android.ui.thread.*"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.*",
        "import lk.kotlin.lifecycle.*"
    ),
    (
        "import com.lightningkite.kotlin.lambda.*",
        "import lk.kotlin.jvm.utils.lambda.*\nimport lk.kotlin.utils.lambda.*\nimport lk.everything.deprecated.lambda.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.*",
        "import lk.android.image.loading.*"
    ),
    (
        "import com.lightningkite.kotlin.collection.*",
        "import lk.kotlin.utils.collection.*\nimport lk.kotlin.jvm.utils.collection.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.*",
        "import lk.anko.view.controllers.deprecated.dialogs.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.*",
        "import lk.android.extensions.files.*"
    ),
    (
        "import com.lightningkite.kotlin.networking.*",
        "import lk.kotlin.okhttp.*\nimport lk.everything.deprecated.networking.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.*",
        "import lk.anko.adapters.observable.*"
    ),
    (
        "import com.lightningkite.kotlin.stream.*",
        "import lk.kotlin.jvm.utils.stream.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.*",
        "import lk.android.activity.access.*\nimport lk.anko.activity.access.*"
    ),
    (
        "import com.lightningkite.kotlin.text.*",
        "import lk.everything.deprecated.text.*\nimport lk.kotlin.utils.text.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.*",
        "import lk.anko.view.controllers.deprecated.containers.*"
    ),
    (
        "import com.lightningkite.kotlin.date.*",
        "import lk.kotlin.jvm.utils.date.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.*",
        "import lk.everything.deprecated.anko.observable.*\nimport lk.anko.animations.observable.*\nimport lk.android.observable.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.*",
        "import lk.android.extensions.image.*"
    ),
    (
        "import com.lightningkite.kotlin.anko.ViewLifecycleListener",
        "import lk.android.lifecycle.ViewLifecycleListener"
    ),
    (
        "import com.lightningkite.kotlin.anko.forThisAndAllChildrenRecursive",
        "import lk.android.lifecycle.forThisAndAllChildrenRecursive"
    ),
    (
        "import com.lightningkite.kotlin.anko.lifecycle",
        "import lk.android.lifecycle.lifecycle"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.anko",
        ""
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.MyJackson",
        "import lk.kotlin.jackson.MyJackson"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonArray",
        "import lk.kotlin.jackson.jacksonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonFromString",
        "import lk.kotlin.jackson.jacksonFromString"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonFromStringNode",
        "import lk.kotlin.jackson.jacksonFromStringNode"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonObject",
        "import lk.kotlin.jackson.jacksonObject"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonToNode",
        "import lk.kotlin.jackson.jacksonToNode"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonToNodeAs",
        "import lk.kotlin.jackson.jacksonToNodeAs"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonToString",
        "import lk.kotlin.jackson.jacksonToString"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonToStringAs",
        "import lk.kotlin.jackson.jacksonToStringAs"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.toJackson",
        "import lk.kotlin.jackson.toJackson"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.toJacksonArray",
        "import lk.kotlin.jackson.toJacksonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.toJacksonObject",
        "import lk.kotlin.jackson.toJacksonObject"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.animateHighlight",
        "import lk.everything.deprecated.anko.animation.animateHighlight"
    ),
    (
        "import com.lightningkite.kotlin.anko.async.AndroidAsync",
        "import lk.everything.deprecated.anko.async.AndroidAsync"
    ),
    (
        "import com.lightningkite.kotlin.anko.full.bindUri",
        "import lk.everything.deprecated.anko.full.bindUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.FormLayout",
        "import lk.everything.deprecated.anko.observable.FormLayout"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindDoubleAutoComma",
        "import lk.everything.deprecated.anko.observable.bindDoubleAutoComma"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindNullableDoubleAutoComma",
        "import lk.everything.deprecated.anko.observable.bindNullableDoubleAutoComma"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.formLayout",
        "import lk.everything.deprecated.anko.observable.formLayout"
    ),
    (
        "import com.lightningkite.kotlin.anko.AdapterLinearLayout",
        "import lk.everything.deprecated.anko.AdapterLinearLayout"
    ),
    (
        "import com.lightningkite.kotlin.anko.FooterItemDecoration",
        "import lk.everything.deprecated.anko.FooterItemDecoration"
    ),
    (
        "import com.lightningkite.kotlin.anko.HeaderItemDecoration",
        "import lk.everything.deprecated.anko.HeaderItemDecoration"
    ),
    (
        "import com.lightningkite.kotlin.anko.HorizontalListView",
        "import lk.everything.deprecated.anko.HorizontalListView"
    ),
    (
        "import com.lightningkite.kotlin.anko.NumericalString",
        "import lk.everything.deprecated.anko.NumericalString"
    ),
    (
        "import com.lightningkite.kotlin.anko.ProgressButton",
        "import lk.everything.deprecated.anko.ProgressButton"
    ),
    (
        "import com.lightningkite.kotlin.anko.ScrollStatus",
        "import lk.everything.deprecated.anko.ScrollStatus"
    ),
    (
        "import com.lightningkite.kotlin.anko.Validation",
        "import lk.everything.deprecated.anko.Validation"
    ),
    (
        "import com.lightningkite.kotlin.anko.ValidationIssue",
        "import lk.everything.deprecated.anko.ValidationIssue"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapterLinearLayout",
        "import lk.everything.deprecated.anko.adapterLinearLayout"
    ),
    (
        "import com.lightningkite.kotlin.anko.add",
        "import lk.everything.deprecated.anko.add"
    ),
    (
        "import com.lightningkite.kotlin.anko.autoComma",
        "import lk.everything.deprecated.anko.autoComma"
    ),
    (
        "import com.lightningkite.kotlin.anko.firstIssue",
        "import lk.everything.deprecated.anko.firstIssue"
    ),
    (
        "import com.lightningkite.kotlin.anko.footer",
        "import lk.everything.deprecated.anko.footer"
    ),
    (
        "import com.lightningkite.kotlin.anko.header",
        "import lk.everything.deprecated.anko.header"
    ),
    (
        "import com.lightningkite.kotlin.anko.horizontalListView",
        "import lk.everything.deprecated.anko.horizontalListView"
    ),
    (
        "import com.lightningkite.kotlin.anko.issues",
        "import lk.everything.deprecated.anko.issues"
    ),
    (
        "import com.lightningkite.kotlin.anko.lparamsMod",
        "import lk.everything.deprecated.anko.lparamsMod"
    ),
    (
        "import com.lightningkite.kotlin.anko.measureDesiredHeight",
        "import lk.everything.deprecated.anko.measureDesiredHeight"
    ),
    (
        "import com.lightningkite.kotlin.anko.measureDesiredWidth",
        "import lk.everything.deprecated.anko.measureDesiredWidth"
    ),
    (
        "import com.lightningkite.kotlin.anko.progressButton",
        "import lk.everything.deprecated.anko.progressButton"
    ),
    (
        "import com.lightningkite.kotlin.anko.quickAdd",
        "import lk.everything.deprecated.anko.quickAdd"
    ),
    (
        "import com.lightningkite.kotlin.anko.remove",
        "import lk.everything.deprecated.anko.remove"
    ),
    (
        "import com.lightningkite.kotlin.anko.scrollStatus",
        "import lk.everything.deprecated.anko.scrollStatus"
    ),
    (
        "import com.lightningkite.kotlin.anko.setBackground",
        "import lk.everything.deprecated.anko.setBackground"
    ),
    (
        "import com.lightningkite.kotlin.anko.validOrSnackbar",
        "import lk.everything.deprecated.anko.validOrSnackbar"
    ),
    (
        "import com.lightningkite.kotlin.async.doAsync",
        "import lk.everything.deprecated.async.doAsync"
    ),
    (
        "import com.lightningkite.kotlin.async.doUiThread",
        "import lk.everything.deprecated.async.doUiThread"
    ),
    (
        "import com.lightningkite.kotlin.async.invokeAsync",
        "import lk.everything.deprecated.async.invokeAsync"
    ),
    (
        "import com.lightningkite.kotlin.async.invokeAsyncFuture",
        "import lk.everything.deprecated.async.invokeAsyncFuture"
    ),
    (
        "import com.lightningkite.kotlin.async.invokeUIThread",
        "import lk.everything.deprecated.async.invokeUIThread"
    ),
    (
        "import com.lightningkite.kotlin.async.parallel",
        "import lk.everything.deprecated.async.parallel"
    ),
    (
        "import com.lightningkite.kotlin.async.parallelAsyncs",
        "import lk.everything.deprecated.async.parallelAsyncs"
    ),
    (
        "import com.lightningkite.kotlin.async.parallelNonblocking",
        "import lk.everything.deprecated.async.parallelNonblocking"
    ),
    (
        "import com.lightningkite.kotlin.async.withEachAsync",
        "import lk.everything.deprecated.async.withEachAsync"
    ),
    (
        "import com.lightningkite.kotlin.async.withReduceAsync",
        "import lk.everything.deprecated.async.withReduceAsync"
    ),
    (
        "import com.lightningkite.kotlin.lambda.map",
        "import lk.everything.deprecated.lambda.map"
    ),
    (
        "import com.lightningkite.kotlin.lambda.transform",
        "import lk.everything.deprecated.lambda.transform"
    ),
    (
        "import com.lightningkite.kotlin.networking.ErrorCaptureApi",
        "import lk.everything.deprecated.networking.ErrorCaptureApi"
    ),
    (
        "import com.lightningkite.kotlin.networking.captureFailure",
        "import lk.everything.deprecated.networking.captureFailure"
    ),
    (
        "import com.lightningkite.kotlin.networking.captureSuccess",
        "import lk.everything.deprecated.networking.captureSuccess"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonFrom",
        "import lk.everything.deprecated.networking.gsonFrom"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToJson",
        "import lk.everything.deprecated.networking.gsonToJson"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToRequestBody",
        "import lk.everything.deprecated.networking.gsonToRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.networking.gsonToString",
        "import lk.everything.deprecated.networking.gsonToString"
    ),
    (
        "import com.lightningkite.kotlin.networking.jsonArray",
        "import lk.everything.deprecated.networking.jsonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.jsonObject",
        "import lk.everything.deprecated.networking.jsonObject"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaGson",
        "import lk.everything.deprecated.networking.lambdaGson"
    ),
    (
        "import com.lightningkite.kotlin.networking.mapResult",
        "import lk.everything.deprecated.networking.mapResult"
    ),
    (
        "import com.lightningkite.kotlin.networking.toJsonArray",
        "import lk.everything.deprecated.networking.toJsonArray"
    ),
    (
        "import com.lightningkite.kotlin.networking.toJsonElement",
        "import lk.everything.deprecated.networking.toJsonElement"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservableObservableProperty",
        "import lk.everything.deprecated.observable.property.ObservableObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservableObservablePropertyOpt",
        "import lk.everything.deprecated.observable.property.ObservableObservablePropertyOpt"
    ),
    (
        "import com.lightningkite.kotlin.observable.bindSub",
        "import lk.everything.deprecated.observable.bindSub"
    ),
    (
        "import com.lightningkite.kotlin.observable.mapObservable",
        "import lk.everything.deprecated.observable.mapObservable"
    ),
    (
        "import com.lightningkite.kotlin.observable.mapReadOnly",
        "import lk.everything.deprecated.observable.mapReadOnly"
    ),
    (
        "import com.lightningkite.kotlin.text.toDoubleMaybe",
        "import lk.everything.deprecated.text.toDoubleMaybe"
    ),
    (
        "import com.lightningkite.kotlin.text.toFloatMaybe",
        "import lk.everything.deprecated.text.toFloatMaybe"
    ),
    (
        "import com.lightningkite.kotlin.text.toIntMaybe",
        "import lk.everything.deprecated.text.toIntMaybe"
    ),
    (
        "import com.lightningkite.kotlin.text.toLongMaybe",
        "import lk.everything.deprecated.text.toLongMaybe"
    ),
    (
        "import com.lightningkite.kotlin.materialStyleAction",
        "import lk.everything.deprecated.materialStyleAction"
    ),
    (
        "import com.lightningkite.kotlin.materialStylePrimary",
        "import lk.everything.deprecated.materialStylePrimary"
    ),
    (
        "import com.lightningkite.kotlin.materialStyleSecondary",
        "import lk.everything.deprecated.materialStyleSecondary"
    ),
    (
        "import com.lightningkite.kotlin.materialStyleTertiary",
        "import lk.everything.deprecated.materialStyleTertiary"
    ),
    (
        "import com.lightningkite.kotlin.anko._Snackbar_Callback",
        "import lk.android.design.extensions._Snackbar_Callback"
    ),
    (
        "import com.lightningkite.kotlin.anko.callback",
        "import lk.android.design.extensions.callback"
    ),
    (
        "import com.lightningkite.kotlin.anko.errorResource",
        "import lk.android.design.extensions.errorResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.hintResource",
        "import lk.android.design.extensions.hintResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.onDismissed",
        "import lk.android.design.extensions.onDismissed"
    ),
    (
        "import com.lightningkite.kotlin.anko.setFont",
        "import lk.android.design.extensions.setFont"
    ),
    (
        "import com.lightningkite.kotlin.anko.setTabBackgroundResource",
        "import lk.android.design.extensions.setTabBackgroundResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.setTabTextSize",
        "import lk.android.design.extensions.setTabTextSize"
    ),
    (
        "import com.lightningkite.kotlin.anko.snackbar",
        "import lk.android.design.extensions.snackbar"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.bind",
        "import lk.kotlin.observable.property.lifecycle.bind"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.bindBlind",
        "import lk.kotlin.observable.property.lifecycle.bindBlind"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.listen",
        "import lk.kotlin.observable.property.lifecycle.listen"
    ),
    (
        "import org.jetbrains.anko.__AbsListView_OnScrollListener",
        "import org.jetbrains.anko.__AbsListView_OnScrollListener"
    ),
    (
        "import org.jetbrains.anko.__AdapterView_OnItemSelectedListener",
        "import org.jetbrains.anko.__AdapterView_OnItemSelectedListener"
    ),
    (
        "import org.jetbrains.anko.__GestureOverlayView_OnGestureListener",
        "import org.jetbrains.anko.__GestureOverlayView_OnGestureListener"
    ),
    (
        "import org.jetbrains.anko.__GestureOverlayView_OnGesturingListener",
        "import org.jetbrains.anko.__GestureOverlayView_OnGesturingListener"
    ),
    (
        "import org.jetbrains.anko.__SearchView_OnQueryTextListener",
        "import org.jetbrains.anko.__SearchView_OnQueryTextListener"
    ),
    (
        "import org.jetbrains.anko.__SearchView_OnSuggestionListener",
        "import org.jetbrains.anko.__SearchView_OnSuggestionListener"
    ),
    (
        "import org.jetbrains.anko.__SeekBar_OnSeekBarChangeListener",
        "import org.jetbrains.anko.__SeekBar_OnSeekBarChangeListener"
    ),
    (
        "import org.jetbrains.anko.__TextWatcher",
        "import org.jetbrains.anko.__TextWatcher"
    ),
    (
        "import org.jetbrains.anko.__ViewGroup_OnHierarchyChangeListener",
        "import org.jetbrains.anko.__ViewGroup_OnHierarchyChangeListener"
    ),
    (
        "import org.jetbrains.anko.__View_OnAttachStateChangeListener",
        "import org.jetbrains.anko.__View_OnAttachStateChangeListener"
    ),
    (
        "import org.jetbrains.anko.enabled",
        "import org.jetbrains.anko.enabled"
    ),
    (
        "import org.jetbrains.anko.onAttachStateChangeListener",
        "import org.jetbrains.anko.onAttachStateChangeListener"
    ),
    (
        "import org.jetbrains.anko.onCheckedChange",
        "import org.jetbrains.anko.onCheckedChange"
    ),
    (
        "import org.jetbrains.anko.onChildClick",
        "import org.jetbrains.anko.onChildClick"
    ),
    (
        "import org.jetbrains.anko.onChronometerTick",
        "import org.jetbrains.anko.onChronometerTick"
    ),
    (
        "import org.jetbrains.anko.onClick",
        "import org.jetbrains.anko.onClick"
    ),
    (
        "import org.jetbrains.anko.onClose",
        "import org.jetbrains.anko.onClose"
    ),
    (
        "import org.jetbrains.anko.onCompletion",
        "import org.jetbrains.anko.onCompletion"
    ),
    (
        "import org.jetbrains.anko.onCreateContextMenu",
        "import org.jetbrains.anko.onCreateContextMenu"
    ),
    (
        "import org.jetbrains.anko.onDateChange",
        "import org.jetbrains.anko.onDateChange"
    ),
    (
        "import org.jetbrains.anko.onDrag",
        "import org.jetbrains.anko.onDrag"
    ),
    (
        "import org.jetbrains.anko.onEditorAction",
        "import org.jetbrains.anko.onEditorAction"
    ),
    (
        "import org.jetbrains.anko.onError",
        "import org.jetbrains.anko.onError"
    ),
    (
        "import org.jetbrains.anko.onFocusChange",
        "import org.jetbrains.anko.onFocusChange"
    ),
    (
        "import org.jetbrains.anko.onGenericMotion",
        "import org.jetbrains.anko.onGenericMotion"
    ),
    (
        "import org.jetbrains.anko.onGestureListener",
        "import org.jetbrains.anko.onGestureListener"
    ),
    (
        "import org.jetbrains.anko.onGesturePerformed",
        "import org.jetbrains.anko.onGesturePerformed"
    ),
    (
        "import org.jetbrains.anko.onGesturingListener",
        "import org.jetbrains.anko.onGesturingListener"
    ),
    (
        "import org.jetbrains.anko.onGroupClick",
        "import org.jetbrains.anko.onGroupClick"
    ),
    (
        "import org.jetbrains.anko.onGroupCollapse",
        "import org.jetbrains.anko.onGroupCollapse"
    ),
    (
        "import org.jetbrains.anko.onGroupExpand",
        "import org.jetbrains.anko.onGroupExpand"
    ),
    (
        "import org.jetbrains.anko.onHierarchyChangeListener",
        "import org.jetbrains.anko.onHierarchyChangeListener"
    ),
    (
        "import org.jetbrains.anko.onHover",
        "import org.jetbrains.anko.onHover"
    ),
    (
        "import org.jetbrains.anko.onInflate",
        "import org.jetbrains.anko.onInflate"
    ),
    (
        "import org.jetbrains.anko.onItemClick",
        "import org.jetbrains.anko.onItemClick"
    ),
    (
        "import org.jetbrains.anko.onItemLongClick",
        "import org.jetbrains.anko.onItemLongClick"
    ),
    (
        "import org.jetbrains.anko.onItemSelectedListener",
        "import org.jetbrains.anko.onItemSelectedListener"
    ),
    (
        "import org.jetbrains.anko.onKey",
        "import org.jetbrains.anko.onKey"
    ),
    (
        "import org.jetbrains.anko.onLayoutChange",
        "import org.jetbrains.anko.onLayoutChange"
    ),
    (
        "import org.jetbrains.anko.onLongClick",
        "import org.jetbrains.anko.onLongClick"
    ),
    (
        "import org.jetbrains.anko.onPrepared",
        "import org.jetbrains.anko.onPrepared"
    ),
    (
        "import org.jetbrains.anko.onQueryTextFocusChange",
        "import org.jetbrains.anko.onQueryTextFocusChange"
    ),
    (
        "import org.jetbrains.anko.onQueryTextListener",
        "import org.jetbrains.anko.onQueryTextListener"
    ),
    (
        "import org.jetbrains.anko.onRatingBarChange",
        "import org.jetbrains.anko.onRatingBarChange"
    ),
    (
        "import org.jetbrains.anko.onScroll",
        "import org.jetbrains.anko.onScroll"
    ),
    (
        "import org.jetbrains.anko.onScrollListener",
        "import org.jetbrains.anko.onScrollListener"
    ),
    (
        "import org.jetbrains.anko.onSearchClick",
        "import org.jetbrains.anko.onSearchClick"
    ),
    (
        "import org.jetbrains.anko.onSeekBarChangeListener",
        "import org.jetbrains.anko.onSeekBarChangeListener"
    ),
    (
        "import org.jetbrains.anko.onSuggestionListener",
        "import org.jetbrains.anko.onSuggestionListener"
    ),
    (
        "import org.jetbrains.anko.onSystemUiVisibilityChange",
        "import org.jetbrains.anko.onSystemUiVisibilityChange"
    ),
    (
        "import org.jetbrains.anko.onTabChanged",
        "import org.jetbrains.anko.onTabChanged"
    ),
    (
        "import org.jetbrains.anko.onTimeChanged",
        "import org.jetbrains.anko.onTimeChanged"
    ),
    (
        "import org.jetbrains.anko.onTouch",
        "import org.jetbrains.anko.onTouch"
    ),
    (
        "import org.jetbrains.anko.onValueChanged",
        "import org.jetbrains.anko.onValueChanged"
    ),
    (
        "import org.jetbrains.anko.onZoomInClick",
        "import org.jetbrains.anko.onZoomInClick"
    ),
    (
        "import org.jetbrains.anko.onZoomOutClick",
        "import org.jetbrains.anko.onZoomOutClick"
    ),
    (
        "import org.jetbrains.anko.textChangedListener",
        "import org.jetbrains.anko.textChangedListener"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.fileSize",
        "import lk.android.extensions.files.fileSize"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.getDataColumn",
        "import lk.android.extensions.files.getDataColumn"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.getRealPath",
        "import lk.android.extensions.files.getRealPath"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.isDownloadsDocument",
        "import lk.android.extensions.files.isDownloadsDocument"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.isExternalStorageDocument",
        "import lk.android.extensions.files.isExternalStorageDocument"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.isMediaDocument",
        "import lk.android.extensions.files.isMediaDocument"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.toByteArray",
        "import lk.android.extensions.files.toByteArray"
    ),
    (
        "import com.lightningkite.kotlin.anko.files.toImageContentUri",
        "import lk.android.extensions.files.toImageContentUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.BitmapFactory_decodeByteArraySized",
        "import lk.android.extensions.image.BitmapFactory_decodeByteArraySized"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.ImageUtils",
        "import lk.android.extensions.image.ImageUtils"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.calculateInSampleSize",
        "import lk.android.extensions.image.calculateInSampleSize"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.calculateInSampleSizeMax",
        "import lk.android.extensions.image.calculateInSampleSizeMax"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.getBitmapFromUri",
        "import lk.android.extensions.image.getBitmapFromUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.image.rotate",
        "import lk.android.extensions.image.rotate"
    ),
    (
        "import com.lightningkite.kotlin.anko.EditTextChangeData",
        "import lk.android.extensions.EditTextChangeData"
    ),
    (
        "import com.lightningkite.kotlin.anko.FullInputType",
        "import lk.android.extensions.FullInputType"
    ),
    (
        "import com.lightningkite.kotlin.anko.HintSpinner",
        "import lk.android.extensions.HintSpinner"
    ),
    (
        "import com.lightningkite.kotlin.anko.StickyHeadersItemDecorator",
        "import lk.android.extensions.StickyHeadersItemDecorator"
    ),
    (
        "import com.lightningkite.kotlin.anko.alpha",
        "import lk.android.extensions.alpha"
    ),
    (
        "import com.lightningkite.kotlin.anko.bottomDrawable",
        "import lk.android.extensions.bottomDrawable"
    ),
    (
        "import com.lightningkite.kotlin.anko.colorAdd",
        "import lk.android.extensions.colorAdd"
    ),
    (
        "import com.lightningkite.kotlin.anko.colorMultiply",
        "import lk.android.extensions.colorMultiply"
    ),
    (
        "import com.lightningkite.kotlin.anko.datePicker",
        "import lk.android.extensions.datePicker"
    ),
    (
        "import com.lightningkite.kotlin.anko.elevationCompat",
        "import lk.android.extensions.elevationCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.fontCache",
        "import lk.android.extensions.fontCache"
    ),
    (
        "import com.lightningkite.kotlin.anko.getActivity",
        "import lk.android.extensions.getActivity"
    ),
    (
        "import com.lightningkite.kotlin.anko.getColorCompat",
        "import lk.android.extensions.getColorCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.getColorStateListCompat",
        "import lk.android.extensions.getColorStateListCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.getDrawableCompat",
        "import lk.android.extensions.getDrawableCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.getUniquePreferenceId",
        "import lk.android.extensions.getUniquePreferenceId"
    ),
    (
        "import com.lightningkite.kotlin.anko.hideSoftInput",
        "import lk.android.extensions.hideSoftInput"
    ),
    (
        "import com.lightningkite.kotlin.anko.hintTextColorResource",
        "import lk.android.extensions.hintTextColorResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.hintTextColorsResource",
        "import lk.android.extensions.hintTextColorsResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.horizontalDivider",
        "import lk.android.extensions.horizontalDivider"
    ),
    (
        "import com.lightningkite.kotlin.anko.html",
        "import lk.android.extensions.html"
    ),
    (
        "import com.lightningkite.kotlin.anko.isAttachedToWindowCompat",
        "import lk.android.extensions.isAttachedToWindowCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.isInLayoutCompat",
        "import lk.android.extensions.isInLayoutCompat"
    ),
    (
        "import com.lightningkite.kotlin.anko.leftDrawable",
        "import lk.android.extensions.leftDrawable"
    ),
    (
        "import com.lightningkite.kotlin.anko.measureDesiredSize",
        "import lk.android.extensions.measureDesiredSize"
    ),
    (
        "import com.lightningkite.kotlin.anko.onClickWithCooldown",
        "import lk.android.extensions.onClickWithCooldown"
    ),
    (
        "import com.lightningkite.kotlin.anko.onDone",
        "import lk.android.extensions.onDone"
    ),
    (
        "import com.lightningkite.kotlin.anko.onImeAction",
        "import lk.android.extensions.onImeAction"
    ),
    (
        "import com.lightningkite.kotlin.anko.onSend",
        "import lk.android.extensions.onSend"
    ),
    (
        "import com.lightningkite.kotlin.anko.once",
        "import lk.android.extensions.once"
    ),
    (
        "import com.lightningkite.kotlin.anko.requestLayoutSafe",
        "import lk.android.extensions.requestLayoutSafe"
    ),
    (
        "import com.lightningkite.kotlin.anko.requestSingleUpdate",
        "import lk.android.extensions.requestSingleUpdate"
    ),
    (
        "import com.lightningkite.kotlin.anko.resetCursorColor",
        "import lk.android.extensions.resetCursorColor"
    ),
    (
        "import com.lightningkite.kotlin.anko.rightDrawable",
        "import lk.android.extensions.rightDrawable"
    ),
    (
        "import com.lightningkite.kotlin.anko.selectableItemBackgroundBorderlessResource",
        "import lk.android.extensions.selectableItemBackgroundBorderlessResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.selectableItemBackgroundResource",
        "import lk.android.extensions.selectableItemBackgroundResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.selector",
        "import lk.android.extensions.selector"
    ),
    (
        "import com.lightningkite.kotlin.anko.setBoundsCentered",
        "import lk.android.extensions.setBoundsCentered"
    ),
    (
        "import com.lightningkite.kotlin.anko.setCursorColor",
        "import lk.android.extensions.setCursorColor"
    ),
    (
        "import com.lightningkite.kotlin.anko.setFont",
        "import lk.android.extensions.setFont"
    ),
    (
        "import com.lightningkite.kotlin.anko.showSoftInput",
        "import lk.android.extensions.showSoftInput"
    ),
    (
        "import com.lightningkite.kotlin.anko.textChanger",
        "import lk.android.extensions.textChanger"
    ),
    (
        "import com.lightningkite.kotlin.anko.textColorResource",
        "import lk.android.extensions.textColorResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.textColorsResource",
        "import lk.android.extensions.textColorsResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.textListener",
        "import lk.android.extensions.textListener"
    ),
    (
        "import com.lightningkite.kotlin.anko.timePicker",
        "import lk.android.extensions.timePicker"
    ),
    (
        "import com.lightningkite.kotlin.anko.topDrawable",
        "import lk.android.extensions.topDrawable"
    ),
    (
        "import com.lightningkite.kotlin.anko.verticalDivider",
        "import lk.android.extensions.verticalDivider"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.BaseObservableProperty",
        "import lk.kotlin.observable.property.BaseObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.CombineObservableProperty2",
        "import lk.kotlin.observable.property.CombineObservableProperty2"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.CombineObservableProperty3",
        "import lk.kotlin.observable.property.CombineObservableProperty3"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.CombineObservablePropertyBlind",
        "import lk.kotlin.observable.property.CombineObservablePropertyBlind"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ConstantObservableProperty",
        "import lk.kotlin.observable.property.ConstantObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.EnablingMutableCollection",
        "import lk.kotlin.observable.property.EnablingMutableCollection"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.LateInitObservableProperty",
        "import lk.kotlin.observable.property.LateInitObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.MutableObservableProperty",
        "import lk.kotlin.observable.property.MutableObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.MutableObservablePropertyMapped",
        "import lk.kotlin.observable.property.MutableObservablePropertyMapped"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.MutableObservablePropertySubReference",
        "import lk.kotlin.observable.property.MutableObservablePropertySubReference"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservableProperty",
        "import lk.kotlin.observable.property.ObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservablePropertyMapped",
        "import lk.kotlin.observable.property.ObservablePropertyMapped"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservablePropertyReference",
        "import lk.kotlin.observable.property.ObservablePropertyReference"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ObservablePropertySubObservable",
        "import lk.kotlin.observable.property.ObservablePropertySubObservable"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.ReducingObservableProperty",
        "import lk.kotlin.observable.property.ReducingObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.StackObservableProperty",
        "import lk.kotlin.observable.property.StackObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.StandardObservableProperty",
        "import lk.kotlin.observable.property.StandardObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.VirtualMutableObservableProperty",
        "import lk.kotlin.observable.property.VirtualMutableObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.VirtualObservableProperty",
        "import lk.kotlin.observable.property.VirtualObservableProperty"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.addAndInvoke",
        "import lk.kotlin.observable.property.addAndInvoke"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.reducing",
        "import lk.kotlin.observable.property.reducing"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.sub",
        "import lk.kotlin.observable.property.sub"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.subObs",
        "import lk.kotlin.observable.property.subObs"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.toObservablePropertyReference",
        "import lk.kotlin.observable.property.toObservablePropertyReference"
    ),
    (
        "import com.lightningkite.kotlin.observable.property.transform",
        "import lk.kotlin.observable.property.transform"
    ),
    (
        "import com.lightningkite.kotlin.anko.async.UIThread",
        "import lk.android.ui.thread.UIThread"
    ),
    (
        "import com.lightningkite.kotlin.anko.async.cancelling",
        "import lk.android.ui.thread.cancelling"
    ),
    (
        "import com.lightningkite.kotlin.anko.async.debounce",
        "import lk.android.ui.thread.debounce"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.FilterableStandardListAdapter",
        "import lk.anko.adapters.observable.FilterableStandardListAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.ListRecyclerViewAdapter",
        "import lk.anko.adapters.observable.ListRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.MultiTypeListRecyclerViewAdapter",
        "import lk.anko.adapters.observable.MultiTypeListRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.StandardListAdapter",
        "import lk.anko.adapters.observable.StandardListAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.StandardRecyclerViewAdapter",
        "import lk.anko.adapters.observable.StandardRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.attachAnimations",
        "import lk.anko.adapters.observable.attachAnimations"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.detatchAnimations",
        "import lk.anko.adapters.observable.detatchAnimations"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.listAdapter",
        "import lk.anko.adapters.observable.listAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.listAdapterObservable",
        "import lk.anko.adapters.observable.listAdapterObservable"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.multiTypeListAdapter",
        "import lk.anko.adapters.observable.multiTypeListAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.multiTypeListAdapterObservable",
        "import lk.anko.adapters.observable.multiTypeListAdapterObservable"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.previousListenerSet",
        "import lk.anko.adapters.observable.previousListenerSet"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.adapter.standardAdapter",
        "import lk.anko.adapters.observable.standardAdapter"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.bind",
        "import lk.kotlin.observable.list.lifecycle.bind"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.filtering",
        "import lk.kotlin.observable.list.lifecycle.filtering"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.flatMapping",
        "import lk.kotlin.observable.list.lifecycle.flatMapping"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.groupingBy",
        "import lk.kotlin.observable.list.lifecycle.groupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.multiGroupingBy",
        "import lk.kotlin.observable.list.lifecycle.multiGroupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.sorting",
        "import lk.kotlin.observable.list.lifecycle.sorting"
    ),
    (
        "import com.lightningkite.kotlin.anko.full.bindUri",
        "import lk.android.image.loading.observable.bindUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation._TransitionView",
        "import lk.anko.animations._TransitionView"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.swapView",
        "import lk.anko.animations.swapView"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.transitionView",
        "import lk.anko.animations.transitionView"
    ),
    (
        "import com.lightningkite.kotlin.bytes.IntBitArray",
        "import lk.kotlin.utils.bytes.IntBitArray"
    ),
    (
        "import com.lightningkite.kotlin.bytes.LongBitArray",
        "import lk.kotlin.utils.bytes.LongBitArray"
    ),
    (
        "import com.lightningkite.kotlin.bytes.hexToByteArray",
        "import lk.kotlin.utils.bytes.hexToByteArray"
    ),
    (
        "import com.lightningkite.kotlin.bytes.toBitArray",
        "import lk.kotlin.utils.bytes.toBitArray"
    ),
    (
        "import com.lightningkite.kotlin.bytes.toHexValue",
        "import lk.kotlin.utils.bytes.toHexValue"
    ),
    (
        "import com.lightningkite.kotlin.bytes.toStringHex",
        "import lk.kotlin.utils.bytes.toStringHex"
    ),
    (
        "import com.lightningkite.kotlin.collection.Cache",
        "import lk.kotlin.utils.collection.Cache"
    ),
    (
        "import com.lightningkite.kotlin.collection.CollectionWriteOnlyMapping",
        "import lk.kotlin.utils.collection.CollectionWriteOnlyMapping"
    ),
    (
        "import com.lightningkite.kotlin.collection.MappedList",
        "import lk.kotlin.utils.collection.MappedList"
    ),
    (
        "import com.lightningkite.kotlin.collection.MappedMutableList",
        "import lk.kotlin.utils.collection.MappedMutableList"
    ),
    (
        "import com.lightningkite.kotlin.collection.MappedMutableSet",
        "import lk.kotlin.utils.collection.MappedMutableSet"
    ),
    (
        "import com.lightningkite.kotlin.collection.ObserveEmptyArrayList",
        "import lk.kotlin.utils.collection.ObserveEmptyArrayList"
    ),
    (
        "import com.lightningkite.kotlin.collection.SetupList",
        "import lk.kotlin.utils.collection.SetupList"
    ),
    (
        "import com.lightningkite.kotlin.collection.addSorted",
        "import lk.kotlin.utils.collection.addSorted"
    ),
    (
        "import com.lightningkite.kotlin.collection.mapping",
        "import lk.kotlin.utils.collection.mapping"
    ),
    (
        "import com.lightningkite.kotlin.collection.mappingMutable",
        "import lk.kotlin.utils.collection.mappingMutable"
    ),
    (
        "import com.lightningkite.kotlin.collection.mappingWriteOnly",
        "import lk.kotlin.utils.collection.mappingWriteOnly"
    ),
    (
        "import com.lightningkite.kotlin.lambda.addInvokeOnce",
        "import lk.kotlin.utils.lambda.addInvokeOnce"
    ),
    (
        "import com.lightningkite.kotlin.lambda.invokeAll",
        "import lk.kotlin.utils.lambda.invokeAll"
    ),
    (
        "import com.lightningkite.kotlin.lambda.lambda",
        "import lk.kotlin.utils.lambda.lambda"
    ),
    (
        "import com.lightningkite.kotlin.lambda.then",
        "import lk.kotlin.utils.lambda.then"
    ),
    (
        "import com.lightningkite.kotlin.math.IEEEremainder",
        "import lk.kotlin.utils.math.IEEEremainder"
    ),
    (
        "import com.lightningkite.kotlin.math.abs",
        "import lk.kotlin.utils.math.abs"
    ),
    (
        "import com.lightningkite.kotlin.math.acos",
        "import lk.kotlin.utils.math.acos"
    ),
    (
        "import com.lightningkite.kotlin.math.asin",
        "import lk.kotlin.utils.math.asin"
    ),
    (
        "import com.lightningkite.kotlin.math.atan",
        "import lk.kotlin.utils.math.atan"
    ),
    (
        "import com.lightningkite.kotlin.math.cbrt",
        "import lk.kotlin.utils.math.cbrt"
    ),
    (
        "import com.lightningkite.kotlin.math.ceil",
        "import lk.kotlin.utils.math.ceil"
    ),
    (
        "import com.lightningkite.kotlin.math.copySign",
        "import lk.kotlin.utils.math.copySign"
    ),
    (
        "import com.lightningkite.kotlin.math.cos",
        "import lk.kotlin.utils.math.cos"
    ),
    (
        "import com.lightningkite.kotlin.math.cosh",
        "import lk.kotlin.utils.math.cosh"
    ),
    (
        "import com.lightningkite.kotlin.math.degreesTo",
        "import lk.kotlin.utils.math.degreesTo"
    ),
    (
        "import com.lightningkite.kotlin.math.exp",
        "import lk.kotlin.utils.math.exp"
    ),
    (
        "import com.lightningkite.kotlin.math.expm1",
        "import lk.kotlin.utils.math.expm1"
    ),
    (
        "import com.lightningkite.kotlin.math.exponent",
        "import lk.kotlin.utils.math.exponent"
    ),
    (
        "import com.lightningkite.kotlin.math.floor",
        "import lk.kotlin.utils.math.floor"
    ),
    (
        "import com.lightningkite.kotlin.math.log",
        "import lk.kotlin.utils.math.log"
    ),
    (
        "import com.lightningkite.kotlin.math.log10",
        "import lk.kotlin.utils.math.log10"
    ),
    (
        "import com.lightningkite.kotlin.math.log1p",
        "import lk.kotlin.utils.math.log1p"
    ),
    (
        "import com.lightningkite.kotlin.math.next",
        "import lk.kotlin.utils.math.next"
    ),
    (
        "import com.lightningkite.kotlin.math.nextUp",
        "import lk.kotlin.utils.math.nextUp"
    ),
    (
        "import com.lightningkite.kotlin.math.pow",
        "import lk.kotlin.utils.math.pow"
    ),
    (
        "import com.lightningkite.kotlin.math.radiansTo",
        "import lk.kotlin.utils.math.radiansTo"
    ),
    (
        "import com.lightningkite.kotlin.math.rint",
        "import lk.kotlin.utils.math.rint"
    ),
    (
        "import com.lightningkite.kotlin.math.round",
        "import lk.kotlin.utils.math.round"
    ),
    (
        "import com.lightningkite.kotlin.math.roundTo",
        "import lk.kotlin.utils.math.roundTo"
    ),
    (
        "import com.lightningkite.kotlin.math.scalb",
        "import lk.kotlin.utils.math.scalb"
    ),
    (
        "import com.lightningkite.kotlin.math.signum",
        "import lk.kotlin.utils.math.signum"
    ),
    (
        "import com.lightningkite.kotlin.math.sin",
        "import lk.kotlin.utils.math.sin"
    ),
    (
        "import com.lightningkite.kotlin.math.sinh",
        "import lk.kotlin.utils.math.sinh"
    ),
    (
        "import com.lightningkite.kotlin.math.sqrt",
        "import lk.kotlin.utils.math.sqrt"
    ),
    (
        "import com.lightningkite.kotlin.math.tan",
        "import lk.kotlin.utils.math.tan"
    ),
    (
        "import com.lightningkite.kotlin.math.tanh",
        "import lk.kotlin.utils.math.tanh"
    ),
    (
        "import com.lightningkite.kotlin.math.toDegrees",
        "import lk.kotlin.utils.math.toDegrees"
    ),
    (
        "import com.lightningkite.kotlin.math.toIntUnsigned",
        "import lk.kotlin.utils.math.toIntUnsigned"
    ),
    (
        "import com.lightningkite.kotlin.math.toRadians",
        "import lk.kotlin.utils.math.toRadians"
    ),
    (
        "import com.lightningkite.kotlin.math.ulp",
        "import lk.kotlin.utils.math.ulp"
    ),
    (
        "import com.lightningkite.kotlin.text.isEmail",
        "import lk.kotlin.utils.text.isEmail"
    ),
    (
        "import com.lightningkite.kotlin.cast",
        "import lk.kotlin.utils.cast"
    ),
    (
        "import com.lightningkite.kotlin.castOrNull",
        "import lk.kotlin.utils.castOrNull"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.image.BitmapHolder",
        "import lk.android.image.loading.image.BitmapHolder"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.image.ImageLoader",
        "import lk.android.image.loading.image.ImageLoader"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.image.lambdaBitmap",
        "import lk.android.image.loading.image.lambdaBitmap"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.image.lambdaBitmapExif",
        "import lk.android.image.loading.image.lambdaBitmapExif"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.imageAnyUri",
        "import lk.android.image.loading.imageAnyUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.networking.toRequestBody",
        "import lk.android.image.loading.toRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.anko.full.captureProgress",
        "import lk.kotlin.observable.property.jvm.captureProgress"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.CustomDialog",
        "import lk.android.dialogs.CustomDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.GenericDialogActivity",
        "import lk.android.dialogs.GenericDialogActivity"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.StandardDialog",
        "import lk.android.dialogs.StandardDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.alertDialog",
        "import lk.android.dialogs.alertDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.confirmationDialog",
        "import lk.android.dialogs.confirmationDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.dialog",
        "import lk.android.dialogs.dialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.infoDialog",
        "import lk.android.dialogs.infoDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.inputDialog",
        "import lk.android.dialogs.inputDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.dialog.standardDialog",
        "import lk.android.dialogs.standardDialog"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.jacksonToRequestBody",
        "import lk.kotlin.okhttp.jackson.jacksonToRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.lambdaJackson",
        "import lk.kotlin.okhttp.jackson.lambdaJackson"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.lambdaJacksonNode",
        "import lk.kotlin.okhttp.jackson.lambdaJacksonNode"
    ),
    (
        "import com.lightningkite.kotlin.networking.jackson.toRequestBody",
        "import lk.kotlin.okhttp.jackson.toRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.BasicViewPagerAdapter",
        "import lk.anko.adapters.BasicViewPagerAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.EmptyAdapter",
        "import lk.anko.adapters.EmptyAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.EmptyRecyclerViewAdapter",
        "import lk.anko.adapters.EmptyRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.MultiRecyclerViewAdapter",
        "import lk.anko.adapters.MultiRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.SingleRecyclerViewAdapter",
        "import lk.anko.adapters.SingleRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.SwipeActionListener",
        "import lk.anko.adapters.SwipeActionListener"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.SwipeDismissListener",
        "import lk.anko.adapters.SwipeDismissListener"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.TransitionRecyclerViewAdapter",
        "import lk.anko.adapters.TransitionRecyclerViewAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.basicAdapter",
        "import lk.anko.adapters.basicAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.multiAdapter",
        "import lk.anko.adapters.multiAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.singleAdapter",
        "import lk.anko.adapters.singleAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.swipe",
        "import lk.anko.adapters.swipe"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.swipeToDismiss",
        "import lk.anko.adapters.swipeToDismiss"
    ),
    (
        "import com.lightningkite.kotlin.anko.adapter.transitionAdapter",
        "import lk.anko.adapters.transitionAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.AnimationSet",
        "import lk.android.animations.AnimationSet"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.Interpolate",
        "import lk.android.animations.Interpolate"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.SwapView",
        "import lk.android.animations.SwapView"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.TransitionView",
        "import lk.android.animations.TransitionView"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.TypedValueAnimator",
        "import lk.android.animations.TypedValueAnimator"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.ViewSwapManager",
        "import lk.android.animations.ViewSwapManager"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.heightAnimator",
        "import lk.android.animations.heightAnimator"
    ),
    (
        "import com.lightningkite.kotlin.anko.animation.widthAnimator",
        "import lk.android.animations.widthAnimator"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.progressLayout",
        "import lk.anko.animations.observable.progressLayout"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.progressLayoutReadOnly",
        "import lk.anko.animations.observable.progressLayoutReadOnly"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.TypedVCStack",
        "import lk.anko.view.controllers.deprecated.containers.TypedVCStack"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCContainer",
        "import lk.anko.view.controllers.deprecated.containers.VCContainer"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCStack",
        "import lk.anko.view.controllers.deprecated.containers.VCStack"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCStackInterface",
        "import lk.anko.view.controllers.deprecated.containers.VCStackInterface"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCSwapper",
        "import lk.anko.view.controllers.deprecated.containers.VCSwapper"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCTabs",
        "import lk.anko.view.controllers.deprecated.containers.VCTabs"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.containers.swap",
        "import lk.anko.view.controllers.deprecated.containers.swap"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.CustomDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.CustomDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.StandardDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.StandardDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.alertDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.alertDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.confirmationDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.confirmationDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.customDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.customDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.infoDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.infoDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.inputDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.inputDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.dialogs.standardDialog",
        "import lk.anko.view.controllers.deprecated.dialogs.standardDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.dialogImage",
        "import lk.anko.view.controllers.deprecated.image.dialogImage"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.dialogImageUri",
        "import lk.anko.view.controllers.deprecated.image.dialogImageUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.dialogPublicImageUri",
        "import lk.anko.view.controllers.deprecated.image.dialogPublicImageUri"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.getImageFromCamera",
        "import lk.anko.view.controllers.deprecated.image.getImageFromCamera"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.getImageFromGallery",
        "import lk.anko.view.controllers.deprecated.image.getImageFromGallery"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.getImageUriFromCamera",
        "import lk.anko.view.controllers.deprecated.image.getImageUriFromCamera"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.getImageUriFromGallery",
        "import lk.anko.view.controllers.deprecated.image.getImageUriFromGallery"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.image.getPublicImageUriFromCamera",
        "import lk.anko.view.controllers.deprecated.image.getPublicImageUriFromCamera"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.ContainerVC",
        "import lk.anko.view.controllers.deprecated.implementations.ContainerVC"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.VCActivity",
        "import lk.anko.view.controllers.deprecated.implementations.VCActivity"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.VCContainerEmbedder",
        "import lk.anko.view.controllers.deprecated.implementations.VCContainerEmbedder"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.VCDialogActivity",
        "import lk.anko.view.controllers.deprecated.implementations.VCDialogActivity"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.dialog",
        "import lk.anko.view.controllers.deprecated.implementations.dialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.implementations.viewControllerDialog",
        "import lk.anko.view.controllers.deprecated.implementations.viewControllerDialog"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.net.HTMLViewController",
        "import lk.anko.view.controllers.deprecated.net.HTMLViewController"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.AnkoViewController",
        "import lk.anko.view.controllers.deprecated.AnkoViewController"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.CallbackViewController",
        "import lk.anko.view.controllers.deprecated.CallbackViewController"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.VCContext",
        "import lk.anko.view.controllers.deprecated.VCContext"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.ViewController",
        "import lk.anko.view.controllers.deprecated.ViewController"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.setUpWithVCTabs",
        "import lk.anko.view.controllers.deprecated.setUpWithVCTabs"
    ),
    (
        "import com.lightningkite.kotlin.anko.viewcontrollers.startIntent",
        "import lk.anko.view.controllers.deprecated.startIntent"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.OnItemSelectedAdapter",
        "import lk.android.observable.OnItemSelectedAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.SeekBarChangeAdapter",
        "import lk.android.observable.SeekBarChangeAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.TextWatcherAdapter",
        "import lk.android.observable.TextWatcherAdapter"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindAny",
        "import lk.android.observable.bindAny"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindArray",
        "import lk.android.observable.bindArray"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindBoolean",
        "import lk.android.observable.bindBoolean"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindDouble",
        "import lk.android.observable.bindDouble"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindFloat",
        "import lk.android.observable.bindFloat"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindIndex",
        "import lk.android.observable.bindIndex"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindInt",
        "import lk.android.observable.bindInt"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindList",
        "import lk.android.observable.bindList"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindListOpt",
        "import lk.android.observable.bindListOpt"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindNullableDouble",
        "import lk.android.observable.bindNullableDouble"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindNullableFloat",
        "import lk.android.observable.bindNullableFloat"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindNullableInt",
        "import lk.android.observable.bindNullableInt"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindNullableString",
        "import lk.android.observable.bindNullableString"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindString",
        "import lk.android.observable.bindString"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.bindValue",
        "import lk.android.observable.bindValue"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.hiddenTouchFunctionality",
        "import lk.android.observable.hiddenTouchFunctionality"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.onRefresh",
        "import lk.android.observable.onRefresh"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.onRefreshAndNow",
        "import lk.android.observable.onRefreshAndNow"
    ),
    (
        "import com.lightningkite.kotlin.async.Async",
        "import lk.kotlin.jvm.utils.async.Async"
    ),
    (
        "import com.lightningkite.kotlin.async.execute",
        "import lk.kotlin.jvm.utils.async.execute"
    ),
    (
        "import com.lightningkite.kotlin.async.invokeFuture",
        "import lk.kotlin.jvm.utils.async.invokeFuture"
    ),
    (
        "import com.lightningkite.kotlin.async.invokeOn",
        "import lk.kotlin.jvm.utils.async.invokeOn"
    ),
    (
        "import com.lightningkite.kotlin.async.submit",
        "import lk.kotlin.jvm.utils.async.submit"
    ),
    (
        "import com.lightningkite.kotlin.async.thenOn",
        "import lk.kotlin.jvm.utils.async.thenOn"
    ),
    (
        "import com.lightningkite.kotlin.collection.mappingMutableHash",
        "import lk.kotlin.jvm.utils.collection.mappingMutableHash"
    ),
    (
        "import com.lightningkite.kotlin.collection.random",
        "import lk.kotlin.jvm.utils.collection.random"
    ),
    (
        "import com.lightningkite.kotlin.date.dayOfMonth",
        "import lk.kotlin.jvm.utils.date.dayOfMonth"
    ),
    (
        "import com.lightningkite.kotlin.date.dayOfWeek",
        "import lk.kotlin.jvm.utils.date.dayOfWeek"
    ),
    (
        "import com.lightningkite.kotlin.date.hourOfDay",
        "import lk.kotlin.jvm.utils.date.hourOfDay"
    ),
    (
        "import com.lightningkite.kotlin.date.millisecond",
        "import lk.kotlin.jvm.utils.date.millisecond"
    ),
    (
        "import com.lightningkite.kotlin.date.minute",
        "import lk.kotlin.jvm.utils.date.minute"
    ),
    (
        "import com.lightningkite.kotlin.date.month",
        "import lk.kotlin.jvm.utils.date.month"
    ),
    (
        "import com.lightningkite.kotlin.date.second",
        "import lk.kotlin.jvm.utils.date.second"
    ),
    (
        "import com.lightningkite.kotlin.date.toCalendar",
        "import lk.kotlin.jvm.utils.date.toCalendar"
    ),
    (
        "import com.lightningkite.kotlin.date.weekInMonth",
        "import lk.kotlin.jvm.utils.date.weekInMonth"
    ),
    (
        "import com.lightningkite.kotlin.date.year",
        "import lk.kotlin.jvm.utils.date.year"
    ),
    (
        "import com.lightningkite.kotlin.files.child",
        "import lk.kotlin.jvm.utils.files.child"
    ),
    (
        "import com.lightningkite.kotlin.lambda.CooldownLambda",
        "import lk.kotlin.jvm.utils.lambda.CooldownLambda"
    ),
    (
        "import com.lightningkite.kotlin.lambda.cooldown",
        "import lk.kotlin.jvm.utils.lambda.cooldown"
    ),
    (
        "import com.lightningkite.kotlin.range.random",
        "import lk.kotlin.jvm.utils.range.random"
    ),
    (
        "import com.lightningkite.kotlin.stream.toByteArray",
        "import lk.kotlin.jvm.utils.stream.toByteArray"
    ),
    (
        "import com.lightningkite.kotlin.stream.toString",
        "import lk.kotlin.jvm.utils.stream.toString"
    ),
    (
        "import com.lightningkite.kotlin.stream.writeStream",
        "import lk.kotlin.jvm.utils.stream.writeStream"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.LifecycleConnectable",
        "import lk.kotlin.lifecycle.LifecycleConnectable"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.LifecycleListener",
        "import lk.kotlin.lifecycle.LifecycleListener"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.LifecycleListenerCollection",
        "import lk.kotlin.lifecycle.LifecycleListenerCollection"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.bind",
        "import lk.kotlin.lifecycle.bind"
    ),
    (
        "import com.lightningkite.kotlin.lifecycle.listen",
        "import lk.kotlin.lifecycle.listen"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.StringFetcher",
        "import lk.android.observable.validation.StringFetcher"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.StringFetcherDirect",
        "import lk.android.observable.validation.StringFetcherDirect"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.StringFetcherResource",
        "import lk.android.observable.validation.StringFetcherResource"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.Validation",
        "import lk.android.observable.validation.Validation"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.bindError",
        "import lk.android.observable.validation.bindError"
    ),
    (
        "import com.lightningkite.kotlin.anko.observable.validation.validation",
        "import lk.android.observable.validation.validation"
    ),
    (
        "import com.lightningkite.kotlin.anko.expanding",
        "import lk.android.animations.observable.expanding"
    ),
    (
        "import com.lightningkite.kotlin.networking.MediaTypes",
        "import lk.kotlin.okhttp.MediaTypes"
    ),
    (
        "import com.lightningkite.kotlin.networking.OkHttpApi",
        "import lk.kotlin.okhttp.OkHttpApi"
    ),
    (
        "import com.lightningkite.kotlin.networking.TypedResponse",
        "import lk.kotlin.okhttp.TypedResponse"
    ),
    (
        "import com.lightningkite.kotlin.networking.chain",
        "import lk.kotlin.okhttp.chain"
    ),
    (
        "import com.lightningkite.kotlin.networking.chainTypeless",
        "import lk.kotlin.okhttp.chainTypeless"
    ),
    (
        "import com.lightningkite.kotlin.networking.defaultClient",
        "import lk.kotlin.okhttp.defaultClient"
    ),
    (
        "import com.lightningkite.kotlin.networking.getDebugInfoString",
        "import lk.kotlin.okhttp.getDebugInfoString"
    ),
    (
        "import com.lightningkite.kotlin.networking.getKotlinHeaders",
        "import lk.kotlin.okhttp.getKotlinHeaders"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambda",
        "import lk.kotlin.okhttp.lambda"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaBytes",
        "import lk.kotlin.okhttp.lambdaBytes"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaCustom",
        "import lk.kotlin.okhttp.lambdaCustom"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaDownload",
        "import lk.kotlin.okhttp.lambdaDownload"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaStream",
        "import lk.kotlin.okhttp.lambdaStream"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaString",
        "import lk.kotlin.okhttp.lambdaString"
    ),
    (
        "import com.lightningkite.kotlin.networking.lambdaUnit",
        "import lk.kotlin.okhttp.lambdaUnit"
    ),
    (
        "import com.lightningkite.kotlin.networking.thenOnFailure",
        "import lk.kotlin.okhttp.thenOnFailure"
    ),
    (
        "import com.lightningkite.kotlin.networking.thenOnSuccess",
        "import lk.kotlin.okhttp.thenOnSuccess"
    ),
    (
        "import com.lightningkite.kotlin.networking.toRequestBody",
        "import lk.kotlin.okhttp.toRequestBody"
    ),
    (
        "import com.lightningkite.kotlin.networking.transformResult",
        "import lk.kotlin.okhttp.transformResult"
    ),
    (
        "import com.lightningkite.kotlin.anko.anko",
        "import lk.anko.extensions.anko"
    ),
    (
        "import com.lightningkite.kotlin.anko.horizontalGridRecyclerView",
        "import lk.anko.extensions.horizontalGridRecyclerView"
    ),
    (
        "import com.lightningkite.kotlin.anko.horizontalRecyclerView",
        "import lk.anko.extensions.horizontalRecyclerView"
    ),
    (
        "import com.lightningkite.kotlin.anko.stickyHeaders",
        "import lk.anko.extensions.stickyHeaders"
    ),
    (
        "import com.lightningkite.kotlin.anko.textInputEditText",
        "import lk.anko.extensions.textInputEditText"
    ),
    (
        "import com.lightningkite.kotlin.anko.themedTextInputEditText",
        "import lk.anko.extensions.themedTextInputEditText"
    ),
    (
        "import com.lightningkite.kotlin.anko.verticalGridRecyclerView",
        "import lk.anko.extensions.verticalGridRecyclerView"
    ),
    (
        "import com.lightningkite.kotlin.anko.verticalRecyclerView",
        "import lk.anko.extensions.verticalRecyclerView"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.AccessibleActivity",
        "import lk.android.activity.access.AccessibleActivity"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.ActivityAccess",
        "import lk.android.activity.access.ActivityAccess"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.ViewGenerator",
        "import lk.android.mighty.view.ViewGenerator"
    ),
    (
        "import com.lightningkite.kotlin.anko.activity.startIntent",
        "import lk.android.activity.access.startIntent"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableList",
        "import lk.kotlin.observable.list.ObservableList"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListFiltered",
        "import lk.kotlin.observable.list.ObservableListFiltered"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListFlatMapping",
        "import lk.kotlin.observable.list.ObservableListFlatMapping"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListGroupingBy",
        "import lk.kotlin.observable.list.ObservableListGroupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListIndicies",
        "import lk.kotlin.observable.list.ObservableListIndicies"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListListenerSet",
        "import lk.kotlin.observable.list.ObservableListListenerSet"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListMapped",
        "import lk.kotlin.observable.list.ObservableListMapped"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListMultiGroupingBy",
        "import lk.kotlin.observable.list.ObservableListMultiGroupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListSorted",
        "import lk.kotlin.observable.list.ObservableListSorted"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.ObservableListWrapper",
        "import lk.kotlin.observable.list.ObservableListWrapper"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.addListenerSet",
        "import lk.kotlin.observable.list.addListenerSet"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.filtering",
        "import lk.kotlin.observable.list.filtering"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.flatMapping",
        "import lk.kotlin.observable.list.flatMapping"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.groupingBy",
        "import lk.kotlin.observable.list.groupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.mapping",
        "import lk.kotlin.observable.list.mapping"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.multiGroupingBy",
        "import lk.kotlin.observable.list.multiGroupingBy"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.observableListOf",
        "import lk.kotlin.observable.list.observableListOf"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.removeListenerSet",
        "import lk.kotlin.observable.list.removeListenerSet"
    ),
    (
        "import com.lightningkite.kotlin.observable.list.sorting",
        "import lk.kotlin.observable.list.sorting"
    )
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
