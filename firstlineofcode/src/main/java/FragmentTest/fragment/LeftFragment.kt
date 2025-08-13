package FragmentTest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firstlineofcode.R

class LeftFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstance: Bundle?): View?{
        return inflater.inflate(R.layout.left_fragment,container,false)
    }
}