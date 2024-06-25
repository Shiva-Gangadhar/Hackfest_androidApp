package com.iitism.hackfestapp.ui.homefragment

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iitism.hackfestapp.R
import com.iitism.hackfestapp.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Timer:
        object : CountDownTimer(100, 100) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                binding.logo.animate().alpha(1F).setDuration(1250).scaleX(3f).scaleY(3f).rotation(720f).setDuration(2000).start()
            }
        }.start()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return  view
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

//        videoView = binding.videoView
//        videoView.setVideoPath(path)
//        videoView.start()
//        videoView.scaleX = 3F
//        videoView.setOnCompletionListener {
//            videoView.start()
//        }
//        videoView.setOnClickListener {
//            if(videoView.isPlaying) videoView.pause()
//            else videoView.resume()
//        }
        countDownHackfestStart()
    }



    override fun onResume() {

        //videoView.start()
        super.onResume()
    }

     fun countDownHackfestStart(){
        val handler = android.os.Handler()
        val runnable = object : java.lang.Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                try {
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val futureDate: Date = dateFormat.parse("2024-4-19 00:00:00")
                    if (!currentDate.after(futureDate)) {
                        var diff: Long = (futureDate.getTime()
                                - currentDate.getTime())
                        val days = diff / (24 * 60 * 60 * 1000)
                        diff -= days * (24 * 60 * 60 * 1000)
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000
                        binding.txtDay.setText("" + String.format("%02d", days))
                        binding.txtHour.setText("" + String.format("%02d", hours))
                        binding.txtMinute.setText("" + String.format("%02d", minutes))
                        binding.txtSecond.setText(("" + String.format("%02d", seconds)))
                    }
                    else {
                        countDownHackfestEnd()
                        binding.textcounterdown.text = "HackFest is Live Now !!!"
                        binding.LinearLayout.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable, 1 * 1000)

    }



    fun countDownHackfestEnd(){
        val handler = android.os.Handler()
        val runnable = object : java.lang.Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                try {
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val futureDate: Date = dateFormat.parse("2024-4-21 12:00:00")
                    if (!currentDate.after(futureDate)) {
                        var diff: Long = (futureDate.getTime()
                                - currentDate.getTime())
                        val hours = diff / (60 * 60 * 1000)
                        diff -= hours * (60 * 60 * 1000)
                        val minutes = diff / (60 * 1000)
                        diff -= minutes * (60 * 1000)
                        val seconds = diff / 1000
                        binding.txtHour.setText("" + String.format("%02d", hours))
                        binding.txtMinute.setText("" + String.format("%02d", minutes))
                        binding.txtSecond.setText(("" + String.format("%02d", seconds)))
                    }
                    else {
                        binding.textcounterdown.text = "Thank's for Participating"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        handler.postDelayed(runnable, 1 * 1000)
    }





}