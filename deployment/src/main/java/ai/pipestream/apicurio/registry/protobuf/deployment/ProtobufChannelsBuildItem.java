package ai.pipestream.apicurio.registry.protobuf.deployment;

import io.quarkus.builder.item.SimpleBuildItem;

public final class ProtobufChannelsBuildItem extends SimpleBuildItem {
    private final boolean hasChannels;

    public ProtobufChannelsBuildItem(boolean hasChannels) {
        this.hasChannels = hasChannels;
    }

    public boolean hasChannels() {
        return hasChannels;
    }
}
