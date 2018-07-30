# Module animations-observable

[Return to Index](../)

## Package description

Adds a couple of functions for binding the animation views found in [animations](../animations/index.html) to [observables](https://github.com/lightningkite/lk-kotlin/observable-property).

    
## Dependencies

This package depends upon:
 - AppCompatV7
 - [animations](../animations/index.html)
 - [lifecycle](../lifecycle/index.html)
 - [observables](https://github.com/lightningkite/lk-kotlin/tree/master/observable-property)


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
    implementation "com.lightningkite.kotlin:observable-property:[version]"
    implementation "com.lightningkite.kotlin.android:animations:[version]"
    implementation "com.lightningkite.kotlin.android:animations-observable:[version]"
}
```

## Resources