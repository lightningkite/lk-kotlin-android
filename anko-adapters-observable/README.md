# Module anko-adapters-observable

[Return to Index](../)

## Package description

Contains some useful implementations of RecyclerView adapters to be used with Anko and observables.

The most important of this is `ListRecyclerViewAdapter`, which can be used to show a list of items in a very convenient syntax like this:

```kotlin

val myObservableList = ObservableListWrapper<Int>()

myObservableList.add(1)
myObservableList.add(2)
myObservableList.add(3)

verticalRecyclerView {

    //Sets a list adapter with animation bindings.
    adapter = listAdapter(myObservableList) { itemObs ->
        //itemObs is an observable property with whatever item the view is supposed to show.
        
        //Here we insert the code to create a view that displays it
        
        textView {
            
            lifecycle.bind(itemObs){
                text = it.toString()
            }
            
        }.lparams(matchParent, wrapContent)
    
    }

}

```
    
## Dependencies

This package depends upon:
 - AppCompatV7 
 - [Anko](https://github.com/Kotlin/anko/wiki/Anko-Layouts).
 - [Observable properties](https://github.com/lightningkite/lk-kotlin/tree/master/observable-property)
 - [Observable lists](https://github.com/lightningkite/lk-kotlin/tree/master/observable-list)


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
    implementation "com.lightningkite.kotlin.android:anko-adapters-observable:[version]"
    implementation "com.lightningkite.kotlin:observable-property:[version]"
    implementation "com.lightningkite.kotlin:observable-list:[version]"
}
```

## Resources