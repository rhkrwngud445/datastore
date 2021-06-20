package com.db.kdatastoreserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KDatastoreServerApplication

fun main(args: Array<String>) {
    runApplication<KDatastoreServerApplication>(*args)
}
