## net-queue
net-queue is lite library wrapper for RabbitMQ amqp-client.
## Prerequirements

Download and install RabbitMQ.

[https://www.rabbitmq.com/download.html](http://)

(Optional) Enable management plugin that provides HTTP-based UI.

[https://www.rabbitmq.com/management.html](http://)

## Building the code

The project requires Maven 3. Some example commands follow:

`mvn package`

After building the module, binary distribution assembliy can be found at:

`target/net-queue-1.0-SNAPSHOT.jar`

## Examples

All Example code is available at:

`/src/main/java/ml/mlazic/netqueue/examples`

### One-to-one Postman example

`/src/main/java/ml/mlazic/netqueue/examples/PostmanSender.java`

```
QueueBuilder builder = new QueueBuilder();
        builder.setHost("localhost")
                .setPort(5672)
                .setUsername("guest")
                .setPassword("guest")
                .setQueueName("postman.queue")
                .setType(QueueType.POSTMAN);

        NetQueue queue = new NetQueue(builder);

        queue.send(new String("Hello Postman!"));

        queue.close();
```

`/src/main/java/ml/mlazic/netqueue/examples/PostmanReceiver.java`

```
QueueBuilder builder = new QueueBuilder();
        builder.setQueueName("postman.queue")
                .setType(QueueType.POSTMAN);

        NetQueue queue = new NetQueue(builder);

        queue.receive();

        queue.close();

```

