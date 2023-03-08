package com.kt.java.board.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(of={"gid"})
public class Items 
{
	private int gid;
	private String seller;
	private String name;
	private int price;
	private String description;
	private int view;
	private Timestamp created_at;
	private List<Files> fileList = new ArrayList<>();
	
	
}
