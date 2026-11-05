package com.example.whalefalls_casus.util

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleHelper {

    fun applyLocale(context: Context, language: String): Context {

        val locale = when (language) {
            "Afrikaans" -> Locale.forLanguageTag("af")
            "isiXhosa" -> Locale.forLanguageTag("xh")
            else -> Locale.ENGLISH
        }

        Locale.setDefault(locale)

        val configuration = Configuration(
            context.resources.configuration
        )

        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }
}