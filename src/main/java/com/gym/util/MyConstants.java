package com.gym.util;

import java.text.SimpleDateFormat;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.httpclient.vo.ClientSecretCredential;
import com.easemob.server.example.httpclient.vo.Credential;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;


public class MyConstants {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	public static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
	
	public static JsonNodeFactory factory = new JsonNodeFactory(false);

    // 通过app的client_id和client_secret来获取app管理员token
    public static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);
    
    public static final String RESPONSE="response";
    
    public static final String NJDRRES="njdrres";
    
    public static final String SIGNIN = "signin";
    
    public static final String ZAN = "zan";
    
    public static final String REG = "reg";
    
    public static final String SCORING = "scoring";
    
    public static final String GUAGUA = "guagua";

    public static final String ADOPT = "adopt";
    
    public static final String MAXONEDAY = "maxoneday";
    
    public static final String ADVICE = "advice";
    
    public static final String ADVICEGET = "adviceget";
    
    public static final String SHARE = "share";
    
    public static final String MAXSHARE = "maxshare";
    
    public static final String PLANTINFO = "plantinfo";

    
    public static final String NZDDIC = "nzddistance";
    
    public static final String SECONDDIC = "seconddistance";
    
    public static final String RECIPECOUNT = "recipecount";
    
    public static final String PTOC="ptoc";
    
    public static final String SMS="sms";
    
    public static final String GUANUM="guanum";
    
    public static final String POINTSDES="pointsdes";

    public static final String HAOPING="好评";
    
    public static final String ZHONGPING="中评";
    
    public static final String CHAPING="差评";
    
}
