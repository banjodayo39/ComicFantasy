package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Results
import com.example.comicfantasy.comic.fragments.ComicFragment
import com.example.comicfantasy.util.loadImageWithGlide
import com.materialstudies.owl.util.ShapeAppearanceTransformation


class HomeFragmentAdapterclass(
    private var list: List<Results?>,
    private val listener: ComicFragment.OnFragmentInteractionListener
) : RecyclerView.Adapter<HomeFragmentAdapterclass.ViewHolder>() {

    private val shapeTransform =
        ShapeAppearanceTransformation(R.style.ShapeAppearance_Owl_SmallComponent)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results: Results? = list[position]
        holder.bind(results!!)
        holder.comicPosition = position
        shapeTransform
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<Results?>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_list_item, parent, false)!!) {

        private var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        private var mCharacter:TextView? = null
        private var issueNumber: TextView? = null
        private var mAuthor : TextView? = null
        var comicPosition = 0
        var imageTransform = shapeTransform

        init {
            mthumbnail = itemView.findViewById(R.id.comic_thumbnail)
            mtitle = itemView.findViewById(R.id.comic_title)
            mAuthor = itemView.findViewById(R.id.comic_author)
            mCharacter = itemView.findViewById(R.id.comic_character)
            issueNumber = itemView.findViewById(R.id.comic_issue_no)

        }

        fun bind(result: Results) {
            mthumbnail?.loadImageWithGlide(result.thumbnail!!.path.toString() + "." + result.thumbnail!!.extension)
            mtitle?.text = itemView.resources.getString(R.string._title,result.title)
            issueNumber!!.text = itemView.resources.getString(R.string._issue_no,result.issueNumber)
            mCharacter!!.text = itemView.resources.getString(R.string._character,result.characters!!.items!![0]!!.name.toString())
            mAuthor!!.text = itemView.resources.getString(R.string._author,result.creators!!.items!![0]!!.name.toString())

            itemView.setOnClickListener {
                listener.onThumbnailClicked(result)
            }
        }


    }
}