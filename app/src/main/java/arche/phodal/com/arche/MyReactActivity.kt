package arche.phodal.com.arche

import android.app.Activity
import android.os.Bundle

import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage

import java.util.Arrays

class MyReactActivity : Activity(), DefaultHardwareBackBtnHandler {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    protected val packages: List<ReactPackage>
        get() = Arrays.asList<ReactPackage>(
                MainReactPackage()
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mReactRootView = ReactRootView(this)
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(application)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackages(packages)
                //                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build()
        mReactRootView!!.startReactApplication(mReactInstanceManager, "RNArche", null)

        setContentView(mReactRootView)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()

        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostPause(this)
        }
    }

    override fun onResume() {
        super.onResume()

        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostResume(this, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostDestroy(this)
        }
    }

    override fun onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}