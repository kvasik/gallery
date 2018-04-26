package vladaliev.gallery.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import vladaliev.gallery.R
import vladaliev.gallery.Utils
import vladaliev.gallery.adapters.GalleryAdapter

class MainActivity : AppCompatActivity() {

    lateinit var utils: Utils

    private var links = arrayOf("https://wallpapershome.ru/images/pages/ico_h/16431.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/15513.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/13655.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/4479.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/10604.jpeg",
            "https://wallpapershome.ru/images/pages/ico_h/13514.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/13537.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/17756.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/16432.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/16650.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/378.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/16720.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/17407.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/14988.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/17748.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/17811.jpg",
            "https://wallpapershome.ru/images/pages/ico_h/16101.jpeg",
            "https://wallpapershome.ru/images/pages/ico_h/15267.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        utils = Utils()
        initList()

        stateful_view.setOfflineRetryOnClickListener {
            setAdapter()
        }
    }

    fun initList() {
        val layoutManager = GridLayoutManager(this, 2)
        gallery_rv.layoutManager = layoutManager

        setAdapter()
    }

    fun setAdapter() {
        if (utils.isOnline(this)) {
            val adapter = GalleryAdapter(this, links)
            gallery_rv.adapter = adapter

            stateful_view.showContent()
        } else
            stateful_view.showOffline()
    }
}
