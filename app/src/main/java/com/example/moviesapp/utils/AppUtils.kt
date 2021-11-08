package com.example.moviesapp.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.util.Log
import com.example.moviesapp.R
import android.R.color

import android.text.style.ForegroundColorSpan

import android.R.string.no




class AppUtils {
    companion object {
        fun showLoadingDialog(context: Context): ProgressDialog {
            val progressDialog = ProgressDialog(context)
            progressDialog.show()
            if (progressDialog.window != null) {
                progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }

        fun getPageData(context: Context, pageNumber: Int) : String {
            var file_name = "page1.json"
            if(pageNumber == 2) { file_name = "page2.json" }
            else if(pageNumber == 3) { file_name = "page3.json" }
            val bufferReader = context.assets.open(file_name).bufferedReader()
            val data = bufferReader.use {
                it.readText()
            }
            Log.d("readFromAsset", data)
            return data;
        }

        fun getSpanCount(context: Context) : Int {
            val orientation: Int = context.resources.configuration.orientation
            return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                3
            } else {
                7
            }
        }

        fun setColorForPath(spannable: Spannable, texts: Array<String>, color: Int) {

            for (i in texts.indices) {
                val indexOfPath: Int = spannable.toString().lowercase().indexOf(texts[i].lowercase())
                if (indexOfPath == -1) {
                    continue
                }
                spannable.setSpan(
                    ForegroundColorSpan(color), indexOfPath,
                    indexOfPath + texts[i].length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }


    }

}