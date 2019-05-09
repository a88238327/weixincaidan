package test;

import entity.QR_info;
import entity.Util;
import entity.getToken;
import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
		String cardID="pqnJv0tXYqeYgyYJe5_9R_mp0KYg";
		QR_info qr_info=new QR_info(cardID, "17508910598");
		JSONObject jsonObject = JSONObject.fromObject(qr_info);
		System.out.println(jsonObject);
		String jsonStr="{\"action_name\": \"QR_CARD\",\"action_info\": {\"card\": "+jsonObject.toString()+"}}";
		System.out.println(jsonStr);
		String url = "https://api.weixin.qq.com/card/qrcode/create?access_token=TOKEN";
		url = url.replace("TOKEN", getToken.get_ACCESSTOKEN());
		//发送请求
		String result = Util.post(url, jsonStr);
		JSONObject json=JSONObject.fromObject(result);
		result=json.getString("show_qrcode_url");
		result=result.replace("\\", "");
		System.out.println(result);
	}
}
