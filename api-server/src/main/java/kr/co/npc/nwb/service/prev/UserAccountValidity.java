package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.math.BigInteger;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.ibatis.jdbc.SqlRunner;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.context.annotation.Scope;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
////import com.sun.org.apache.xml.internal.security.utils.Base64;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//import kr.co.dstrust.dsl.util.Util;
//
//
//@Service("accountValidity")
//@Scope("prototype")
//public class UserAccountValidity extends ApiRequestTemplate {
//    //@Autowired
//
//    @Resource(name="sqlSession_dsl")    
//    private SqlSession sqlSession_dsl;
//
//    public UserAccountValidity(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException 
//    {         
//        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("db_name 가 없습니다.");
//        }        
//        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("userGwId 가 없습니다.");
//        }     
//    }
//
//    @Override
//    public void service() throws ServiceException {
//        
//        Map<String,Object> result = null;
//        
//        String db_name = this.reqData.get("db_name");
//        SqlSession sqlSession = null;
//        switch(db_name)
//        {
//        case "DSL":
//            sqlSession = sqlSession_dsl;
//            break;
//        default :
//            sqlSession = sqlSession_dsl;
//            break;
//        }
//
//        int failCount = 0;
//        
//        if(sqlSession != null)
//        {
//            result = sqlSession.selectOne("accounts.userAccountVailidity", this.reqData);
//            
//            if (result != null) {
//                //result
//                
//                Set<String> keys = result.keySet();
//                JsonObject accountInfos = new JsonObject();
//                for(String key : keys)
//                {                        
//                    accountInfos.addProperty(key, result.get(key).toString());                            
//                }    
//
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.add("accountInfos", accountInfos);  
//            }
//            else {
//                // 데이터 없음.
//                this.apiResult.addProperty("resultCode", "404");  
//            }
//        } else {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }
//              
//    }
//}