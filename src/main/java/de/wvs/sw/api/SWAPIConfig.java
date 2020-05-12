package de.wvs.sw.api;

import lombok.Getter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marvin Erkes on 10.05.2020.
 */
public abstract class SWAPIConfig {

    @Getter
    protected String thorHost;

    @Getter
    protected int thorPort;

    public void thorHost(String host) {
        this.thorHost = host;
    }

    public void thorPort(int port) {

        this.thorPort = port;
    }
}
