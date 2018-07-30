# Module observable

[Return to Index](../)

## Package description

Adds a ton of convenience function for binding the values of observable properties to views.

Some examples:

- `EditText.bindString` allows you to keep the value of the observable and the value in the EditText always the same.
- `CompoundButton.bindBoolean` allows you to keep the checked/unchecked state of the component matching the value of the observable property.
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [ui-thread](../ui-thread)
 - [lifecycle](../lifecycle)
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
    implementation "com.lightningkite.kotlin:observable-property:[version]"
}
```

## Resources