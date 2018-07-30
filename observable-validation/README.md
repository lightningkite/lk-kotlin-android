# Module observable-validation

[Return to Index](../)

## Package description

Adds a system for validating forms using observables.

TODO: Add more documentation
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [ui-thread](../ui-thread)
 - [lifecycle](../lifecycle)
 - [observable](../observable)
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
    implementation "com.lightningkite.kotlin.android:ui-thread:[version]"
    implementation "com.lightningkite.kotlin.android:lifecycle:[version]"
    implementation "com.lightningkite.kotlin.android:observable:[version]"
    implementation "com.lightningkite.kotlin.android:observable-validation:[version]"
    implementation "com.lightningkite.kotlin:observable-property:[version]"
}
```

## Resources