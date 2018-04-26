package vladaliev.gallery.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.full_photo_activity.*
import vladaliev.gallery.Constants
import vladaliev.gallery.R
import vladaliev.gallery.Utils
import vladaliev.gallery.adapters.FullPhotoAdapter

class FullPhotoActivity : AppCompatActivity() {

    lateinit var utils: Utils

    lateinit var links: Array<String>
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_photo_activity)

        utils = Utils()

        initAdapter()
        checkConnection()

        stateful_view.setOfflineRetryOnClickListener {
            initAdapter()
        }
    }

    fun initAdapter() {
        getExtraData()

        pager.adapter = FullPhotoAdapter(this, links)
        pager.currentItem = position
    }

    fun getExtraData() {
        links = intent.getStringArrayExtra(Constants.EXTRA_LINKS_ARRAY)
        position = intent.getIntExtra(Constants.EXTRA_POSITION, 0)
    }

    fun checkConnection(){
        if(utils.isOnline(this))
            stateful_view.showContent()
        else
            stateful_view.showOffline()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.full_photo_activity_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> sharePhotoUrl()
        }

        return super.onOptionsItemSelected(item)
    }

    /* For photo link sharing */
    fun sharePhotoUrl() {
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, links.get(pager.currentItem))
        sendIntent.setType("text/plain")
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share)))
    }
}
