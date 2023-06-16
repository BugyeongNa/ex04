package com.member.dispachar;

import java.util.HashMap;
import java.util.Map;

import com.member.dispachar.controller.MemberInsertController;
import com.member.dispachar.controller.MemberRegisterController;
import com.member.dispachar.controller.MemberRemoveController;
import com.member.dispachar.controller.MemberUpdateController;
import com.member.dispachar.controller.MemberViewController;
import com.member.dispachar.controller.MemberListController;
import com.member.dispachar.controller.MemberModifyController;

public class HandlerMapping {
	
//	Controller를 구현한 객체들을 저장하는Map
	private Map<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<>();
		
//		mappings.put("/", new TestUrlMappingTest());
		mappings.put("/tmemInsert.do", new MemberInsertController());
		mappings.put("/tmemList.do", new MemberListController());
		mappings.put("/tmemModify.do", new MemberModifyController());
		mappings.put("/tmemRegister.do", new MemberRegisterController());
		mappings.put("/tmemRemove.do", new MemberRemoveController());
		mappings.put("/tmemUpdate.do", new MemberUpdateController());
		mappings.put("/tmemView.do", new MemberViewController());
//		mappings.put("/register.do", new MemberRegisterController());
		
	}
	public Controller getController(String path) {
//		Map에 등록된 Controller들 중에서 특정 경로에 해당하는 Controller 반환
//		url: Controller => 1:1 맵핑
		return mappings.get(path);
	}
	
}
