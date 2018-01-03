package arche.phodal.com.arche

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)
        val button: Button = view!!.findViewById(R.id.open_rn_button)
        button.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.open_rn_button -> {
                val reactActivity = Intent(v.context, ArcheReactActivity::class.java)
                this.startActivity(reactActivity)
            }

        }
    }
}