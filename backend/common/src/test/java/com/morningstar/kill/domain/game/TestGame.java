package com.morningstar.kill.domain.game;

import com.morningstar.kill.domain.exception.GameModeNotSatisfiedByTableMemberCountException;
import com.morningstar.kill.domain.exception.GameModeNotSupportedByTableTypeException;
import com.morningstar.kill.domain.lobby.PrivateTable;
import com.morningstar.kill.domain.lobby.Room;
import com.morningstar.kill.domain.lobby.SharedTable;
import com.morningstar.kill.domain.lobby.Table;
import com.morningstar.kill.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class TestGame {
    @Test
    public void test() {
        Room room1 = new Room(new User("A"), new LinkedList<>(List.of(new User("B"))));
        Room room2 = new Room(new User("C"), new LinkedList<>(List.of(new User("D"))));
        Room room3 = new Room(new User("E"), new LinkedList<>(List.of(new User("F"), new User("G"))));
        Room room4 = new Room(new User("H"), new LinkedList<>(List.of(new User("I"), new User("J"))));
        Table table1 = new PrivateTable(room1);
        Table table2 = new SharedTable(room1, room2);
        Table table3 = new SharedTable(room3, room4);
        log.info("两人的房间可以玩1V1: {}", Game.createGame(table1, Mode.ONE_VS_ONE).toString());
        try{
            Game.createGame(table1, Mode.TWO_VS_TWO);
        }catch (GameModeNotSatisfiedByTableMemberCountException e){
            log.info("两人的房间玩不了2V2");
        }

        log.info("两个两人的房间可以玩2V2: {}", Game.createGame(table2, Mode.TWO_VS_TWO).toString());

        try{
            Game.createGame(table3, Mode.IDENTITY);
        }catch (GameModeNotSupportedByTableTypeException e){
            log.info("即便人数够，SharedRoom也不能玩国战");
        }
    }
}
