package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//
//
//@Service("worksetInfo")
//@Scope("prototype")
//public class WorksetInfo extends ApiRequestTemplate {
//    //@Autowired
//
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
//    public WorksetInfo(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        RequestParamException rp = null;
//        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
//            throw new RequestParamException("db_name 가 없습니다.");
//        }
//        boolean no_wset = false ;
//        boolean no_frm = false ;
//
//        no_wset = StringUtils.isEmpty(this.reqData.get("wset_cd"));
//        no_frm = StringUtils.isEmpty(this.reqData.get("frm_cd"));
//        
//        if ( no_wset && no_frm ) {
//            
//            if(no_frm)
//                throw new RequestParamException("frm_cd 가 없습니다.");
//            
//            if(no_wset)
//                throw new RequestParamException("wset_cd 가 없습니다.");
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
//        
//        String db_name = this.reqData.get("db_name");
//        SqlSession sqlSession = null;
//        switch(db_name)
//        {
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
//            break;
//        }
//        
//        if(sqlSession != null)
//        {
//            if (this.reqData.get("wset_cd") !=null )
//            {
//                Object result = null;
//                result = sqlSession.selectOne("worksets.workset", this.reqData);
//                
//                if (result != null) {                    
//                    
//                    JsonObject workset = null;
//                    //result                    
//                    
//                        Map<String, Object> data = (Map<String, Object>) result;    
//                        Set<String> keys = data.keySet();
//                        workset = new JsonObject();
//                        for(String key : keys)
//                        {                        
//                            workset.addProperty(key, data.get(key).toString());                            
//                        }                     
//                                        
//                                    
//                    this.apiResult.addProperty("resultCode", "200");
//                    this.apiResult.addProperty("message", "Success");
//                    this.apiResult.add("worksets", workset);               
//                }
//                else {
//                    // 데이터 없음.
//                    this.apiResult.addProperty("resultCode", "404");
//                }
//            }
//            else if (this.reqData.get("frm_cd") !=null )
//            {
//                List<Object> result = null;
//                result = sqlSession.selectList("worksets.worksetList", this.reqData);
//                
//                if (result != null) {
//                    
//                    JsonArray worksetsList = new JsonArray();
//                    JsonObject workset = null;
//                    //result
//                    for(Object ro : result)
//                    {
//                        Map<String, Object> data = (Map<String, Object>) ro;    
//                        Set<String> keys = data.keySet();
//                        workset = new JsonObject();
//                        for(String key : keys)
//                        {                        
//                            workset.addProperty(key, data.get(key).toString());                            
//                        }                      
//                        worksetsList.add(workset); 
//                    }
//                                    
//                    this.apiResult.addProperty("resultCode", "200");
//                    this.apiResult.addProperty("message", "Success");
//                    this.apiResult.add("worksets", worksetsList);               
//                }
//                else {
//                    // 데이터 없음.
//                    this.apiResult.addProperty("resultCode", "404");
//                }
//            }
//            
//            
//            
//        } else {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }       
//    }
//}