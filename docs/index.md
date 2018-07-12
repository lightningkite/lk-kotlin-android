# LK Kotlin

[ ![Download](https://api.bintray.com/packages/lightningkite/com.lightningkite.kotlin.android/extensions/images/download.svg) ](https://bintray.com/lightningkite/com.lightningkite.kotlin.android/extensions/_latestVersion)

Lightning Kite's project to make Android even more awesome for speedy development

## What is this?

This project contains a number of small, minimalist libraries for improving and speeding up Android development.

All of them use a combination of Android and Kotlin, and possibly other languages.

Each piece of the library is built to be read - in other words, you should be able to look at the source of anything in it and understand it pretty much immediately.  If not, please make a GitHub issue for it so that it can be better written or at least better documented.

This includes the following packages:

- [mighty-view](mighty-view/README.md) - My favorite package, personally.  While it, of itself, contains only four extension functions, it represents an entirely new, clean way of making Android apps.  It has dependencies on several other packages in this library.
- [activity-access](activity-access/README.md) - Defines and implements an interface for accessing the activity lifecycle more easily.
- [animations](animations/README.md) - Various animation tools
- [animations-observable](animations-observable/README.md) - Various animation tools that use [observables](https://github.com/lightningkite/lk-kotlin)
- [design-extensions](design-extensions/README.md) - Various extensions for using the 'design' elements from the compat libraries.
- [dialogs](dialogs/README.md) - Uses `activity-access` to show very sturdy dialogs.
- [extensions](extensions/README.md) - Various Kotlin extensions to make Android more usable.
- [image-loading](image-loading/README.md) - Various stuff for loading images.
- [image-loading-observable](image-loading-observable/README.md) - Various stuff for loading images using [observables](https://github.com/lightningkite/lk-kotlin).
- [lifecycle](lifecycle/README.md) - Exposes the view's lifecycle using [observables](https://github.com/lightningkite/lk-kotlin).
- [observable](observable/README.md) - Various Android things that can be done with [observables](https://github.com/lightningkite/lk-kotlin).
- [observable-validation](observable-validation/README.md) - A system for validating forms using [observables](https://github.com/lightningkite/lk-kotlin).
- [ui-thread](ui-thread/README.md) - Exposes a [Executor] that represents the UI thread.
- [anko-adapters](anko-adapters/README.md) - Tools for building adapters in Anko.
- [anko-adapters-observable](anko-adapters-observable/README.md) - Tools for building adapters in Anko combined with [observables](https://github.com/lightningkite/lk-kotlin).
- [anko-animations](anko-animations/README.md) - More animation tools, specifically for Anko.
- [anko-animations-observable](anko-animations-observable/README.md) - Same as above with additional things for [observables](https://github.com/lightningkite/lk-kotlin).
- [anko-extensions](anko-extensions/README.md) - Various extensions to Anko.


## Gradle Inclusion

Bintray: [ ![Download](https://api.bintray.com/packages/lightningkite/com.lightningkite.kotlin.android/extensions/images/download.svg) ](https://bintray.com/lightningkite/com.lightningkite.kotlin.android/extensions/_latestVersion)

Add the repository:

```
repositories {
    maven {
        url "https://dl.bintray.com/lightningkite/com.lightningkite.kotlin.android"
    }
}
```

Include the desired libraries:

```
dependencies {
    implementation "com.lightningkite.kotlin.android:activity-access:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:animations:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:animations-observable:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:design-extensions:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:dialogs:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:extensions:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:image-loading:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:image-loading-observable:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:lifecycle:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:observable:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:observable-validation:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:ui-thread:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:mighty-view:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:anko-adapters-observable:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:anko-adapters:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:anko-animations-observable:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:anko-animations:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin.android:anko-extensions:${rootProject.libraryKotlinVersion}"
    
    implementation "com.lightningkite.kotlin:utils:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin:jackson:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin:okhttp:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin:okhttp-jackson:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin:observable-property:${rootProject.libraryKotlinVersion}"
    implementation "com.lightningkite.kotlin:observable-list:${rootProject.libraryKotlinVersion}"
}
```

# TODO Notes

- Get better code coverage on the observable lists
