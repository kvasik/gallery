package vladaliev.gallery.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import vladaliev.gallery.Constants
import vladaliev.gallery.R
import vladaliev.gallery.activities.FullPhotoActivity

class GalleryAdapter(val context: Context, val links: Array<String>) :
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        var imageView: ImageView

        init {
            imageView = mView.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.gallery_list_item,
                parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(links.get(position)).fitCenter()
                .placeholder(R.drawable.vector_placeholder).into(holder.imageView)

        holder.mView.setOnClickListener {
            val intent = Intent(context,FullPhotoActivity::class.java)
            intent.putExtra(Constants.EXTRA_LINKS_ARRAY,links)
            intent.putExtra(Constants.EXTRA_POSITION,position)
            context.startActivity(intent)
        }
    }

//    private fun createShareDialog(position: Int) {
//        val builder = AlertDialog.Builder(mContext)
//        builder.setTitle(mContext.getString(R.string.share)).setPositiveButton(
//                mContext.getString(R.string.yes), DialogInterface.OnClickListener { dialog, which ->
//            val sendIntent = Intent()
//            sendIntent.action = Intent.ACTION_SEND
//            sendIntent.putExtra(Intent.EXTRA_TEXT, mNoteList[position].getName() + "\n\n" + mNoteList[position].getNote())
//            sendIntent.type = "text/plain"
//            NotesListActivity.getAppContext().startActivity(sendIntent)
//        }).setNegativeButton(mContext.getString(R.string.no), null).show()
//    }

    override fun getItemCount(): Int {
        return links.size
    }
}