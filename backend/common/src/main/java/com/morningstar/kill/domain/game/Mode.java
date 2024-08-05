package com.morningstar.kill.domain.game;

import com.morningstar.kill.domain.game.game.*;
import lombok.Getter;

/**
 * 游戏模式
 */
@Getter
public enum Mode {
    IDENTITY("身份场", 4, 10, IdentityGame.class),
    ONE_VS_ONE("1V1(主副将)", 2, 2, SoloGame.class),
    TWO_VS_TWO("2V2", 4, 4, DoublesGame.class),
    THREE_VS_THREE("3V3", 6, 6, TriplesGame.class),
    NATION("国战", 2, 10, NationGame.class);

    private final String name;
    private final int minHeadNum;
    private final int maxHeadNum;
    private final Class<? extends Game> GameClass;

    Mode(String name, int minHeadNum, int maxHeadNum, Class<? extends Game> GameClass) {
        this.name = name;
        this.minHeadNum = minHeadNum;
        this.maxHeadNum = maxHeadNum;
        this.GameClass = GameClass;
    }

    @Override
    public String toString() {
        return name;
    }
}
