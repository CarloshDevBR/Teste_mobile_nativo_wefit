package com.example.teste_mobile_wefit.utils

import com.example.teste_mobile_wefit.constants.AppConstants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppDate {
    fun getDateToday(): String {
        return SimpleDateFormat(
            "dd/MM/yyyy",
            Locale(AppConstants.NATIONALITY.LANGAGUE, AppConstants.NATIONALITY.COUNTRY)
        ).format(Date())
    }

    fun getHoursToday(): String {
        return SimpleDateFormat(
            "HH:mm",
            Locale(AppConstants.NATIONALITY.LANGAGUE, AppConstants.NATIONALITY.COUNTRY)
        ).format(Date())
    }
}