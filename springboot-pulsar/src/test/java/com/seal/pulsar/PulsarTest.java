package com.seal.pulsar;

import com.seal.pulsar.service.PulsarMessageWrapper;
import org.apache.pulsar.client.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Apache Pulsar test
 *
 * @author fengzhiqiang
 * @date 2021/5/11 15:47
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PulsarTest {
    private static final String SERVICE_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "test-topic";
    private PulsarClient client;

    @BeforeAll
    public void setUp() throws Exception {
        client = PulsarClient.builder().serviceUrl(SERVICE_URL).build();
    }

    @AfterAll
    public void tearDown() throws Exception {
        client.close();
    }

    /**
     * 生产者
     *
     * @throws Exception
     */
    @Test
    public void testProduce() throws Exception {
        Producer<byte[]> producer = client.newProducer()
                .topic(TOPIC_NAME)
                .compressionType(CompressionType.LZ4)
                .create();
        IntStream.range(1, 5).forEach(i -> {
            String content = String.format("hi-pulsar-%d", i);
            try {
                MessageId msgId = producer.send(content.getBytes());
            } catch (PulsarClientException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 消费者
     *
     * @throws Exception
     */
    @Test
    public void testSubscribe() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Consumer<byte[]> consumer = client.newConsumer()
                .topic(TOPIC_NAME)
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionName("Demo-1")
                .subscribe();
        Flux.<PulsarMessageWrapper<byte[]>>create(sink -> {
            consumer.receiveAsync().thenAccept(message -> {
                sink.next(new PulsarMessageWrapper<>(consumer, message));
            });
        }).flatMap(message -> {
            System.out.println(new String(message.getData()));
            return message.acknowledgeAsync();
        }).subscribe();
        latch.await();
    }
}
