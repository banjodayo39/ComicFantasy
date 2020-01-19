package com.example.eBox.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eBox.R
import com.example.eBox.data.remote.ComicResults
import com.example.eBox.ui.fragments.ComicFragment
import com.example.eBox.util.ShapeAppearanceTransformation
import com.example.eBox.util.loadImageWithGlide


class ComicFragmentAdapterclass(
    private var list: List<ComicResults?>,
    private val listener: ComicFragment.OnFragmentInteractionListener,
    private val comicFragment: ComicFragment
) : RecyclerView.Adapter<ComicFragmentAdapterclass.ViewHolder>() {

    private val shapeTransform =
        ShapeAppearanceTransformation(R.style.ShapeAppearance_Owl_SmallComponent)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comicResults: ComicResults? = list[position]
        holder.bind(comicResults!!)
        holder.comicPosition = position
        shapeTransform
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<ComicResults?>) {
        this.list = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_list_item, parent, false)!!) {

        private var mthumbnail: ImageView? = null
        private var mtitle: TextView? = null
        private var mDescription: TextView? = null
        var rootLayout : ViewGroup?=null
        var comicPosition = 0

        init {
            mthumbnail = itemView.findViewById(R.id.comic_thumbnail)
            mtitle = itemView.findViewById(R.id.comic_title)
            mDescription = itemView.findViewById(R.id.comic_description)
            rootLayout = itemView.findViewById(R.id.layout_root)
        }

        fun bind(result: ComicResults) {
            mthumbnail?.loadImageWithGlide(result.thumbnail!!.path.toString() + "." + result.thumbnail!!.extension)
            mtitle?.text = itemView.resources.getString(R.string._title, result.title)
            var desc = itemView.resources.getString(R.string._comic_description, result.description)
            mDescription!!.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(desc).toString()
            }
            itemView.setOnClickListener {
               listener.onThumbnailClicked(result)
            }
        }


    }
}