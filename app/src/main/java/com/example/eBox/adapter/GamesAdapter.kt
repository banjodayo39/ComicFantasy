package com.example.eBox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eBox.R
import com.example.eBox.data.remote.GamesResult
import com.example.eBox.games.fragments.GamesFragment

class GamesAdapter(
    private val list: List<GamesResult>,
    private val listener: GamesFragment.OnFragmentInteractionListener,
    private val gamesFragment: GamesFragment
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {
    private var optionSelectedText = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: GamesResult = list[position]
        holder.bind(results)
        holder.gamesViewPosition = position
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.games_list_item, parent, false)!!),
        View.OnClickListener {

        private var question: TextView? = null
        private var selection_result: TextView? = null
        private var option_1: Button? = null
        private var option_2: Button? = null
        private var submit_button: Button? = null
        private var optionSelectedText = ""



        var gamesViewPosition = 0

        init {
            question = itemView.findViewById(R.id.question_text)
            option_1 = itemView.findViewById(R.id.option_1)
            option_2 = itemView.findViewById(R.id.option_2)
            submit_button = itemView.findViewById(R.id.button_submit)
            selection_result = itemView.findViewById(R.id.selection_result)
        }

        override fun onClick(v: View?) {
            val checked = (v as Button).callOnClick()
            when (v.id) {
                R.id.option_1 ->
                    if (checked) {

                    }
                R.id.option_2 ->
                    if (checked) {
                        setText( option_2!!.text.toString())
                        option_2!!.setBackgroundResource(R.color._100_red)
                        option_1!!.setBackgroundResource(R.drawable.button_shape)
                    }
            }
        }

        fun bind(triviaResult: GamesResult)  {
            question?.text = triviaResult.question


            var text1 = triviaResult.correct_answer
            var text2 = triviaResult.incorrect_answers?.get(0)

            val option = mutableListOf<String>(text1!!, text2!!)
            option.shuffle()
            option_1?.text = option[0]
            option_2?.text = option[1]

            option_1!!.setOnClickListener {
                setText(option_1!!.text.toString())
                option_1!!.setBackgroundResource(R.color._100_red)
                option_2!!.setBackgroundResource(R.drawable.button_shape)
                optionSelectedText = option_1!!.text.toString()

            }

            option_2!!.setOnClickListener {
                setText(option_2!!.text.toString())
                option_2!!.setBackgroundResource(R.color._100_red)
                option_1!!.setBackgroundResource(R.drawable.button_shape)
                optionSelectedText = option_2!!.text.toString()

            }

            submit_button!!.setOnClickListener {
                changeOptionBackground(option_2!!, triviaResult)
                changeOptionBackground(option_1!!,triviaResult)
            }

        }

        private fun setText(text:String){
            optionSelectedText = text
        }

        private fun getText():String{
            return optionSelectedText
        }
        private fun changeOptionBackground(button: Button,triviaResult: GamesResult) {
            if (button.text == getText() && getText()== triviaResult.correct_answer) {
                   button.setBackgroundResource(R.color.green_button)
                   button.text = triviaResult.correct_answer
                selection_result!!.text = gamesFragment.getString(R.string.correct,triviaResult.correct_answer,button.text)
                selection_result!!.visibility = View.VISIBLE

            }
            else if (button.text == getText() && button.text != triviaResult.correct_answer) {
                button.setBackgroundResource(R.color.red)
                selection_result!!.text =
                    gamesFragment.getString(
                        R.string.incorrect_response,
                        triviaResult.correct_answer,
                        button.text
                    )
                button.text = triviaResult.incorrect_answers!![0]
                selection_result!!.visibility = View.VISIBLE
            }
        }
    }


}
