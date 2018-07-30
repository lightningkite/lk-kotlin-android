# Module image-loading-observable

[Return to Index](../)

## Package description

Some functions and tools for loading images.
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - OkHttp
 - [extensions](../extensions)
 - [ui-thread](../ui-thread)
 - [image-loading](../image-loading)
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
    implementation "com.lightningkite.kotlin.android:extensions:[version]"
    implementation "com.lightningkite.kotlin.android:image-loading:[version]"
    implementation "com.lightningkite.kotlin:observable-property:[version]"
}
```

## Resources