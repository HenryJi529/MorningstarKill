package com.morningstar.kill.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TableName: user_record
 */
@Data
@NoArgsConstructor
public class UserRecord implements Serializable {
    private String userId;

    private String recordId;
}