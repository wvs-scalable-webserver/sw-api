package de.wvs.sw.api.modules;

import de.progme.iris.exception.IrisException;
import de.wvs.sw.api.SWAPIConfig;
import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;

/**
 * Created by Marvin Erkes on 10.05.20.
 */
@AllArgsConstructor
public abstract class Module {

    protected SWAPIConfig config;

    public abstract void setup() throws Exception;
}
