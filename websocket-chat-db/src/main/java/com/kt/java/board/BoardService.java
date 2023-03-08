package com.kt.java.board;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kt.java.board.vo.ChatRoom;
import com.kt.java.board.vo.Files;
import com.kt.java.board.vo.Items;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService 
{
	@Autowired
	private BoardMapper dao;
	
	public Items getItem(int gid){
		 List<Map<String,Object>> list = dao.getItem(gid);
		   Items item = new Items();
		   
		   for(int i=0;i<list.size();i++)
		   {
			   Map<String,Object> map = list.get(i);
			   
			   BigDecimal big = (java.math.BigDecimal)map.get("GID");
			   boolean found = false;
			   
			   item.setGid(big.intValue());
			   item.setName((String) map.get("NAME"));
			   item.setSeller((String) map.get("SELLER"));
			   big = (java.math.BigDecimal)map.get("PRICE");
			   item.setPrice(big.intValue());
			   item.setDescription((String) map.get("DESCRIPTION"));
			   big = (java.math.BigDecimal)map.get("VIEW_COUNT");
			   item.setView(big.intValue());
			   item.setCreated_at((Timestamp)map.get("CREATED_AT"));
			   
			   Object objFname = map.get("FNAME");
			   if(objFname==null)
			   {
				   if(!found) continue;
			   }
				   Files file = new Files();
				   file.setFname((String) objFname);
				   big = (BigDecimal)map.get("FNUM");
				   file.setFnum(big.intValue());
				   big = (BigDecimal)map.get("FSIZE");
				   file.setFsize(big.intValue());
				   item.getFileList().add(file);	
			 }
		   
		   return item;
	}
	   
   public String upload(Map<String,Object> map)
   {
	   Path fileStroageLocation=Paths.get("./src/main/resources/static/files");
	   MultipartFile[] mfiles = (MultipartFile[]) map.get("mfiles");
	   
	   List<Files> fileList = new ArrayList<>();
	      try {
		         for(int i=0;i<mfiles.length;i++) 
		         {
		  		   if(mfiles[0].getSize()==0) continue; // 첨부파일 없을때 탈출
		  		   
		  		   Files file = new Files();
		  		   String fname = mfiles[i].getOriginalFilename();
		           file.setFname(fname);
		           
		           fileList.add(file); 
		           
		           Path targetLocation = fileStroageLocation.resolve(fname);
		           java.nio.file.Files.copy(mfiles[i].getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				
		         }
			   }catch (Exception e) {
			         e.printStackTrace();
			   }
	      log.info("뭐가문제여");
	      int saved = dao.saveItem((Items)map.get("item"));
	   if(fileList.size()!=0) 
	     {
		   	log.info("뭐가 문제냐구");
	    	 int attached = dao.saveFile(fileList);
	     }
	   log.info(Integer.toString(saved));
	      return saved>0? "저장 성공":"저장실패" ;
   }
   
	public List<ChatRoom> chatRoomList(String buyer){
		return dao.chatRoomList(buyer);
	}

}
