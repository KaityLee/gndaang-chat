package com.kt.java.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController 
{
	 @Autowired
	 public BoardService svc;
	 
	 @Autowired
	 public HttpSession session;
	 
	 @GetMapping("/list")
	 public String itemList(Model m) 
	 {
		 m.addAttribute("itemList",svc.boardList());
		 return "board/board_list";
	 }
	 
	 @GetMapping("/upload")
	 public String getForm() 
	 {
		 return "board/upload_form";
	 }
	 
	@PostMapping("/upload")
	@ResponseBody
	public Map<String,Object> uploadform(@RequestParam("files")MultipartFile[] mfiles,
			HttpServletRequest request, Items item) 
	{
		ServletContext context = request.getServletContext();
		Map<String, Object> map = new HashMap<>();
		map.put("mfiles", mfiles);
		map.put("item", item);
		
		log.info(item.toString()+mfiles.toString());
		String msg = svc.upload(map);
			 
		map.put("msg",msg);
			  
		return map;
	}
	 
	 @GetMapping("/item")
	 public String detail(@RequestParam int gid, Model m) 
	 {
		 m.addAttribute("item",svc.getItem(gid));
		 return "board/product";
	 }
	 
	 @GetMapping("/searchRoom")
	 public String searchRoom(@RequestParam int gid,Model m)
	 {
		 String buyer = (String) session.getAttribute("username");
		 int roomId = svc.searchRoom(gid,buyer);
		 if(roomId==0) {
			 roomId= svc.createRoom(gid,buyer);
		 }
		 m.addAttribute("roomId",roomId);
		 m.addAttribute("name",svc.findname(roomId));
		 
		 return "board/chat";
	 }
	 
	 @PostMapping("/enter_room")
	 public String enterRoom(int roomId, Model m)
	 {
		 m.addAttribute("name",svc.findname(roomId));
		 m.addAttribute("roomId",roomId);
		 return "board/chat";
	 }
	 
	 @GetMapping("/chatroom")
	 public String chatRooms(Model m)
	 {
		 String user = (String) session.getAttribute("username");
		 
		 m.addAttribute("chatroom",svc.chatRoomList(user));
		 
		 return "board/chatroom";
	 }
	   
}
