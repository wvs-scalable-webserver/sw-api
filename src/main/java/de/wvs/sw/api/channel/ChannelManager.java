package de.wvs.sw.api.channel;

import de.progme.thor.client.pub.Publisher;
import de.progme.thor.client.sub.Subscriber;
import lombok.AllArgsConstructor;

/**
 * Created by Marvin Erkes on 11.02.20.
 */
@AllArgsConstructor
public class ChannelManager {

    private final Publisher publisher;
    private final Subscriber subscriber;

    public void subscribe(Class clazz) {
        this.subscriber.subscribeMulti(clazz);
    }

    public void send(Packet packet) {
        this.publisher.publish(packet.getChannel(), packet.getData());
    }
}
