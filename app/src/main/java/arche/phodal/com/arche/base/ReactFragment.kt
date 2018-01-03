package arche.phodal.com.arche.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView

import arche.phodal.com.arche.ArcheApplication

abstract class ReactFragment : Fragment() {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    abstract val mainComponentName: String

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mReactRootView = ReactRootView(context)
        mReactInstanceManager = (activity.application as ArcheApplication)
                .reactNativeHost
                .reactInstanceManager

    }

    override fun onCreateView(inflater: LayoutInflater?, group: ViewGroup?, savedInstanceState: Bundle?): ReactRootView? {
        super.onCreate(savedInstanceState)
        return mReactRootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mReactRootView!!.startReactApplication(
                mReactInstanceManager,
                mainComponentName,
                null
        )
    }
}