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
	
	public List<Map<String,Object>> boardList();
	
	public List<Map<String,Object>> getItem(int gid);
	
	public Map<String,Object> searchRoom(int gid, String buyer);
	
	public int createRoom(int gid, String buyer);
	
	public List<Map<String,Object>> chatRoomList(String seller,String buyer);

	public String findname(int roomId);

}
