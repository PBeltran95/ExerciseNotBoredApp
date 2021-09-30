package ar.com.example.notbored.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Category(
    val categoryName:String = "",
    val activities:List<RecreationalActivity>
)

@Parcelize
data class RecreationalActivity(
    val activityName:String = "",
    val price : Double = 0.0,
    val participants:String = "2",
    val categoryName: String
): Parcelable
