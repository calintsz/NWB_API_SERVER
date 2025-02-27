package kr.co.npc.nwb.service;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.npc.nwb.core.ApiRequestTemplate;
import kr.co.npc.nwb.core.JedisHelper;

@Service("tokenVerify")
@Scope("prototype")
public class TokenVerify extends ApiRequestTemplate {
    private static final JedisHelper helper = JedisHelper.getInstance();

    public TokenVerify(Map<String, String> reqData) {
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
                this.apiResult.addProperty("resultCode", "200");
                this.apiResult.addProperty("message", "Success");
                this.apiResult.add("issueDate", token.get("issueDate"));
                this.apiResult.add("userGwId", token.get("userGwId"));
                this.apiResult.add("userSysId", token.get("userSysId"));
                this.apiResult.add("userNm1", token.get("userNm1"));
            }
            
            helper.returnResource(jedis);
        }
        catch (Exception e) {
            helper.returnResource(jedis);
        }
    }
}
