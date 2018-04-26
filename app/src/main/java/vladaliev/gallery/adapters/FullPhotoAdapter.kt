package vladaliev.gallery.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import vladaliev.gallery.R
import android.os.Parcelable
import com.github.chrisbanes.photoview.PhotoView

class FullPhotoAdapter(val context: Context, val links : Array<String>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val photoLayout = LayoutInflater.from(context).inflate(R.layout.full_photo,
                collection, false)!!

        val photoView = photoLayout.findViewById(R.id.image) as PhotoView

        Glide.with(context).load(links.get(position)).
                placeholder(R.drawable.vector_placeholder).into(photoView)

        collection.addView(photoLayout, 0)

        return photoLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

    override fun getCount(): Int {
        return links.size
    }
}