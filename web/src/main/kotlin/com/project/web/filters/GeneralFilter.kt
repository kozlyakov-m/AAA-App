package com.project.web.filters

import com.google.inject.Singleton
import org.apache.logging.log4j.kotlin.KotlinLogger
import com.project.web.di.logger.InjectLogger
import java.io.IOException
import javax.servlet.*

@Singleton
class GeneralFilter : Filter {
    @InjectLogger
    lateinit var logger: KotlinLogger

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        logger.debug("doFilter method")

        request.characterEncoding = "UTF-8"
        response.characterEncoding = "UTF-8"
        response.contentType = "text/html"

        chain.doFilter(request,response)
    }

    override fun destroy() {}
}
