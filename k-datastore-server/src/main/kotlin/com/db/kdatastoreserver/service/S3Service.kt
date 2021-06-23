package com.db.kdatastoreserver.service

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.annotation.PostConstruct

const val EXTENSION_DELIMITER = "."

@Service
class S3Service {

    lateinit var s3Client: AmazonS3

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    @Value("\${cloud.aws.credentials.accessKey}")
    lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secretKey}")
    lateinit var secretKey: String

    @PostConstruct
    fun s3Client() {
        val credentials: AWSCredentials = BasicAWSCredentials(accessKey, secretKey)

        s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.AP_NORTHEAST_2)
            .build()
    }

    fun upload(file: MultipartFile?): String {
        val originalFileName = Optional.ofNullable(file?.originalFilename)
            .filter { it.isNotEmpty() }
            .orElse(file?.name)
        val extension = findExtension(originalFileName)
        val fileName = randomFileNameWithExtension(extension)
        val request = PutObjectRequest(bucket, fileName, file?.inputStream, null)
            .withCannedAcl(CannedAccessControlList.PublicRead)
        s3Client.putObject(request)

        return fileName
    }

    fun retrieve(fileName: String) = s3Client.getUrl(bucket, fileName).toString()

    private fun findExtension(originalFileName: String): String {
        val position = originalFileName.lastIndexOf(EXTENSION_DELIMITER)
        return originalFileName.substring(position)
    }

    private fun randomFileNameWithExtension(extension: String) = UUID.randomUUID().toString().plus(extension)
}