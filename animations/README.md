# Module animations

[Return to Index](../)

## Package description

Includes useful tools for animating your views conveniently, including:

- SwapView - An animated view holder which can swap to holding a new view in an animated fashion
- TransitionView - An animated view holder which transitions between its children
- AnimationSet - A simple class defining a pair of animations for animating between two views, as well as some useful built-in transitions
- TypedValueAnimator - Value animators that are properly typed
- widthAnimator and heightAnimator - Functions that build you value animators attached to a view's size
    
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
    implementation "com.lightningkite.kotlin.android:animations:[version]"
}
```

## Resources