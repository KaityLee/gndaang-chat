package com.kt.java.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.java.board.vo.Items;

@Service
public class BoardService 
{
	@Autowired
	private BoardMapper dao;
	
	public Items getItem(int gid){
		Items item = dao.getItem(gid);
		return item;
		
	}

}
