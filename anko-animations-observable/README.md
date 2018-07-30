# Module anko-animations-observable

[Return to Index](../)

## Package description

Contains Anko Layout functions that also use [observables](https://github.com/lightningkite/lk-kotlin/tree/master/observable-property) for [animations](../animations/index.html).

Right now, that's only a single but super-handy function, `progressLayout`, which allows you to wrap a view with a progress indicator that turns on and off depending on how you set the oservable.
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [Anko](https://github.com/Kotlin/anko/wiki/Anko-Layouts).
 - [Animations](../animations/index.html)
 - [Observable properties](https://github.com/lightningkite/lk-kotlin/tree/master/observable-property)


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
    implementation "com.lightningkite.kotlin.android:anko-animations-observable:[version]"
    implementation "com.lightningkite.kotlin.android:anko-animations:[version]"
    implementation "com.lightningkite.kotlin:observable-property:[version]"
    implementation "com.lightningkite.kotlin:observable-list:[version]"
}
```

## Resources