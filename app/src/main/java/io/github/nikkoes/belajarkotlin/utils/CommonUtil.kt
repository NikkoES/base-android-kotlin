package io.github.nikkoes.belajarkotlin.utils

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CommonUtil {

    companion object {

        lateinit var gson: Gson

        fun uppercaseString(str: String): String {
            val ch = str.toCharArray()
            for (i in 0 until str.length) {
                if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {
                    if (ch[i] in 'a'..'z') {
                        ch[i] = (ch[i] - 'a' + 'A'.toInt()).toChar()
                    }
                } else if (ch[i] in 'A'..'Z')
                    ch[i] = ch[i] + 'a'.toInt() - 'A'.toInt()
            }
            return String(ch)
        }
    }

    fun formatStringToDate(tanggal: String, fromFormat: String, toFormat: String) : String {
        val fromString = SimpleDateFormat(fromFormat, Locale.ENGLISH)
        val fromDate = SimpleDateFormat(toFormat, Locale.ENGLISH)
        var date: Date? = null
        try {
            date = fromString.parse(tanggal)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return fromDate.format(date)
    }

    fun formatDateToString(date: Date, format: String): String {
        val df = SimpleDateFormat(format, Locale.ENGLISH)
        return df.format(date)
    }

    fun currentDate(format: String): String {
        val tanggal: String
        val date = Date()
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val tz = TimeZone.getTimeZone("Asia/Jakarta")
        dateFormat.timeZone = tz
        tanggal = dateFormat.format(date)
        return tanggal
    }

    fun currentTime(format: String): String {
        val jam: String
        val date = Date()
        //val dateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val tz = TimeZone.getTimeZone("Asia/Jakarta")
        dateFormat.timeZone = tz
        jam = dateFormat.format(date)
        return jam
    }

    fun convertHari(num: Int): String {
        var hari = ""
        when (num) {
            1 -> hari = "Senin"
            2 -> hari = "Selasa"
            3 -> hari = "Rabu"
            4 -> hari = "Kamis"
            5 -> hari = "Jumat"
            6 -> hari = "Sabtu"
            0 -> hari = "Minggu"
        }
        return hari
    }

    fun convertStringToJson(parameter: Array<String>, value: Array<String>): JSONObject {
        val jsonObject = JSONObject()
        for (i in parameter.indices) {
            try {
                jsonObject.put(parameter[i], value[i])
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
        return jsonObject
    }

    fun JsonToString(obj: Any): String {
        return gson.toJson(obj)
    }

    fun <T> stringToJson(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }

    fun formatPriceIndonesia(price: Double?): String {
        val kursIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val formatRp = DecimalFormatSymbols()

        formatRp.currencySymbol = "Rp. "
        formatRp.monetaryDecimalSeparator = ','
        formatRp.groupingSeparator = '.'

        kursIndonesia.decimalFormatSymbols = formatRp
        return kursIndonesia.format(price)
    }

    fun validateEmptyEntries(fields: ArrayList<View>): Boolean {
        var check = true

        for (i in fields.indices) {
            val currentField = fields[i] as EditText
            if (currentField.text.toString().length <= 0) {
                currentField.error = "Please fill out this field"
                check = false
            }
        }
        return check
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}
