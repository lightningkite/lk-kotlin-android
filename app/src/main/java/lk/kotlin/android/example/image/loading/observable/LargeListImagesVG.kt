package lk.kotlin.android.example.image.loading.observable

import android.net.Uri
import android.os.Environment
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.image.loading.image.ImageLoader
import lk.android.image.loading.observable.bindUri
import lk.android.mighty.view.ViewGenerator
import lk.android.ui.thread.UIThread
import lk.anko.adapters.observable.listAdapter
import lk.anko.animations.observable.progressLayout
import lk.anko.extensions.anko
import lk.anko.extensions.verticalGridRecyclerView
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.observable.list.ObservableListWrapper
import lk.kotlin.observable.property.transform
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import java.io.File

class LargeListImagesVG() : ViewGenerator {

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalGridRecyclerView(2) {

        val list = ObservableListWrapper<Uri>()
        access.requestPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) {
            if (it) {
                Async.execute {
                    val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    fun onFileRecursive(file: File, action: (File) -> Unit) {
                        if (file.isDirectory) {
                            file.listFiles().forEach { onFileRecursive(it, action) }
                        } else {
                            action(file)
                        }
                    }
                    onFileRecursive(root) {
                        UIThread.execute {
                            list.add(Uri.fromFile(it))
                        }
                    }
                }
            }
        }

        val imageLoader = ImageLoader(imageMaxWidth = 512, imageMaxHeight = 512)

        adapter = listAdapter(list) { itemObs ->
            progressLayout { runningObs ->
                imageView {
                    bindUri(itemObs.transform { it as Uri? }, imageLoader, loadingObs = runningObs)
                }
            }.lparams(matchParent, dip(150)) { margin = dip(8) }
        }

    }
}