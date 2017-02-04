package com.mycompany.meowcrm.service;

import java.util.List;

public interface ICallService {

    public void call(String number, String context);

    public List usersOnline();

    public void hangUp();

    public void redirectCurr(String number);
}
