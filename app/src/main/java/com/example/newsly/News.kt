package com.example.newsly

import android.hardware.camera2.TotalCaptureResult

data class News (val totalResults:Int,val articles:List<Article>){
}