package kr.co.npc.nwb.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.npc.nwb.core.ApiRequestTemplate;
import kr.co.npc.nwb.core.KeyMaker;
import kr.co.npc.nwb.service.dao.TokenKey;

@Service("nwbInfo")
@Qualifier()
//@Qualifier("sqlSessionTemplate")
@Scope("prototype")
public class NWBInfo extends ApiRequestTemplate {
    @Autowired
    //@Resource(name="sqlSession")        XX
    private SqlSession sqlSession;


    public NWBInfo(Map<String, String> reqData) {
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("user_id"))) {
//            throw new RequestParamException("유저 아이디가 없습니다.");
//        }
    }

    @Override
    public void service() throws ServiceException {
        // 입력 email 사용자의 이메일을 HTTP header에 입력한다.
        // 출력 resultCode API 처리 결과코드를 돌려준다. API 처리 결과가 정상이면 결과코드는 200이다.
        // 출력 message API 처리 결과 메시지를 돌려준다. API의 처리결과가 정상일 때는 Success 메시지를 돌려주며
        // 나머지 정상이 아닐 때는 오류 메시지를 돌려준다.
        // 출력 userNo 입력된 이메일에 해당하는 사용자의 사용자 번호를 돌려준다.

        nwbInfo(sqlSession);
    }
    public void nwbInfo(SqlSession sqlSession)
    {        
        if ( StringUtils.isEmpty(this.reqData.get("nwb_id")) ) 
        {
            nwbInfos(sqlSession, (List<Object>) sqlSession.selectList("nwbs.nwbInfos", this.reqData));
        }
        else
        {
            nwbInfo(sqlSession, (Map<String, Object>) sqlSession.selectOne("nwbs.nwbInfoByNwbId", this.reqData));
        }       
    }
    public void nwbInfo(SqlSession sqlSession, Map<String, Object> result)
    {
        if (result != null) 
        {
            JsonObject nwbInfo = null;
            //result  
            Map<String, Object> data = result;
            Set<String> keys = data.keySet();
            nwbInfo = new JsonObject();
            for(String key : keys)
            {                        
                String value = "";
                if (data.get(key) != null)
                    value = data.get(key).toString();

                nwbInfo.addProperty(key, value);                            
            }     
            this.apiResult.addProperty("resultCode", "200");
            this.apiResult.addProperty("message", "Success");
            this.apiResult.add("nwbInfo", nwbInfo);   
        }
        else 
        {
            // 데이터 없음.
            this.apiResult.addProperty("resultCode", "404");
        }
    }
    public void nwbInfos(SqlSession sqlSession, List<Object> result)
    {


        if (result != null) 
        {
            JsonArray nwbInfoList = new JsonArray();            
            JsonObject nwbInfo = null;
            //result            
            List<Object> ll = result;
            for(Object mm : ll)
            {
                Map<String, Object> data = (Map<String, Object>) mm;
                Set<String> keys = data.keySet();
                nwbInfo = new JsonObject();
                for(String key : keys)
                {                        
                    String value = "";
                    if (data.get(key) != null)
                        value = data.get(key).toString();

                    nwbInfo.addProperty(key, value);                            
                }     
                nwbInfoList.add(nwbInfo);
                    
            }
            this.apiResult.addProperty("resultCode", "200");
            this.apiResult.addProperty("message", "Success");
            this.apiResult.add("nwbInfos", nwbInfoList);   
                    
        }
        else 
        {
            // 데이터 없음.
            this.apiResult.addProperty("resultCode", "404");
        }
    }
}
