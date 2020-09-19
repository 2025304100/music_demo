package com.music_demo.inform.performera;

import com.music_demo.inform.execute.emailPush;
import com.music_demo.inform.middle.Email;
import com.music_demo.inform.performera.imp.agency;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class agencyEmail implements agency {
    private final int MAXTHEAD = 5;
    private Logger log = Logger.getLogger("com.music_demo.inform.performera.agencyEmail.class");

    @Override
    public void init() {
        ExecutorService fixed = Executors.newFixedThreadPool(MAXTHEAD);
        emailPush emailPush = new emailPush();

        for (int i = 0; i < 7; i++) {
            Future<Object> submit = fixed.submit(() -> {

                while (true) {
                    Map<String, String> stringStringMap = emailPush.aisle().sendEmail();
                    try {
                        Email email = new Email();
                        email.testSimpleTextMail(stringStringMap);
                    } catch (Exception e) {
                        log.info("com.music_demo.inform.performera.agencyEmail.init异常:"
                                + e.getLocalizedMessage());
                    }
                }
            });

        }
    }
}
