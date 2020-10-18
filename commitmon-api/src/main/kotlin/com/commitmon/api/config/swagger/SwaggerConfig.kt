package com.commitmon.api.config.swagger

import com.commitmon.api.logger
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig(
    private val buildProperties: BuildProperties
) {
    private val log by logger()

    @Bean
    fun docket(): Docket {
        log.debug("Starting Swagger...")

        return Docket(DocumentationType.SWAGGER_2)
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                ServerHttpRequest::class.java,
                ServerHttpResponse::class.java,
                ServerWebExchange::class.java
            )
            .apiInfo(apiInfo())
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.commitmon.api"))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
    }

    private fun apiInfo() = ApiInfoBuilder()
        .title(buildProperties.name)
        .version(buildProperties.version)
        .build()
}