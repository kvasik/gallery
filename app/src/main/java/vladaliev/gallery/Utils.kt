package vladaliev.gallery

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    fun isOnline(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isAvailable && info.isConnected
    }
}