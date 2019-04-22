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
//@Service("jumpInfo")
//@Qualifier()
////@Qualifier("sqlSessionTemplate")
//@Scope("prototype")
//public class JumpInfo extends ApiRequestTemplate {
//    //@Autowired
//
//    //@Resource(name="sqlSession")
//    @Resource(name="sqlSession_gw")    
//    private SqlSession sqlSession_gw;
//    //@Resource(name="sqlSession")
//    @Resource(name="sqlSession_erp")    
//    private SqlSession sqlSession_erp;
//    @Resource(name="sqlSession_qms")    
//    private SqlSession sqlSession_qms;
//    @Resource(name="sqlSession_mms")    
//    private SqlSession sqlSession_mms;
//    @Resource(name="sqlSession_eis")    
//    private SqlSession sqlSession_eis;
//    
//    public JumpInfo(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("reg_id"))) {
//            throw new RequestParamException("reg_id 유저 아이디가 없습니다.");
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
//        SqlSession sqlSession = null;
//        switch(db_name)
//        {
//        case "GW":
//            sqlSession = sqlSession_gw;
//            return;//break;
//        case "ERP":
//            sqlSession = sqlSession_erp;            
//            break;
//        case "QMS":
//            sqlSession = sqlSession_qms;
//            break;
//        case "MMS":
//            sqlSession = sqlSession_mms;
//            break;
//        case "EIS":
//            sqlSession = sqlSession_eis;
//            break;
//        default :
//            sqlSession = sqlSession_erp;
//            break;
//        }
//        
//        userInfo(sqlSession);
//    }
//    public void userInfo(SqlSession sqlSession)
//    {
//        Map<String, Object> result = sqlSession.selectOne("jumps.initInfoByRegid", this.reqData);
//
//        if (result != null) 
//        {
//            JsonObject workset = null;
//            //result
//            
//                Map<String, Object> data = result; 
//                Set<String> keys = data.keySet();
//                workset = new JsonObject();
//                for(String key : keys)
//                {                        
//                    String value = "";
//                    if (data.get(key) != null)
//                        value = data.get(key).toString();
//                    
//                    workset.addProperty(key, value);                            
//                }                                      
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.add("infos", workset);               
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }    
//    }
//    
//}
