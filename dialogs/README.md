# Module dialogs

[Return to Index](../)

## Package description

Functionality for creating dialogs very quickly but in a very stable manner (i.e. they're activities so they don't disappear on rotation).

This does depend on the [activity-access](../activity-access) package to launch and use the dialogs.

Here are some quick examples of the functionality:

```kotlin

val access: ActivityAccess

access.infoDialog(
    title = "Hello!",
    message = "This is some important information."
)

access.confirmationDialog(
    title = "Confirmation",
    message = "Are you sure you want to do this?"
){
    //do the thing
}

```
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [activity-access](../activity-access)


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
    implementation "com.lightningkite.kotlin.android:dialogs:[version]"
    implementation "com.lightningkite.kotlin.android:activity-access:[version]"
}
```

## Resources