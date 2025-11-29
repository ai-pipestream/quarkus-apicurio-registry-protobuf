package ai.pipestream.apicurio.registry.protobuf;

import ai.pipestream.apicurio.registry.protobuf.runtime.ProtobufChannelConfigSource;
import io.quarkus.runtime.annotations.Recorder;

import java.util.Set;

/**
 * Recorder for Apicurio Registry Protobuf configuration.
 *
 * <p>
 * This recorder runs at {@code STATIC_INIT} to configure the high-priority
 * {@code ConfigSource}
 * with the detected Protobuf channels. It ensures that the correct serializers
 * and deserializers
 * are applied before the application starts.
 * </p>
 */
@Recorder
public class ApicurioRegistryProtobufRecorder {

    /**
     * Default constructor.
     */
    public ApicurioRegistryProtobufRecorder() {
    }

    /**
     * Called at STATIC_INIT to configure the Protobuf channels.
     * This sets up the high-priority ConfigSource with serializer/deserializer
     * config.
     *
     * @param incomingChannels the set of incoming channel names to configure
     * @param outgoingChannels the set of outgoing channel names to configure
     */
    public void configureProtobufChannels(Set<String> incomingChannels, Set<String> outgoingChannels) {
        ProtobufChannelConfigSource.setChannels(incomingChannels, outgoingChannels);
    }
}
