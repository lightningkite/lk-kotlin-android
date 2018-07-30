# Module extensions

[Return to Index](../)

## Package description

A large collection of convenience functions for Android apps.

Things like:

- Convenient launchers for date and time dialogs
- Compat extension functions (`resources.getColorCompat(...)`)
- Easier-to-use input types (`FullInputTypes.NEGATIVE_FLOAT`)
- Color manipulation functions (`myIntColor.colorMultiply(.5f)`)
    
## Dependencies

This package depends upon:
 - AppCompatV7 


## Gradle Inclusion

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
    implementation "com.lightningkite.kotlin.android:extensions:[version]"
}
```

## Resources