package de.wvs.sw.api;

import de.wvs.sw.api.modules.Module;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Marvin Erkes on 10.05.20.
 */
public class SWAPI {

    @Getter()
    private static SWAPI instance;

    @Getter
    private SWAPIConfig config;

    public SWAPI(SWAPIConfig config) {
        instance = this;
        this.config = config;
    }

    public Module initializeModule(Class<? extends Module> moduleClass) throws Exception {
        Module module = moduleClass.getConstructor(SWAPIConfig.class).newInstance(this.config);
        module.setup();
        return module;
    }
}
