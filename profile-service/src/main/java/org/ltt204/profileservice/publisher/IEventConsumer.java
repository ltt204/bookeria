package org.ltt204.profileservice.publisher;

import org.ltt204.profileservice.events.consumers.UserCreatedEvent;
import org.ltt204.profileservice.events.consumers.UserDeletedEvent;
import org.ltt204.profileservice.events.consumers.UserUpdatedEvent;

public interface IEventConsumer {
    void consumeEvent(UserCreatedEvent event);

     void consumeEvent(UserUpdatedEvent event);

     void consumeEvent(UserDeletedEvent event);
}
