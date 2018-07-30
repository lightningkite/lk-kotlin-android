# Module lifecycle

[Return to Index](../)

## Package description

Adds an observable property to every view called `lifecycle` that is `true` when the view is attached and `false` otherwise.

This turns out to be super useful in ensuring there are no event listener leaks, adding a listener when the view is attached and removing it when it is detached.

There are some convenient functions for using this functionality:

```kotlin

val event = HashSet<(Input)->Unit>()

...


view {
    lifecycle.listen(event){ it:Input ->
        //Do something with the view.
    }
}

```

Also, when using with observable properties, you can use `bind` to call the listener immediately as well.

```kotlin

val currentNum = StandardObservableProperty(0)

...


textView {
    lifecycle.bind(currentNum){ it:Int ->
        text = it.toString()
    }
    setOnClickListener {
        currentNum.value++
    }
}

```
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [ui-thread](../ui-thread)
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
    implementation "com.lightningkite.kotlin:observable-property:[version]"
}
```

## Resources