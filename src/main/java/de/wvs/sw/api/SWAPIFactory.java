package de.wvs.sw.api;

/**
 * Created by Marvin Erkes on 10.05.20.
 */
public final class SWAPIFactory {

    private SWAPIFactory() {
        // Do not allow instance creation
    }

    public static SWAPI create(SWAPIConfig swApiConfig) {
        return new SWAPI(swApiConfig);
    }
}
