# Module ui-thread

[Return to Index](../)

## Package description

Literally just adds an object called `UIThread` that is a Java `Executor` for running things on the UI Thread.
    
## Dependencies

None!

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
    implementation "com.lightningkite.kotlin.android:ui-thread:[version]"
}
```

## Resources