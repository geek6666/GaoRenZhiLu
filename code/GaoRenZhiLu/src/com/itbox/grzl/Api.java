package com.itbox.grzl;

/**
 * WebService接口地址
 * 
 * @author hyh
 */
public class Api {
	/**正式地址*/
	private static final String API_HOST   = "http://115.28.105.82:8006/";
	private static final String CODE_HOST  = "http://whoyao.com/checkcode.ashx?key=android_remark";
	private static final String IMAGE_HOST = "http://image.whoyao.net/";
	
//	/**测试  接口地址*/
//	private static final String Api_d = "http://api.d.whoyao.com/";
//	private static final String Api_r = "http://api.r.whoyao.com/";
//	private static final String Api_h = "http://10.10.1.137:40008/";
//	private static final String Api_c = "http://10.10.1.155:82/";
//	/**测试  验证码地址*/
//	private static final String Code_d = "http://d.whoyao.com/checkcode.ashx?key=android_remark";
//	private static final String Code_r = "http://r.whoyao.com/checkcode.ashx?key=android_remark";
//	private static final String Code_h = "http://10.10.1.137/checkcode.ashx?key=android_remark";
//	//	211.100.49.56
//	/**测试  图片地址*/
//	private static final String Image_d = "http://image.d.whoyao.com/";
//	private static final String Image_r = "http://image.r.whoyao.com/";
//	
//	/**接口地址*/
//	public static final String Api_Addr = Api_d;
//	/**图片地址*/
//	public static final String Image_Addr = Image_d;
//	/**验证码图片*/
//	public static final String Code_Addr = Code_d;
	
	
	/**图片尺寸*/
//	private static final String ImageDemen_60 = "/60x60.jpg";
	public static final String ImageDemen_100 = "/100x100.jpg";
	public static final String ImageDemen_120_90 = "/120x90.jpg";
	public static final String ImageDemen_240_180 = "/240x180.jpg";
	public static final String ImageDemen_0 = "/0x0.jpg";
	
	
	/**公共模块*/
 	public static final class Common {
		private static final String PartName = "common/";
		/**与系统同步时间*/
		public static final String GetServerTime =  PartName + "getsystemtime";
//		/**获取系统标签*/
//		public static final String GetSystemTag =  PartName + "getsystemtag";
//		/**测试UserAgent*/
//		public static final String TestUserAgent =  PartName + "useragent";
	}
	
	/**
	 * 用户模块
	 * @author hyh
	 */
	public static final class User {
		
		private static final String PartName = "user/";
		/**1.客户端初始化和检查更新*/
		public static final String CheckUpdate =  PartName + "mobileinfo";
		/**2.登录*/
		public static final String Login =  PartName + "login";
		/**3.注册*/
		public static final String Register =  PartName + "register";
		/**4.验证账号是否已注册*/
		public static final String CheckAccount =  PartName + "checkaccount";
		/**5.获取短信验证码*/
		public static final String SendVerifyCode =  PartName + "sendverifycode";
		/**6.校验短信验证码*/
		public static final String CheckVerifyCode =  PartName + "checkverifycode";
		/**8.邮箱找回密码*/
		public static final String ResetPwdEmail = PartName + "uppwdbyemail";
		/**9.获取用户详细信息*/
		public static final String DetailInfo = PartName + "getmyuserdetail";
		/**10.编辑个人资料*/
		public static final String UpUserInfo = PartName + "upuserdetail";
		/**12.修改个人头像*/
		public static final String UpUserFace = PartName + "upuserface";
		/**13.上传个人照片*/
		public static final String AddUserPhoto = PartName + "adduserphoto";
		/**14.删除用户照片*/
		public static final String DelUserPhoto = PartName + "deleteuserphoto";
		/**15.实名认证*/
		public static final String UpHonestyInfo = PartName + "myhonestyinfo";
		/**16.获取闲人预告*/
		public static final String GetFree = PartName + "getmyfree";
		/**17.发闲人预告*/
		public static final String AddMyFree = PartName + "addmyfree";
		/**18.修改密码*/
		public static final String ChangePassword = PartName + "uppassword";
		/**19.标签换一换*/
		public static final String GetNextTags = PartName + "nexttags";
		/**20.添加标签*/
		public static final String UpTags = PartName + "addtag";
		/**21.删除标签*/
		public static final String DeleteTag = PartName + "deleteusertag";
		/**22.获取隐私设置*/
		public static final String AddUserSafeSetting = PartName + "addusersetsafe";
		/**23.修改隐私设置*/
		public static final String UpUserSafeSetting = PartName + "usersetsafe";
		/**24.获取隐私设置*/
		public static final String GetUserSafeSetting = PartName + "getusersetsafe";
		/**25.修改条件设置*/
		public static final String UpConditionSetting = PartName + "conditionsetting";
		/**26.获取条件设置*/
		public static final String GetConditionSetting = PartName + "getconditionsetting";
		/**27.意见反馈*/
		public static final String Feedback = PartName + "addfeedback";
		/**28.修改时空设置*/
		public static final String UpUserSpacetime = PartName + "adduserspantime";
		/**29.手机找回密码*/
		public static final String RetrievePwdByPhone = PartName + "uppwdbyuserphone";
		/**30.获取时空设置*/
		public static final String GetUserSpacetime = PartName + "getuserspacetime";
		/*31.注销接口,仅ios*/
		/**32.获取实名认证信息*/
		public static final String GetHonestyInfo = PartName + "getuserhonesty";
		/**33 删除全部标签*/
		public static final String DeleteAllTag = PartName+"deletealltag";
		/*
		 * 用户模块第2部分
		 * */
		/**33:朋友列表*/
		public static final String GetUserList = PartName + "friendlist";
		/**34:朋友搜索*/
		public static final String Search = PartName + "searchfriend";
		/**35:我的好友表*/
		public static final String GetFridnds = PartName + "getfriends";
		/**36:好友管理*/
		public static final String FriendManage = PartName + "friendmanage";
		/**37:好友请求管理*/
		public static final String FriendRequestManage = PartName + "operationfriend";
		/**38:黑名单管理*/
		public static final String BlackManage = PartName + "blackmanage";
		/**39:收藏管理*/
		public static final String FaviconsManage = PartName + "faviconsmanage";
		/**40:与Ta的关系*/
		public static final String Relation = PartName + "getuserrelation";
		/**41:验证邮箱*/
		public static final String VerifyEmail = PartName + "checkemail";
		/** 42我的资金 */
		public static final String MyFund = PartName + "getusercapital";
		
		
	}

	/**活动模块接口*/
	public static final class Event{
		
		private static final String PartName = "activity/";
		
		/**1:添加活动接口*/
		public static final String add = PartName + "addactivity";
		/**2:活动搜索接口*/
		public static final String search = PartName + "searchactivity";
		/**3:参加活动接口*/
		public static final String join = PartName + "joinactivityuser";
		/**4:添加感兴趣接口*/
		public static final String interestAdd = PartName + "addactivityuserinterest";
		/**5:添加活动评论接口*/
		public static final String commentAdd = PartName + "addactivityusercomment";
		/**6:获取活动评论接口*/
		public static final String commentList = PartName + "getactivityusercomment";
		/**7:获取活动详情接口*/
		public static final String detail = PartName + "getactivityusercomment";
		/**8:我的活动接口*/
		public static final String listForUser = PartName + "getactivityusercomment";
		
	}
	
	/**在线学习模块*/
	public static final class Online{
		
		private static final String PartName = "OnLine/";
		/** 1.获取在线活动接口 */
		public static final String getOnline = PartName + "getonline";
		/** 2.添加学习统计数接口 */
		public static final String addStatistics = PartName + "addonlinestatistics";

	}

	/**支付模块*/
	public static final class Pay{
		
		private static final String PartName = "Alipay/";
//		/** 1.话题首页 */
//		public static final String TopicHome = PartName + "topiclist";

		
	}
	
	/**咨询模块*/
	public static final class Consultation{
		
		private static final String PartName = "Consultation/";
		/** 1.马上提问 */
		public static final String probleming = PartName + "probleming";
		/** 2.咨询搜索 */
		public static final String getteacher = PartName + "getteacher";
//		/** 1.话题首页 */
//		public static final String TopicHome = PartName + "topiclist";
		
		
	}
	
	private static String getHost(){		
		return API_HOST;
	}
	/**获取Api的URL地址*/
	public static String getUrl(String apiName){
		return getHost() + apiName;
	}
	
	/**获取图片的URL地址*/
//	public static String getImageUrl(String ImageID,String ImageDimen){
//		return Image_Addr + ImageID + ImageDimen;
//	}
	
	
}