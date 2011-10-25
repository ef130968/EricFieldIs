package ericfieldis

import org.springframework.web.multipart.MultipartFile
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ericfieldis.entity.user.User

class FileUploadService {

    static transactional = true

    def String uploadFileToDisk(MultipartFile multipartFile, String name, String destinationDirectory) {
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(destinationDirectory)

        def storagePathDirectory = new File(storagePath)
        if(!storagePathDirectory.exists()) {
            print "CREATING DIRECTORY ${storagePath}: "
            if(storagePathDirectory.mkdirs()) {
                println "SUCCESS"
            } else {
                println "FAILED"
            }
        }

        if(!multipartFile.isEmpty()) {
            File file = new File("${storagePath}/${name}")
            boolean fileSuccessfullyDeleted = true
            if(file.exists()) {
                fileSuccessfullyDeleted = file.delete()
            }
            if(fileSuccessfullyDeleted) {
                multipartFile.transferTo(file)

                println "Saved file: ${storagePath}/${name}"
                return "${storagePath}/${name}"
            } else {
                println "Unable to delete file: ${storagePath}/${name}"
                return null
            }
        } else {
            println "File ${multipartFile.inspect()} was empty!"
            return null
        }
    }

    def void uploadFileToDB(CommonsMultipartFile avatarFile, User userInstance) {

        userInstance.avatar = avatarFile.bytes // avatar is the image blob field of the record
        userInstance.save() // create the record
    }
}
