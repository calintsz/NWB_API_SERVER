package kr.co.npc.nwb.service;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

//import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.google.gson.JsonObject;

import kr.co.npc.nwb.core.ApiRequestTemplate;
import kr.co.npc.nwb.core.JedisHelper;
import kr.co.npc.nwb.core.KeyMaker;
import kr.co.npc.nwb.service.dao.TokenKey;

@Service("tokenIssue")
@Scope("prototype")
public class TokenIssue extends ApiRequestTemplate {
    private static final JedisHelper helper = JedisHelper.getInstance();

    @Autowired
    //@Resource(name="sqlSession")
    private SqlSession sqlSession;

    public TokenIssue(Map<String, String> reqData) {
        super(reqData);
    }

    public void requestParamValidation() throws RequestParamException {
        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {
            throw new RequestParamException("data:"+this.reqData.toString()+"\n userId가 없습니다.");
        }

        if (StringUtils.isEmpty(this.reqData.get("userPwd"))) {
            throw new RequestParamException("data:"+this.reqData.toString()+"\n password가 없습니다.");
        }
        
        if (StringUtils.isEmpty(this.reqData.get("userSysId"))) {
            throw new RequestParamException("data:"+this.reqData.toString()+"\n userSysId가 없습니다.");
        }
    }

    public void service() throws ServiceException {
        Jedis jedis = null;
        try {
//            Map<String, Object> result = sqlSession.selectOne("users.userInfoByPassword", this.reqData);
//
//            if (result != null) {
//                final long threeHour = 60 * 60 * 3;
//                long issueDate = System.currentTimeMillis() / 1000;
//                String userGwId = String.valueOf(result.get("userGwId"));
//                String userNm1 = String.valueOf(result.get("userNm1"));
//                String userSysId = String.valueOf(this.reqData.get("userSysId"));
//                
//                // token 만들기.
//                JsonObject token = new JsonObject();
//                token.addProperty("issueDate", issueDate);
//                token.addProperty("expireDate", issueDate + threeHour);
//                token.addProperty("userGwId", userGwId);
//                token.addProperty("userSysId", userSysId);
//                token.addProperty("userNm1", userNm1);
//                
//                // token 저장.
//                KeyMaker tokenKey = new TokenKey(userGwId, userSysId, issueDate);
//                jedis = helper.getConnection();
//                jedis.setex(tokenKey.getKey(), 60 * 60 * 3, token.toString());
//                
//                // changePwd 
//                HashMap<String,String> datas = new HashMap<String,String>();                
//                datas.put("userSysId",userSysId);
//                datas.put("userGwId",userGwId);
//                datas.put("token", tokenKey.getKey());
//
//                // helper.
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.addProperty("token", tokenKey.getKey());
//            }
//            else {
//                // 데이터 없음.
//                this.apiResult.addProperty("resultCode", "404");
//            }
//            
//            helper.returnResource(jedis);
        }
        catch (Exception e) {
            helper.returnResource(jedis);
        }
    }
}
