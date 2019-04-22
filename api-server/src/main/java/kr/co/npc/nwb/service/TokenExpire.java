package kr.co.npc.nwb.service;

import java.util.HashMap;
import java.util.Map;

//import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.npc.nwb.core.ApiRequestTemplate;
import kr.co.npc.nwb.core.JedisHelper;
import kr.co.npc.nwb.core.KeyMaker;
import kr.co.npc.nwb.service.dao.TokenKey;
import redis.clients.jedis.Jedis;

@Service("tokenExpire")
@Scope("prototype")
public class TokenExpire extends ApiRequestTemplate {
    private static final JedisHelper helper = JedisHelper.getInstance();
    @Autowired
    //@Resource(name="sqlSession")
    private SqlSession sqlSession;
    
    public TokenExpire(Map<String, String> reqData) {
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
        if (StringUtils.isEmpty(this.reqData.get("token"))) {
            throw new RequestParamException("token이 없습니다.");
        }
    }

    @Override
    public void service() throws ServiceException {
        Jedis jedis = null;
        try {
            String userSysId = null;
            String userGwId = null;
            jedis = helper.getConnection();
            
            String tokenString = jedis.get(this.reqData.get("token"));

            if (tokenString == null) 
            {
                this.apiResult.addProperty("resultCode", "404");
                this.apiResult.addProperty("message", "Fail");
            }
            else 
            {
                Gson gson = new Gson();
                JsonObject token = gson.fromJson(tokenString, JsonObject.class);

                // helper.
                //this.apiResult.addProperty("resultCode", "200");
                //this.apiResult.addProperty("message", "Success");
                //this.apiResult.add("issueDate", token.get("issueDate"));
                //this.apiResult.add("userId", token.get("userId"));
                //this.apiResult.add("userNm1", token.get("userNm1"));

                userSysId =  token.get("userSysId").toString();
                userGwId =  token.get("userGwId").toString();
                
                if (userSysId == null)
                {
                    this.apiResult.addProperty("resultCode", "404");
                    this.apiResult.addProperty("message", "Fail");
                }
                else                    
                {
                 // token 저장.
                    jedis = helper.getConnection();
                    long result = jedis.del(this.reqData.get("token"));
//                    System.out.println(result);
                    
                    long issueDate = System.currentTimeMillis() / 1000;
                    // token 저장.
                    KeyMaker tokenKey = new TokenKey(this.reqData.get("token"),this.reqData.get("token"), issueDate);
                    
                    // changePwd 
                    HashMap<String,String> datas = new HashMap<String,String>();
                    datas.put("userSysId", userSysId);
                    datas.put("userGwId", userGwId);
                    datas.put("token",tokenKey.getKey());

                    // helper.
                    this.apiResult.addProperty("resultCode", "200");
                    this.apiResult.addProperty("message", "Success");
                    this.apiResult.addProperty("token", this.reqData.get("token"));
                }
            } 
            
            helper.returnResource(jedis);
        }
        catch (Exception e) {
            helper.returnResource(jedis);
        }
        
    }
}
