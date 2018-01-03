package arche.phodal.com.arche

import ArcheReactFragment
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val OVERLAY_PERMISSION_REQ_CODE = 2018

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + packageName))
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val reactActivity = Intent(this, ArcheReactActivity::class.java)
                this.startActivity(reactActivity)
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = ArcheReactFragment()
                supportFragmentManager.beginTransaction()
                        .add(R.id.container, fragment)
                        .commit()

                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_webview -> {
                val fragment = ArcheWebViewFragment()
                supportFragmentManager.beginTransaction()
                        .add(R.id.container, fragment)
                        .commit()

                message.setText(R.string.title_webview)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("ShowToast")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "Lost Permissions", Toast.LENGTH_SHORT)
                }
            }
        }
    }
}
