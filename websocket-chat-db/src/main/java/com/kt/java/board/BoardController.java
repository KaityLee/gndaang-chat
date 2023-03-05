package com.kt.java.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kt.java.board.vo.ChatRoom;
import com.kt.java.board.vo.Items;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;


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
	 
	 @PostMapping("/uploadForm")
	   @ResponseBody
	   @Transactional
	   public Map<String,Object> uploadform(@RequestParam("files")MultipartFile[] mfiles,
	                     HttpServletRequest request, Items item) 
	   {
			ServletContext context = request.getServletContext();
			String savePath = context.getRealPath("/WEB-INF/files");
			Map<String, Object> map = new HashMap<>();
			map.put("savePath", savePath);
			map.put("mfiles", mfiles);
			map.put("item", item);
		 
			boolean success = svc.upload(map);
			 
			map.put("success",success);
		  
		    return map;
	   }
	 
	 @GetMapping("/item")
	 public String detail(@RequestParam int gid) 
	 {
		 Items item = svc.getItem(gid);
		 return "board/upload_form";
	 }
	 
	 @PostMapping("/chatroom")
	 public List<ChatRoom> chatRooms(String buyer)
	 {
		 return svc.chatRoomList(buyer);
	 }
	   
}
