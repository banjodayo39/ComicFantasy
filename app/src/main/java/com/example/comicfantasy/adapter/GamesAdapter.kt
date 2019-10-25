package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.GamesResult
import com.example.comicfantasy.data.remote.MovieResult
import com.example.comicfantasy.data.remote.Trivia
import com.example.comicfantasy.games.fragments.GamesFragment
import com.example.comicfantasy.movie.fragment.MovieFragment
import com.example.comicfantasy.util.loadImageWithGlide
import java.util.*

class GamesAdapter(private val list: List<GamesResult>,
                   private val listener: GamesFragment.OnFragmentInteractionListener
)
    : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: GamesResult= list[position]
        holder.bind(results)
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.games_list_item, parent, false)!!) {
        private var question: TextView? = null
        private var option_1: Button? = null
        private var option_2: Button? = null
        private var option_3:Button?=null

        init {
            question = itemView.findViewById(R.id.question_text)
            option_1=itemView.findViewById(R.id.option_1)
            option_2=itemView.findViewById(R.id.option_2)
        }

        fun bind(triviaResult:GamesResult) {
            question?.text =triviaResult?.question
            val random= java.util.Random().nextInt(1)
            var positions  = (0..1).map { Random().nextInt() }
            var text1=triviaResult.correct_answer
            var text2= triviaResult.incorrect_answers?.get(0)

            val option  = mutableListOf<String>(text1!!,text2!!)
            option.shuffle()
            option_1?.text=option[0]
            option_2?.text=option[1]

            }

        }

    }
