package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.GamesResult
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Trivia
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.util.loadImageWithGlide
import com.google.android.youtube.player.internal.t
import com.google.android.youtube.player.internal.v
import kotlinx.android.synthetic.main.games_list_item.*
import java.util.*

class GamesAdapter(private val list: List<GamesResult>,
                   private val listener: GamesFragment.OnFragmentInteractionListener,
                   private  val gamesFragment: GamesFragment
)
    : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: GamesResult= list[position]
        holder.bind(results)
        holder.gamesViewPosition = position
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.games_list_item, parent, false)!!) {
        private var question: TextView? = null
        private var selection_result :TextView? = null
        private var option_1: Button? = null
        private var option_2: Button? = null
        private var submit_button:Button? = null
        private var optionSelectedText = ""
        var gamesViewPosition = 0

        init {
            question = itemView.findViewById(R.id.question_text)
            option_1=itemView.findViewById(R.id.option_1)
            option_2=itemView.findViewById(R.id.option_2)
            submit_button = itemView.findViewById(R.id.button_submit)
            selection_result = itemView.findViewById(R.id.selection_result)
        }

        fun bind(triviaResult:GamesResult) {
            question?.text = triviaResult.question
            val random= java.util.Random().nextInt(1)
            var positions  = (0..1).map { Random().nextInt() }
            var text1=triviaResult.correct_answer
            var text2= triviaResult.incorrect_answers?.get(0)

            val option  = mutableListOf<String>(text1!!,text2!!)
            option.shuffle()
            option_1?.text=option[0]
            option_2?.text=option[1]

            option_1!!.setOnClickListener {
                option_1!!.setBackgroundResource(R.color._100_red)
                option_2!!.setBackgroundResource(R.drawable.button_shape)
                optionSelectedText = option_1!!.text.toString()
                
                submit_button!!.setOnClickListener {
                    if (optionSelectedText == triviaResult.correct_answer){
                        option_1!!.setBackgroundResource(R.color.green_button)
                        selection_result!!.text = gamesFragment.getString(R.string.correct)
                    }
                    else {
                        option_1!!.setBackgroundResource(R.color.red)
                        selection_result!!.text = gamesFragment.getString(R.string.incorrect)+String.format(" ,answer is %s", triviaResult.correct_answer)
                    }
                }
            }

            option_2!!.setOnClickListener {
                option_2!!.setBackgroundResource(R.color._100_red)
                option_1!!.setBackgroundResource(R.drawable.button_shape)
                optionSelectedText = option_2!!.text.toString()

                submit_button!!.setOnClickListener {
                    if (optionSelectedText == triviaResult.correct_answer){
                        option_2!!.setBackgroundResource(R.color.green_button)
                        selection_result!!.text = gamesFragment.getString(R.string.correct)
                        selection_result!!.visibility = View.VISIBLE
                    }
                    else {
                        option_2!!.setBackgroundResource(R.color.red)
                        selection_result!!.text = gamesFragment.getString(R.string.incorrect) +String.format(" ,answer is %s", triviaResult.correct_answer)
                        selection_result!!.visibility = View.VISIBLE
                    }
                }
            }
        }
        }

    }
