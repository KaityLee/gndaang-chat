package com.kt.java.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kt.java.board.vo.Items;


@Controller
@RequestMapping("/board")
public class BoardController 
{
	 @Autowired
	 public BoardService svc;
	 
	 @GetMapping("/")
	 public String itemList() 
	 {
		 return "board/board_list";
	 }
	 
	 @GetMapping("/upload")
	 public String getForm() 
	 {
		 return "board/upload_form";
	 }
	 
	 @GetMapping("/item")
	 public String detail(@RequestParam int gid) 
	 {
		 Items item = svc.getItem(gid);
		 return "board/upload_form";
	 }
	   
}
