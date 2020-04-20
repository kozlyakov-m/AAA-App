package com.project.app.service

import com.project.app.ExitCodes
import com.project.app.dao.AccountingDAO
import com.project.app.domain.Permission
import com.project.app.domain.Session
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AccountingService(val accountingDAO: AccountingDAO) {

    var session: Session? = null

    fun accounting(
        res: Permission,
        ds: String,
        de: String,
        vol: String
    ): ExitCodes {

        val volInt = vol.toInteger() ?: return ExitCodes.INVALID_ACTIVITY
        val dateStart = ds.toDate() ?: return ExitCodes.INVALID_ACTIVITY
        val dateEnd = de.toDate() ?: return ExitCodes.INVALID_ACTIVITY

        return if (dateEnd >= dateStart && volInt >= 0) {
            session = Session(res, dateStart, dateEnd, volInt)
            accountingDAO.writeSession(res, ds, de, volInt)
            ExitCodes.SUCCESS
        } else {
            ExitCodes.INVALID_ACTIVITY
        }
    }

    private fun String.toDate(pattern: String = "yyyy-MM-dd"): LocalDate? = try {
        LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))
    } catch (e: DateTimeParseException) { // если дата некорректная возвращаем null
        null
    }

    private fun String.toInteger(): Int? = try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
