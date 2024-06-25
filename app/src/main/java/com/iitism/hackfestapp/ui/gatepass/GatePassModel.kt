package com.iitism.hackfestapp.ui.gatepass

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
data class GatePassModel(
    val QrImage : Bitmap,
    val textValue : String
)