package com.kt.java.board.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="gid")
public class Items 
{
	private int gid;
	private String seller_id;
	private String name;
	private int price;
	private String description;
	private int view;
	private java.sql.Date created_at;
	private List<Files> attList = new ArrayList<>();
	
}
