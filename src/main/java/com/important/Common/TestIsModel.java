package com.important.Common;

public class TestIsModel {
	/**
	 * 推荐使用：包装类来定义不带is的变量
	 * 
	 * 为什么不要带is？ 
	 * Serializable Result With fastjson :{"success":true} 
	 * Serializable Result With Gson :{"isSuccess":true}
     * Serializable Result With jackson :{"success":true}
     * 
     * 不同的序列化框架得到的json内容并不相同，所以带is可能导致序列化错误
	 */
}

class Model1 {

	private Boolean isSuccess;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}

class Model2 {

	private Boolean success;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}

class Model3 {

	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}

class Model4 {

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}