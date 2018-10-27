package com.texttutti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class RetrieveFileController {

    @GetMapping("/retrieve/{fileName}")
    public String retrieveFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {

        try {
            final String canonicalPath = new File(".").getCanonicalPath();
            File file = new File(canonicalPath + "//scores//" + fileName);
            final FileInputStream fileInputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            final ServletOutputStream outputStream = response.getOutputStream();
            byte[] bufferData = new byte[1024];
            int read=0;
            while((read = fileInputStream.read(bufferData))!= -1){
                outputStream.write(bufferData, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File does not exist");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't get output stream");
        }
        return "Hopefully you got it!";
    }

}
