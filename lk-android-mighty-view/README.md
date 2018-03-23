# Module lk-android-mighty-view

[Return to Index](../)

## Package description

My favorite package, personally.  

While it, of itself, contains only four extension functions, it, with its dependencies, represents an entirely different, clean way of making Android apps.  

Instead of building the app to move from activity to activity, you can make it move from ViewGenerator to ViewGenerator.

This has the following advantages:

- Passing information through constructors
    - Since ViewGenerator is merely a type alias for a lambda that takes an ActivityAccess and outputs a view, you can make anything be a ViewGenerator.  This frees up the constructor for use.
- Nesting
    - It is incredibly simple to nest one ViewGenerator inside of another.  Simply call the other one from the inside of the other and add the resulting view as a child.
- Testing
    - You can create a ViewGenerator and test its parts in the JVM directly with the exception of `invoke`.
- Performance
    - Moving between ViewGenerators is visibly faster to your users.  Try out the demo for yourself and you'll see!
    - ViewGenerators also don't leave their views around while offscreen.  This also saves resources.
- Screen Rotation
    - By using the observable system included, you can bind the views to the data within the ViewGenerator, thus making rebuilding the view free.
- Simplicity
    - Using ViewGenerators is far more simple and readable than activities, and combines particularly well with view DSLs like Anko.
    
It does, however, have some disadvantages that you should be aware of:

- Exception handling must be done manually
    - In Activities, a exception is automatically handled within the activity itself much of the time, such that only one activity crashes rather than the entire app.  This system has no such built-in fallback; you must handle such things yourself.
- You must be wary of contexts
    - Android Studio does a pretty good job at catching context reference leaks while using Activities.  It cannot help you, however, when you are using this library.  A good rule of thumb is to *never hold a reference to a context or view in a view generator.*  There is no need to do so, however, if you are using observables correctly.
    
    
## Dependencies    

Note that this module has a number of small dependencies and one larger dependency.  The overall dex contribution besides AppCompat, however, is pretty small around 3300 methods.

Also, the dependencies are all useful in their own right, and you will likely be using them yourself.

The large dependency is AppCompatV7, which almost all apps have anyways.  If you create a new Android project in Android Studio, it's included by default anyways.

It has dependencies on several other packages in this library, including:

- [lk-android-animations](../lk-android-animations/README.md)
- [lk-android-lifecycle](../lk-android-lifecycle/README.md)
- [lk-android-animations-observable](../lk-android-animations-observable/README.md)
- [lk-android-dialogs](../lk-android-dialogs/README.md)
- [lk-android-activity-access](../lk-android-activity-access/README.md)

It also has the following external dependencies from [lk-kotlin](https://github.com/lightningkite/lk-kotlin):

- [com.lightningkite.kotlin:lk-kotlin-utils](https://github.com/lightningkite/lk-kotlin/lk-kotlin-utils)
- [com.lightningkite.kotlin:lk-kotlin-lifecycle](https://github.com/lightningkite/lk-kotlin/lk-kotlin-lifecycle)
- [com.lightningkite.kotlin:lk-kotlin-observable-property](https://github.com/lightningkite/lk-kotlin/lk-kotlin-observable-property)
- [com.lightningkite.kotlin:lk-kotlin-observable-property-lifecycle](https://github.com/lightningkite/lk-kotlin/lk-kotlin-observable-property-lifecycle)
- [com.lightningkite.kotlin:lk-kotlin-observable-list](https://github.com/lightningkite/lk-kotlin/lk-kotlin-observable-list)
- [com.lightningkite.kotlin:lk-kotlin-observable-list-lifecycle](https://github.com/lightningkite/lk-kotlin/lk-kotlin-observable-list-lifecycle)



## Gradle Inclusion

Add the repository:

```
repositories {
    maven {
        url "https://dl.bintray.com/lightningkite/com.lightningkite.kotlin"
        url "https://dl.bintray.com/lightningkite/com.lightningkite.kotlin.android"
    }
}
```

Include the desired libraries:

```
dependencies {
    implementation "com.lightningkite.kotlin.android:lk-android-mighty-view:[version]"
    
    implementation "com.lightningkite.kotlin.android:lk-android-animations:[version]"
    implementation "com.lightningkite.kotlin.android:lk-android-animations-observable:[version]"
    implementation "com.lightningkite.kotlin.android:lk-android-dialogs:[version]"
    implementation "com.lightningkite.kotlin.android:lk-android-activity-access:[version]"
    
    implementation "com.lightningkite.kotlin:lk-kotlin-observable-list:[version]"
    implementation "com.lightningkite.kotlin:lk-kotlin-lifecycle:[version]"
    implementation "com.lightningkite.kotlin:lk-kotlin-utils:[version]"
    implementation "com.lightningkite.kotlin:lk-kotlin-observable-property:[version]"
    implementation "com.lightningkite.kotlin:lk-kotlin-observable-list-lifecycle:[version]"
    implementation "com.lightningkite.kotlin:lk-kotlin-observable-property-lifecycle:[version]"
}
```

## Resources