package com.example.comicfantasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicfantasy.R
import com.example.comicfantasy.data.remote.Image
import com.example.comicfantasy.util.loadImageWithGlide


class SeriesFragmentAdapter(private val list: List<Image>) : RecyclerView.Adapter<SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SeriesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holderSeries: SeriesViewHolder, position: Int) {
        val image:Image = list[position]
        holderSeries.bind(image)
    }

    override fun getItemCount(): Int = list.size

}

class SeriesViewHolder(inflater: LayoutInflater?, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater?.inflate(R.layout.comic_image_list, parent, false)!!) {
    private var mImage: ImageView? = null


    init {
        mImage = itemView.findViewById(R.id.comic_images)

    }

    fun bind(image: Image) {

        mImage?.loadImageWithGlide(image.path.toString()+"."+image.extension)
        /*var image = listOf<Image>()
        var i:Int =0
        image.forEach{
            mImage?.loadImageWithGlide(
                results.images!!.toList()[i]?.path.toString()
                    +"."+results.images!!.toList()[i]?.extension)
                 i+=1
        }*/
    }


}