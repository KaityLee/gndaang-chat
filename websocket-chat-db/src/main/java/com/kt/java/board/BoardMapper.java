package com.kt.java.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.java.board.vo.ChatRoom;
import com.kt.java.board.vo.Files;
import com.kt.java.board.vo.Items;

@Mapper
public interface BoardMapper {

	public int saveItem(Items item);
	public int saveFile(List<Files> list);
	public List<Map<String,Object>> getItem(int gid);
	public List<ChatRoom> chatRoomList(String buyer);
}
