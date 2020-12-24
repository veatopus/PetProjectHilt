package kg.geektech.ruslan.petprojecthilt.ui.pictures.adapter

import android.view.View
import kg.geektech.ruslan.petprojecthilt.core.loadImage
import kg.geektech.ruslan.petprojecthilt.R
import kg.geektech.ruslan.petprojecthilt.core.BaseAdapter
import kg.geektech.ruslan.petprojecthilt.data.model.Pictures
import kg.geektech.ruslan.petprojecthilt.databinding.ItemPicturesBinding

class PicturesAdapter(val onPopupMenuClickListener: (Pictures) -> Unit) : BaseAdapter<Pictures>(R.layout.item_pictures, mutableListOf()) {

    override fun getItemCount(): Int {
        return if (data.size == 0) 0 else Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(data[position % data.size])
    }

    override fun onBind(view: View, model: Pictures) {
        ItemPicturesBinding.bind(view).also { binding ->
            model.url?.let { url -> binding.imageViewPictures.loadImage(url) }
            binding.imageViewPopupMenu.setOnClickListener { onPopupMenuClickListener(model) }
        }
    }

}