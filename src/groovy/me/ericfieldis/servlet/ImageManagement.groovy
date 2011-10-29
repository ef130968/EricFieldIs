package me.ericfieldis.servlet

import ericfieldis.entity.user.User

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.web.multipart.MultipartFile
import org.grails.plugins.imagetools.ImageTool

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 27/10/11
 * Time: 8:19 PM
 * To change this template use File | Settings | File Templates.
 */

class ImageManagement {
    def boolean deleteImageFromDisk(String name, String sourceDirectory) {
        def servletContext = ServletContextHolder.servletContext
        def storagePath = servletContext.getRealPath(sourceDirectory)
        File file = new File("${storagePath}/${name}")
        if(!file.exists()) return true
        boolean fileSuccessfullyDeleted = file.delete()
        if(!fileSuccessfullyDeleted) {
            println "Unable to delete file: ${storagePath}/${name}"
        }
        fileSuccessfullyDeleted
    }

    def String uploadImageToDisk(MultipartFile multipartFile, String name, String destinationDirectory) {
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
            try {
                def imageTool = new ImageTool()
                imageTool.load(multipartFile.getBytes())
                imageTool.saveOriginal()
                String imageType = null
                int index = name.lastIndexOf('.')
                switch (name.substring(index + 1)) {
                    case 'jpg':
                    case 'jpeg':
                        imageType = 'JPEG'
                        break
                    case 'png':
                        imageType = 'PNG'
                        break
                    default:
                        break
                }
                if(imageType) {
                    imageTool.thumbnail(190)
                    imageTool.writeResult("${storagePath}/${name}", imageType)
                } else {
                    File file = new File("${storagePath}/${name}")
                    if(deleteImageFromDisk(name, destinationDirectory)) {
                        multipartFile.transferTo(file)
                        println "Saved file: ${storagePath}/${name}"
                    } else {
                        println "Unable to delete file: ${storagePath}/${name}"
                        return null
                    }
                }
            } catch (Exception e) {
                println e
            }
            return "${storagePath}/${name}"
        } else {
            println "File ${multipartFile.inspect()} was empty!"
            return null
        }
    }

    def boolean uploadImageToDB(MultipartFile avatarFile, User userInstance) {

        String imageMimeType
        int index = avatarFile.originalFilename.lastIndexOf('.')
        switch (avatarFile.originalFilename.substring(index + 1)) {
            case 'png':
                imageMimeType = 'image/png'
                break
            case 'gif':
                imageMimeType = 'image/gif'
                break
            default:
                imageMimeType = 'image/jpeg'
                break
        }
        userInstance.avatarMimeType = imageMimeType
        userInstance.avatarImage = avatarFile.bytes // avatar is the image blob field of the record
        !userInstance.hasErrors() && userInstance.save(flush: true)
    }

    def void retrieveImageThumbnailFromDB(def userId, int maxSizeInPixels) {
        try {
            def userInstance = User.get(userId)
            assert userInstance != null
            def imageTool = new org.grails.plugins.imagetools.ImageTool()
            imageTool.load(userInstance?.avatarImage)
            try {
                //TODO - Eric - Figure out why small .ico files through exception here but code continues anyway
                imageTool.thumbnail(maxSizeInPixels)
                def imageType = 'JPEG'
                if(userInstance.avatarMimeType == 'image/png') imageType = 'PNG'
                if(userInstance.avatarMimeType == 'image/gif') imageType = 'GIF'
                try {
                    def thumbImage = imageTool.getBytes(imageType)
                    response.contentType = userInstance.avatarMimeType
                    response.contentLength = thumbImage.length
                    response.outputStream.write(thumbImage)
                } catch (Exception e) {
                    retrieveImageFromDB(userId)
                }
            } catch (Exception e) {
                retrieveImageFromDB(userId)
            }
        } catch (Exception e) {
            println(e)
        }
    }

    def void retrieveImageFromDB(def userId) {
        def userInstance = User.get(userId)
        response.contentType = userInstance.avatarMimeType
        response.contentLength = userInstance?.avatarImage?.length
        response.outputStream.write(userInstance?.avatarImage)
    }
}
