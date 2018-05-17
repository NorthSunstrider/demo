package com.northsunstrider.test;

import java.util.List;

import com.northsunstrider.entity.PasswordInfo;
import com.northsunstrider.service.SystemDataService;

public class Test {
	private static SystemDataService sds = new SystemDataService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<PasswordInfo> passwordInfos = getPasswordInfos();
		for (PasswordInfo passwordInfo : passwordInfos)
			System.out.println(passwordInfo.getUsername());
	}

	public static List<PasswordInfo> getPasswordInfos() {
		return sds.getPasswordInfos();
	}
}
