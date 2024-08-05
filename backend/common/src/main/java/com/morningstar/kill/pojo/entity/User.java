package com.morningstar.kill.pojo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Deque;
import java.util.UUID;

import com.morningstar.kill.domain.exception.UserAlreadyInRoomException;
import com.morningstar.kill.domain.exception.UserNotInRoomException;
import com.morningstar.kill.domain.lobby.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TableName: user
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    private String id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String nickname;

    private Integer sex;

    private Timestamp createTime;

    private Timestamp updateTime;


    // NOTE: 不修改直接通过自身修改room属性，避免逻辑混乱
    private Room room;

    public User(String username) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.username = username;
    }

    public void createRoom(){
        if(room != null){
            throw new UserAlreadyInRoomException();
        }
        new Room(this);
    }

    public void createRoomWithOthers(Deque<User> users){
        if(room != null){
            throw new UserAlreadyInRoomException();
        }
        new Room(this, users);
    }

    public void exitRoom(){
        if(this.room == null){
            throw new UserNotInRoomException();
        }
        this.room.removeMember(this);
    }

    public void inviteUser(User user){
        if(this.room == null){
            throw new UserNotInRoomException();
        }
        this.room.addMember(user);
    }

    public void closeRoom(){
        if(this.room == null){
            throw new UserNotInRoomException();
        }
        if(this.room.getOwner().equals(this)){
            this.room.close();
        }else{
            throw new RuntimeException("没有权限关闭房间");
        }
    }
}