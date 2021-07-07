package com.shasha.soapapis.config;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServiceConfig implements CallbackHandler {


    private static final Logger log = LoggerFactory.getLogger(WebServiceConfig.class);


    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];

        log.info("Identifier: " + callback.getIdentifier());

        // you won't be able to retrieve the password using callback.getPassword().
        // to authenticate a user, you'll need to set the password tied to the user.
        // user credentials are typically retrieved from DB or your own authentication source.
        // if the password set here is the same as the password passed by caller, authentication is successful.
        callback.setPassword("ADMX");

    }
}
