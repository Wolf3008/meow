package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.util.MultipartFileSender;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudioService implements IAudioService {

    @Autowired
    private ICdrService cdrService;

    @Override
    public void getAudio(String path, HttpServletRequest request, HttpServletResponse response) {

        try {
            //ServletContext context = request.getServletContext();
            String root = System.getProperty("catalina.home");
            Logger.getLogger(AudioService.class.getName()).log(Level.INFO, "\nPath - >" + root + path);

            File downloadFile = new File(root + path);

            MultipartFileSender.fromFile(downloadFile)
                    .with(request)
                    .with(response)
                    .serveResource();

        } catch (IOException ex) {
            Logger.getLogger(AudioService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AudioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void getAudio(long id, HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = cdrService.getFileNameById(id);
            String root = System.getProperty("catalina.home") + "/audio/";
            Logger.getLogger(AudioService.class.getName()).log(Level.INFO, "\nPath - >" + root + path);

            File downloadFile = new File(root + path);

            MultipartFileSender.fromFile(downloadFile)
                    .with(request)
                    .with(response)
                    .serveResource();

        } catch (IOException ex) {
            Logger.getLogger(AudioService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AudioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
