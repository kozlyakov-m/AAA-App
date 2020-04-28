package com.project.web.di

import com.google.inject.matcher.Matchers
import com.google.inject.servlet.ServletModule
import com.project.web.di.logger.Log4JTypeListener
import com.project.web.filters.GeneralFilter
import com.project.web.servlets.*

class GuiceServletModule : ServletModule() {
    override fun configureServlets() {
        super.configureServlets()
        bindListener(
            Matchers.any(),
                Log4JTypeListener()
        )
        filter("/*").through(GeneralFilter::class.java)
        serve("/hello").with(HelloServlet::class.java)
        serve("/echo/get").with(EchoServlet::class.java)
        serve("/echo/post").with(EchoServlet::class.java)
        serve("/ajax/user").with(UserServlet::class.java)
        serve("/ajax/authority").with(AuthorityServlet::class.java)
        serve("/ajax/activity").with(ActivityServlet::class.java)
    }
}
