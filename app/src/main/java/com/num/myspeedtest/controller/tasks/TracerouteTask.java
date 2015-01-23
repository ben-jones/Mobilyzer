package com.num.myspeedtest.controller.tasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.num.myspeedtest.controller.utils.PingUtil;
import com.num.myspeedtest.model.Traceroute;

import java.util.HashMap;

public class TracerouteTask implements Runnable {

    private String address;
    private int index;
    private Handler handler;

    public TracerouteTask(String address, int index, Handler handler){
        this.address = address;
        this.index = index;
        this.handler = handler;
    }

    @Override
    public void run(){
        HashMap<String, String> params = new HashMap<>();
        params.put("-c", "1");
        params.put("-t", index+"");
        Traceroute traceroute = PingUtil.traceroute(address, params);

        Bundle bundle = new Bundle();
        bundle.putParcelable("traceroute", traceroute);

        Message msg = new Message();
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
