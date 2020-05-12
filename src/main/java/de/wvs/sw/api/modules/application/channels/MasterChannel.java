package de.wvs.sw.api.modules.application.channels;

import de.progme.thor.client.sub.impl.handler.annotation.Channel;
import de.progme.thor.client.sub.impl.handler.annotation.Key;
import de.progme.thor.client.sub.impl.handler.annotation.Value;
import de.wvs.sw.api.modules.application.ApplicationModule;
import de.wvs.sw.shared.application.SWSlave;
import org.json.JSONObject;

/**
 * Created by Marvin Erkes on 11.02.20.
 */
@Channel("master")
public class MasterChannel {

    @Key("connection")
    @Value("reconnect")
    public void onConnectionReconnect(JSONObject data) {

        if (ApplicationModule.getInstance() == null) return;
        ApplicationModule.getInstance().changeStatus(ApplicationModule.getInstance().getStatus());
    }
}
