package com.kt.java.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatRoom 
{
	private int rid;
	private int gid;
	private String buyer;
	private Timestamp created_at;

}
