# Module activity-access

[Return to Index](../)

## Package description

Defines and implements ActivityAccess, an interface for accessing activities and their callbacks.

The nifty thing about this is that you can start intents with a lambda callback, rather than looping it back through the activity.  It ends up organizing and modularizing code significantly.

    
## Dependencies

This package only depends upon AppCompatV7.


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
    implementation "com.lightningkite.kotlin.android:activity-access:[version]"
}
```

## Resources