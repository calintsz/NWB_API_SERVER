package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import redis.clients.jedis.Jedis;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//import kr.co.dstrust.dsl.core.KeyMaker;
//import kr.co.dstrust.dsl.service.dao.TokenKey;
//
//@Service("userSecurityPolicy")
//@Qualifier()
//
//@Scope("prototype")
//public class UserSecurityPolicy extends ApiRequestTemplate {
//
//    @Resource(name="sqlSession_erp")    
//    private SqlSession sqlSession_erp;
//    
//    public UserSecurityPolicy(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {
//            throw new RequestParamException("userGwId 유저 아이디가 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
//            throw new RequestParamException("db_name 이 없습니다.");
//        }
//    }
//
//    @Override
//    public void service() throws ServiceException {
//        // 입력 email 사용자의 이메일을 HTTP header에 입력한다.
//        // 출력 resultCode API 처리 결과코드를 돌려준다. API 처리 결과가 정상이면 결과코드는 200이다.
//        // 출력 message API 처리 결과 메시지를 돌려준다. API의 처리결과가 정상일 때는 Success 메시지를 돌려주며
//        // 나머지 정상이 아닐 때는 오류 메시지를 돌려준다.
//        // 출력 userNo 입력된 이메일에 해당하는 사용자의 사용자 번호를 돌려준다.
//        
//        String db_name = this.reqData.get("db_name");
//        SqlSession sql_session = null;
//        switch(db_name)
//        {
//        case "ERP":
//            sql_session = sqlSession_erp;
//            break;        
//        default :
//            sql_session = sqlSession_erp;
//            break;
//        }
//        
//        userInfo(sql_session);
//    }
//    
//    public void userInfo(SqlSession sqlSession)
//    {
//        Map<String, Object> result = sqlSession.selectOne("users.userSecurity", this.reqData);
//
//        if (result != null) 
//        {
//            JsonObject map_list = null;
//            //result
//
//            Map<String, Object> data = result; 
//            Set<String> keys = data.keySet();
//            map_list = new JsonObject();
//            for(String key : keys)
//            {                        
//                String value = "";
//                if (data.get(key) != null)
//                    value = data.get(key).toString();
//
//                map_list.addProperty(key, value);                            
//            }                                      
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.add("securityPolicy", map_list);               
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }    
//    }
//}
