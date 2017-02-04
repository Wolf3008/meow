package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.service.IAudioService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/audio")
public class AudioController {

    @Autowired
    private IAudioService audioService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("id") long id,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //String name = request.getRequestURI().replace("ContactBook/app/", "");
        audioService.getAudio(id, request, response);
    }
}
