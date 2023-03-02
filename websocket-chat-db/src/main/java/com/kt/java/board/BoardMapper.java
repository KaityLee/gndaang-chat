package com.kt.java.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kt.java.board.vo.Files;
import com.kt.java.board.vo.Items;

@Mapper
public interface BoardMapper {

	public int addItem(Items item);
	public int addmattach(List<Files> list);
	public Items getItem(int gid);
}
