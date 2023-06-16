package com.member.dispachar;

import lombok.Setter;

@Setter
public class ViewResolver {
	
	public String prefix;
	public String suffix;

	public String getView(String viewName) {
//		index => prefix + 파일명 + suffix
//		ex) "index" => "/member/" + "/index" + ".jsp"
		return prefix+viewName+suffix;
	}
}
