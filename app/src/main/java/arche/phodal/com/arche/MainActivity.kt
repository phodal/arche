package arche.phodal.com.arche

import ArcheReactFragment
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var homeFragment: HomeFragment? = null
    private var archeReactFragment: ArcheReactFragment? = null
    private var archeWebViewFragment: ArcheWebViewFragment? = null
    var fragments: Array<Fragment>? = null
    private var lastShowFragment = 0

    private val OVERLAY_PERMISSION_REQ_CODE = 2018

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initFragments()

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + packageName))
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE)
            }
        }
    }

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (lastShowFragment != 0) {
                    switchFragment(lastShowFragment, 0)
                    lastShowFragment = 0
                }
                message.setText(R.string.title_home)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, 1)
                    lastShowFragment = 1;
                }
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_webview -> {
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                message.setText(R.string.title_webview)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments!![lastIndex])
        if (!fragments!![index].isAdded()) {
            transaction.add(R.id.container, fragments!![index])
        }
        transaction.show(fragments!![index]).commitAllowingStateLoss()
    }

    private fun initFragments() {
        homeFragment = HomeFragment()
        archeReactFragment = ArcheReactFragment()
        archeWebViewFragment = ArcheWebViewFragment()
        fragments = arrayOf<Fragment>(homeFragment!!, archeReactFragment!!, archeWebViewFragment!!)
        lastShowFragment = 0
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, homeFragment)
                .show(homeFragment)
                .commit()
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
