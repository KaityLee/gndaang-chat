package com.kt.java.board.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= {"fnum"})
public class Files {
	private int gid;
	private int fnum;
	private String fname;
	private long fsize;


}
