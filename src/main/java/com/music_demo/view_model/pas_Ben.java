package com.music_demo.view_model;

import java.util.Date;

public class pas_Ben {
    private final int KEY = 211;

    public int[] pas(int pas) {

        return null;
    }

    public long pas_dem(int type, int context) {
        long pas = (KEY * ((int) (Math.random() * 110) + 50)) + (100 + type * 10 + context);
        long time1 = new Date().getTime();

        return pas;

    }

}
