package com.morningstar.kill.pojo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TableName: record
 */
@Data
@NoArgsConstructor
public class Record implements Serializable {
    private String id;

    private String content;

    private Timestamp createTime;

    private Timestamp updateTime;
}