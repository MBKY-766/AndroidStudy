package FragmentTest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstlineofcode.R

class NewsContentFragment: Fragment() {
    private lateinit var contentLayout: LinearLayout
    private lateinit var newsTitle: TextView
    private lateinit var newsContent: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }
    fun refresh(title: String, content: String) {
        //根据id给控件赋值
        contentLayout=requireActivity().findViewById(R.id.contentLayout)
        newsTitle=requireActivity().findViewById(R.id.newsTitle)
        newsContent=requireActivity().findViewById(R.id.newsContent)
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title // 刷新新闻的标题
        newsContent.text = content // 刷新新闻的内容
    }
}