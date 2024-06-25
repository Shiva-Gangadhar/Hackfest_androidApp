package com.iitism.hackfestapp.ui.timelinefragment

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.iitism.hackfestapp.R
import kotlinx.coroutines.delay


class TimelineFragment : Fragment() {

    companion object {
        fun newInstance() = TimelineFragment()
    }

    private lateinit var viewModel: TimelineViewModel
    private lateinit var left:List<LinearLayoutCompat>
    private lateinit var right:List<LinearLayoutCompat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_timeline, container, false)
        left= listOf(v.findViewById(R.id.left1),v.findViewById(R.id.lt2),v.findViewById(R.id.lft3),v.findViewById(R.id.l3))
        right=listOf(v.findViewById(R.id.right1),v.findViewById(R.id.rght),v.findViewById(R.id.rht3))
        return v
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(TimelineViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    animateLinearLayouts()
}

    private fun animateLinearLayouts() {
        // Animate leftLinearLayout to slide from the left
        var cnt = 7
        var i=0
        var j=0
        while (cnt > 0) {
            if(i!=4){
                ObjectAnimator.ofFloat(left[i], "translationX", 0f).apply {
                    duration = 1000
                    startDelay = (500*i).toLong()
                    start()
                i++}
            }
            //Thread.sleep(1000)
            if(j!=3) {
                ObjectAnimator.ofFloat(right[j], "translationX", 0f).apply {
                    duration = 1000
                    startDelay= (500*j).toLong()
                        start()
                        j++
                }

            }
            cnt--
        }
    }
}