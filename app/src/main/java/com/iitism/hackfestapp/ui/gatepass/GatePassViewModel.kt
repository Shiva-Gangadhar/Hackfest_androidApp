package com.iitism.hackfestapp.ui.gatepass

import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class GatePassViewModel : ViewModel() {

    val ID = "ZNY5914"


    fun getQrBitmapList(ID: String): List<GatePassModel> {
//        val qrCodeBitmap = BarcodeEncoder().encodeBitmap(value, BarcodeFormat.QR_CODE, 300, 300)
//        return qrCodeBitmap
        return listOf(
            GatePassModel(
                QrImage = BarcodeEncoder().encodeBitmap(
                    "in/${ID}",
                    BarcodeFormat.QR_CODE,
                    250,
                    250
                ),
                "Check IN"
            ),
            GatePassModel(
                QrImage = BarcodeEncoder().encodeBitmap(
                    "out/${ID}",
                    BarcodeFormat.QR_CODE,
                    250,
                    250,
                ),
                "Check OUT"
            )
        )
    }

    fun getAdminQrBitmapList(ID: String): List<GatePassModel> {
//        val qrCodeBitmap = BarcodeEncoder().encodeBitmap(value, BarcodeFormat.QR_CODE, 300, 300)
//        return qrCodeBitmap
        return listOf(
            GatePassModel(
                QrImage = BarcodeEncoder().encodeBitmap(
                    "https://www.hackfestiitism.com/refreshment",
                    BarcodeFormat.QR_CODE,
                    250,
                    250
                ),
                "Refreshment - 1"
            ),
            GatePassModel(
                QrImage = BarcodeEncoder().encodeBitmap(
                    "https://www.hackfestiitism.com/refreshment_two",
                    BarcodeFormat.QR_CODE,
                    250,
                    250,
                ),
                "Refreshment - 2"
            )
        )
    }

}