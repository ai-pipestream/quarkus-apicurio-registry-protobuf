package io.quarkus.apicurio.registry.protobuf.deployment;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Map;
import java.util.Optional;

/**
 * Build-time configuration for the Apicurio Registry Protobuf extension.
 *
 * <p>
 * This configuration class defines properties that are available at build time,
 * primarily focusing on Dev Services configuration for Apicurio Registry.
 * </p>
 */
@ConfigMapping(prefix = "quarkus.apicurio-registry.protobuf")
@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
public interface ApicurioRegistryProtobufBuildTimeConfig {

    /**
     * Dev Services configuration.
     *
     * <p>
     * Dev Services allows Quarkus to automatically start an Apicurio Registry
     * instance
     * in dev and test modes if one is not already configured.
     * </p>
     */
    DevServicesConfig devservices();

    interface DevServicesConfig {
        /**
         * If Dev Services for Apicurio Registry has been explicitly enabled or
         * disabled.
         *
         * <p>
         * By default, Dev Services is enabled unless
         * {@code mp.messaging.connector.smallrye-kafka.apicurio.registry.url}
         * is configured.
         * </p>
         */
        Optional<Boolean> enabled();

        /**
         * The Apicurio Registry image to use.
         *
         * <p>
         * Defaults to {@code apicurio/apicurio-registry-mem:2.4.4.Final} if not
         * specified.
         * </p>
         */
        Optional<String> imageName();

        /**
         * Optional fixed port the dev service will listen to.
         *
         * <p>
         * If not defined, a random available port will be chosen.
         * </p>
         */
        Optional<Integer> port();

        /**
         * Indicates if the Apicurio Registry instance managed by Quarkus Dev Services
         * is shared.
         *
         * <p>
         * When shared, Quarkus looks for running containers using label-based service
         * discovery.
         * If a matching container is found, it is used, and a new one is not started.
         * </p>
         */
        @WithDefault("true")
        boolean shared();

        /**
         * The value of the {@code quarkus-dev-service-apicurio-registry} label.
         *
         * <p>
         * This label is used to identify the shared container.
         * </p>
         */
        @WithDefault("apicurio-registry")
        String serviceName();

        /**
         * Environment variables that are passed to the container.
         */
        Map<String, String> containerEnv();
    }
}
