package test;

import entity.Util;
import entity.getToken;

public class test1 {

	public static void main(String[] args) {
		String urlString="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
		String ttString=Util.get(urlString.replace("ACCESS_TOKEN", getToken.get_ACCESSTOKEN()));
		System.out.println(ttString);
	}
}
