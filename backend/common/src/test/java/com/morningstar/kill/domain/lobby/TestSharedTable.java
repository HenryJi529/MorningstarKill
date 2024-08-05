package com.morningstar.kill.domain.lobby;

import com.morningstar.kill.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestSharedTable {
    @Test
    public void test() {
        User user1 = new User("1.1");
        User user2 = new User("1.2");
        User user3 = new User("2.1");
        User user4 = new User("2.2");
        user1.createRoom();
        user1.inviteUser(user2);
        user3.createRoom();
        user3.inviteUser(user4);
        SharedTable table = new SharedTable(user1.getRoom(), user3.getRoom());
        log.info(table.toString());
    }
}
