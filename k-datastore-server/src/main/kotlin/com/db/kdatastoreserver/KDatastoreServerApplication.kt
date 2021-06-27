package com.db.kdatastoreserver

import com.db.kdatastoreserver.domain.Member
import com.db.kdatastoreserver.domain.repository.MemberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate

const val DEFAULT_IMAGE: String = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"

@SpringBootApplication
class KDatastoreServerApplication {
    @Bean
    fun init(
        repository: MemberRepository,
        template: MongoTemplate
    ) = CommandLineRunner {
        repository.findByName("데데시") ?: repository.save(Member("데데시", DEFAULT_IMAGE))
    }
}

fun main(args: Array<String>) {
    runApplication<KDatastoreServerApplication>(*args)
}
