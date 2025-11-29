package io.quarkus.apicurio.registry.protobuf;

import io.quarkus.apicurio.registry.protobuf.runtime.ProtobufChannelConfigSource;
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
     * Called at STATIC_INIT to configure the Protobuf channels.
     * This sets up the high-priority ConfigSource with serializer/deserializer
     * config.
     */
    public void configureProtobufChannels(Set<String> incomingChannels, Set<String> outgoingChannels) {
        ProtobufChannelConfigSource.setChannels(incomingChannels, outgoingChannels);
    }
}
