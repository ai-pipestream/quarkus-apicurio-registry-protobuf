package io.quarkus.apicurio.registry.protobuf;

import com.google.protobuf.Message;
import org.jboss.logging.Logger;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A key extractor that generates random UUIDs for each message.
 * <p>
 * <b>WARNING: This extractor should NOT be used in production!</b>
 * <p>
 * Random UUIDs break several important Kafka patterns:
 * <ul>
 *   <li><b>Idempotency</b> - Replaying the same message generates a different key</li>
 *   <li><b>Ordering</b> - Related messages may land on different partitions</li>
 *   <li><b>Compaction</b> - Log compaction becomes meaningless</li>
 *   <li><b>Deduplication</b> - Cannot detect duplicate messages</li>
 * </ul>
 * <p>
 * Instead, derive your UUID from:
 * <ul>
 *   <li>A natural business key (order ID, customer ID, etc.)</li>
 *   <li>A deterministic hash (UUIDv5 from content)</li>
 *   <li>An external system's identifier</li>
 * </ul>
 * <p>
 * This class exists for:
 * <ul>
 *   <li>Quick prototyping and testing</li>
 *   <li>Fire-and-forget events where replay doesn't matter</li>
 *   <li>Demonstrating what NOT to do in production</li>
 * </ul>
 *
 * @param <T> the Protobuf message type
 * @see UuidKeyExtractor
 */
public class RandomUuidKeyExtractor<T extends Message> implements UuidKeyExtractor<T> {

    private static final Logger LOG = Logger.getLogger(RandomUuidKeyExtractor.class);

    private static final AtomicBoolean WARNING_LOGGED = new AtomicBoolean(false);

    /**
     * Creates a new random UUID extractor.
     * <p>
     * Logs a warning on first use reminding developers not to use this in production.
     */
    public RandomUuidKeyExtractor() {
        logWarningOnce();
    }

    private void logWarningOnce() {
        if (WARNING_LOGGED.compareAndSet(false, true)) {
            LOG.warn("=".repeat(80));
            LOG.warn("RandomUuidKeyExtractor is being used!");
            LOG.warn("This generates random UUIDs which breaks idempotency and replay.");
            LOG.warn("DO NOT USE IN PRODUCTION - implement UuidKeyExtractor for your message type.");
            LOG.warn("=".repeat(80));
        }
    }

    /**
     * Generates a random UUID for the message.
     * <p>
     * The message content is ignored - a new random UUID is generated each time.
     *
     * @param message the Protobuf message (ignored)
     * @return a random UUID
     */
    @Override
    public UUID extractKey(T message) {
        return UUID.randomUUID();
    }

    /**
     * Creates a RandomUuidKeyExtractor for any message type.
     * <p>
     * Convenience factory method for type inference.
     *
     * @param <T> the Protobuf message type
     * @return a new RandomUuidKeyExtractor
     */
    public static <T extends Message> RandomUuidKeyExtractor<T> create() {
        return new RandomUuidKeyExtractor<>();
    }
}
