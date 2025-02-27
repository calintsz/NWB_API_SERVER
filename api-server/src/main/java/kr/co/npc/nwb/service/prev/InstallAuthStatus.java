package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//
//@Service("InstallAuthStatus")
//@Qualifier()
////@Qualifier("sqlSessionTemplate")
//@Scope("prototype")
//public class InstallAuthStatus extends ApiRequestTemplate {
//    //@Autowired
//
//    @Resource(name="sqlSession_dsl")    
//    private SqlSession sqlSession_dsl;
//
//    public InstallAuthStatus(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("ip"))) {
//            throw new RequestParamException("ip 이 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("mac"))) {
//            throw new RequestParamException("mac 이 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("host_name"))) {
//            throw new RequestParamException("host_name 가 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("work_group"))) {
//            throw new RequestParamException("work_group 이 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("request_time"))) {
//            throw new RequestParamException("request_time 이 없습니다.");
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
//        Map<String, Object> result = null;
//        result = sqlSession_dsl.selectOne("installs.getAuthStatus", this.reqData);
//                
//        if(result != null)
//        {   
//            Map<String, Object> data = result; 
//            Set<String> keys = data.keySet();
//            
//            for(String key : keys)
//            {                        
//                String value = null;
//                if (data.get(key) != null)
//                    value = data.get(key).toString();
//                
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.addProperty("isAllowed", value==null?"0":value);                                           
//            }  
//                        
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }   
//    }
//    
//    
//}
