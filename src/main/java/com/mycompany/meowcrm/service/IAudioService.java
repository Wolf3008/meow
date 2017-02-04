package com.mycompany.meowcrm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAudioService {

    public void getAudio(String path, HttpServletRequest request, HttpServletResponse response);

    public void getAudio(long id, HttpServletRequest request, HttpServletResponse response);
}
