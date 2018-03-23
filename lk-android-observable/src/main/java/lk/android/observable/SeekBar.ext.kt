@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable


import android.widget.SeekBar
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

//import org.jetbrains.anko.onSeekBarChangeListener

/**
 * Binds this [SeekBar] two way to the observable property.
 * When the user picks a new value from the seek bar, the value of the observable property will change to the new value.
 * When the value of the observable property changes, the seek bar will be adjusted accordingly.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SeekBar.bindInt(range: ClosedRange<Int>, obs: MutableObservableProperty<Int>) {
    max = range.endInclusive - range.start + 1
    lifecycle.bind(obs) {
        val newProg = it - range.start
        if (this.progress != newProg) {
            this.progress = newProg
        }
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val newValue = progress + range.start
                if (obs.value != newValue) {
                    obs.value = newValue
                }
            }
        }
    })
}


/**
 * Binds this [SeekBar] two way to the observable property.
 * When the user picks a new value from the seek bar, the value of the observable property will change to the new value.
 * When the value of the observable property changes, the seek bar will be adjusted accordingly.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SeekBar.bindFloat(range: ClosedRange<Float>, steps: Int = 1000, obs: MutableObservableProperty<Float>) {
    max = steps
    lifecycle.bind(obs) {
        val newProg = ((it - range.start) / (range.endInclusive - range.start) * steps).toInt()
        if (this.progress != newProg) {
            this.progress = newProg
        }
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val newValue = ((progress.toDouble() / steps) * (range.endInclusive - range.start) + range.start).toFloat()
                if (obs.value != newValue) {
                    obs.value = newValue
                }
            }
        }
    })
}

/**
 * Binds this [SeekBar] two way to the observable property.
 * When the user picks a new value from the seek bar, the value of the observable property will change to the new value.
 * When the value of the observable property changes, the seek bar will be adjusted accordingly.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SeekBar.bindDouble(range: ClosedRange<Double>, steps: Int = 1000, obs: MutableObservableProperty<Double>) {
    max = steps
    lifecycle.bind(obs) {
        val newProg = ((it - range.start) / (range.endInclusive - range.start) * steps).toInt()
        if (this.progress != newProg) {
            this.progress = newProg
        }
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val newValue = ((progress.toDouble() / steps) * (range.endInclusive - range.start) + range.start)
                if (obs.value != newValue) {
                    obs.value = newValue
                }
            }
        }
    })
}