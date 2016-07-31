package com.example.dongjun.ottoee;

import com.squareup.otto.Bus;

/**
 * Created by dongjun on 2016-07-31.
 */
public class BusStation  {

    private static final Bus bus = new Bus();

    public static Bus getInstance(){
        return bus;
    }

    private BusStation(){

    }


}
