package com.commitmon.domain.config.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jmx.export.MBeanExporter
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.commitmon.domain.repository"],
    entityManagerFactoryRef = "commitMonEntityManagerFactory",
    transactionManagerRef = "commitMonTransactionManager")
@PropertySource("classpath:properties/database/commitmon-database-\${spring.profiles.active}.properties")
class CommitMonDatabaseConfig(private val mbeanExporter: MBeanExporter) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "commit-mon.jpa")
    fun commitMonJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun commitMonHibernateSettings(): HibernateSettings {
        return HibernateSettings()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "commit-mon")
    fun commitMonHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @Primary
    fun commitMonDataSource(): DataSource {
        val dataSource = HikariDataSource(commitMonHikariConfig())
        mbeanExporter.addExcludedBean("commitMonDataSource")
        return dataSource
    }

    @Bean
    @Primary
    fun commitMonEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(commitMonDataSource())
            .packages("com.commitmon.domain.entity")
            .persistenceUnit("commitMonPersistenceUnit")
            .properties(getVendorProperties(commitMonDataSource()))
            .build()
    }

    private fun getVendorProperties(dataSource: DataSource): Map<String, String> {
        return commitMonJpaProperties().properties
    }

    @Bean(name = ["commitMonJdbcTemplate"])
    fun commitMonJdbcTemplate(@Qualifier("commitMonDataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    fun commitMonTransactionManager(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(commitMonEntityManagerFactory(builder).getObject()!!)
    }

    @Bean
    @Primary
    fun commitMonTransactionTemplate(builder: EntityManagerFactoryBuilder): TransactionTemplate {
        return TransactionTemplate(commitMonTransactionManager(builder))
    }
}