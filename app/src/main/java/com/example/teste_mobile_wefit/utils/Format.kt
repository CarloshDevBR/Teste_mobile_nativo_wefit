package com.example.teste_mobile_wefit.utils

import com.example.teste_mobile_wefit.constants.AppConstants
import java.text.NumberFormat
import java.util.Locale

class Format {
    fun formatCurrencyToBRL(value: Double): String {
        return NumberFormat.getCurrencyInstance(
            Locale(
                AppConstants.NATIONALITY.LANGAGUE,
                AppConstants.NATIONALITY.COUNTRY
            )
        ).format(value)
    }
}