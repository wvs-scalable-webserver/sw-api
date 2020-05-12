package de.wvs.sw.api.modules.application.packets;

import com.google.gson.Gson;
import de.wvs.sw.api.channel.Packet;
import de.wvs.sw.shared.application.Deployment;
import de.wvs.sw.shared.application.SWSlave;

/**
 * Created by Marvin Erkes on 11.02.20.
 */
public class StatusPacket extends Packet {

    private static final Gson gson = new Gson();

    public StatusPacket(String deploymentUuid, Deployment.Status status) {
        super("application");

        this.data.put("deployment", "status");
        this.data.put("deploymentUuid", deploymentUuid);
        this.data.put("status", status);
    }
}
