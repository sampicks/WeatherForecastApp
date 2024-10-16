package com.example.weatherforecastapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat

class Utils {

    companion object{
        fun formatDate(strDate: String) : String{//"MMMM d, yyyy"
            var formatter = SimpleDateFormat("yyyy-MM-dd")
            val newDate =  formatter.parse(strDate)
            var newDateFormat = SimpleDateFormat(
                "dd MMM"
            )
            val stringDate: String = newDateFormat.format(newDate)

            return stringDate
        }
    }
}