package com.dinosaur.app.service

import com.dinosaur.app.domain.Permission
import com.dinosaur.app.domain.Session
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AccountingService {

    fun accounting(res: Permission,
                   ds: String,
                   de: String,
                   vol: String): Session? {

        val volInt = vol.toInteger() ?: return null
        val dateStart = ds.toDate() ?: return null
        val dateEnd = de.toDate() ?: return null

        return if (dateEnd >= dateStart && volInt >= 0) {
            Session(res, dateStart, dateEnd, volInt)
        } else {
            null
        }

    }

    private fun String.toDate(pattern: String = "yyyy-MM-dd"): LocalDate? = try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))
    } catch (e: DateTimeParseException) { //если дата некорректная возвращаем null
        null
    }

    private fun String.toInteger(): Int? = try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}