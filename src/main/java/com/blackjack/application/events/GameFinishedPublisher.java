package com.blackjack.application.events;

import com.blackjack.domain.events.GameFinishedEvent;

public interface GameFinishedPublisher {

    void publish(GameFinishedEvent event);

}
