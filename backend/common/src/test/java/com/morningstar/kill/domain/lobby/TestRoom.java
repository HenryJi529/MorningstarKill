package com.morningstar.kill.domain.lobby;

import com.morningstar.kill.pojo.entity.User;
import org.junit.jupiter.api.Test;

public class TestRoom {
    @Test
    public void test() {
        User user1 = new User("小白");
        User user2 = new User( "小红");
        User user3 = new User("小黄");
        user1.createRoom();
        user1.inviteUser(user2);
        assert user1.getRoom().getMemberCount() == 2;
        user1.inviteUser(user3);
        assert user1.getRoom().getMemberCount() == 3;
        assert user1.getRoom() == user2.getRoom();
        assert user1.getRoom().getOwner() == user1;
        user1.exitRoom();
        assert user2.getRoom().getOwner() == user2;
        user2.closeRoom();
        assert user3.getRoom() == null;
    }
}
