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
//@Service("userDetailInfo")
//@Qualifier()
////@Qualifier("sqlSessionTemplate")
//@Scope("prototype")
//public class UserInfoDetail extends ApiRequestTemplate {
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
//    public UserInfoDetail(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {
//            throw new RequestParamException("userGwId 유저 아이디가 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("jump_db_name"))) {
//            throw new RequestParamException("db_name 이 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("userSysId"))) {
//            throw new RequestParamException("userSysId 유저 아이디가 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("frm_cd"))) {
//            throw new RequestParamException("frm_cd 가 없습니다.");
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
//        String db_name = this.reqData.get("jump_db_name");
//        SqlSession sql_session = null;
//        switch(db_name)
//        {
//        case "GW":
////            gwUserInfo(sqlSession_gw);
//            return;
//        case "ERP":
//            sql_session = sqlSession_erp;
//            break;
//        case "QMS":
//            sql_session = sqlSession_qms;
//            break;
//        case "MMS":
//            sql_session = sqlSession_mms;
//            break;
//        case "EIS":
//            sql_session = sqlSession_eis;
//            break;
//        default :
//            break;
//        }
//        
//        checkFormOpened(sql_session);
//    }
//    public void checkFormOpened(SqlSession sqlSession)
//    {
//        Map<String, Object> result = sqlSession.selectOne("users.checkFormOpened", this.reqData);
//        
//        if (result != null) 
//        {
//            String frm_cd = result.get("frm_cd").toString();
//                                              
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.addProperty("opened", frm_cd.equalsIgnoreCase(this.reqData.get("frm_cd"))?"Y":"N");               
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }    
//    }
//    
////    public void gwUserInfo(SqlSession sqlSession)
////    {
////        Map<String, Object> result = sqlSession.selectOne("users.userInfoById", this.reqData);
////
////        if (result != null) {
////            String userNm1 = String.valueOf(result.get("userNm1"));
////
////            // helper.
////            this.apiResult.addProperty("resultCode", "200");
////            this.apiResult.addProperty("message", "Success");
////            this.apiResult.addProperty("userNm1", userNm1);
////        }
////        else {
////            // 데이터 없음.
////            this.apiResult.addProperty("resultCode", "404");
////        }
////    }
//}
