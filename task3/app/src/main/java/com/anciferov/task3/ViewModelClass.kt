package com.anciferov.task3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.net.URL

class ViewModelClass: ViewModel() {
    val liveData = MutableLiveData<Bitmap>()
    fun downloadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = URL("https://sun9-46.userapi.com/impg/Hj646DIin87E9FHXsZOpkXZfKbPuAn0KbzumHQ/-znLKZ9Ca_M.jpg?size=2160x2160&quality=96&sign=4f755b4e0be56ed1c09b2cc1cd216481&type=album")
            val mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main) {liveData.value = mIcon}
        }
    }
}