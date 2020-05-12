package de.wvs.sw.api.modules.application;

import de.progme.iris.Iris;
import de.progme.iris.IrisConfig;
import de.progme.iris.config.Header;
import de.progme.iris.config.Key;
import de.progme.iris.config.Value;
import de.progme.iris.exception.IrisException;
import de.progme.thor.client.pub.PublisherFactory;
import de.progme.thor.client.sub.SubscriberFactory;
import de.wvs.sw.api.SWAPI;
import de.wvs.sw.api.SWAPIConfig;
import de.progme.thor.client.sub.Subscriber;
import de.progme.thor.client.pub.Publisher;
import de.wvs.sw.api.channel.ChannelManager;
import de.wvs.sw.api.modules.Module;
import de.wvs.sw.api.modules.application.channels.MasterChannel;
import de.wvs.sw.api.modules.application.packets.StatusPacket;
import de.wvs.sw.shared.application.Application;
import de.wvs.sw.shared.application.Deployment;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Marvin Erkes on 10.05.20.
 */
public class ApplicationModule extends Module {

    @Getter()
    private static ApplicationModule instance;

    private String deploymentUuid;

    @Getter
    private String host;
    @Getter
    private int port;

    @Getter
    private Deployment.Status status;

    private Subscriber subscriber;
    private Publisher publisher;

    private ChannelManager channelManager;

    public ApplicationModule(SWAPIConfig config) {
        super(config);

        instance = this;
    }

    public void setup() throws FileNotFoundException, IrisException {

        File config = new File("application-config.iris");
        if (!config.exists()) {
            throw new FileNotFoundException("Config not found");
        }
        IrisConfig applicationConfig = Iris.from(config)
                .def(new Header("general"), new Key("deploymentId"), new Value("0"))
                .def(new Header("thor"), new Key("host"), new Value("localhost"), new Value("1337"))
                .build();

        this.deploymentUuid = applicationConfig.getHeader("general").getKey("deploymentUuid").getValue(0).asString();
        this.host = applicationConfig.getHeader("networking").getKey("host").getValue(0).asString();
        this.port = applicationConfig.getHeader("networking").getKey("port").getValue(0).asInt();

        Key thorKey = applicationConfig.getHeader("thor").getKey("host");
        this.publisher = PublisherFactory.create(thorKey.getValue(0).asString(), thorKey.getValue(1).asInt());
        this.subscriber = SubscriberFactory.create(thorKey.getValue(0).asString(), thorKey.getValue(1).asInt(), "deployment-" + deploymentUuid);

        this.channelManager = new ChannelManager(this.publisher, this.subscriber);
        this.channelManager.subscribe(MasterChannel.class);
    }

    public void changeStatus(Deployment.Status status) {
        this.status = status;

        StatusPacket packet = new StatusPacket(this.deploymentUuid, status);
        this.channelManager.send(packet);
    }

    public void destroy() {
        this.publisher.disconnect();
        this.subscriber.disconnect();
    }
}
