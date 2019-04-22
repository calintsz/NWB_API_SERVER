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
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//
//@Service("getNotice")
//@Scope("prototype")
//public class GetNotice extends ApiRequestTemplate {
//
//    @Resource(name="sqlSession_gw")    
//    private SqlSession sqlSession_gw;
//    
//    public GetNotice(Map<String, String> reqData) {
//        super(reqData);
//
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {        
////        if (StringUtils.isEmpty(this.reqData.get("o_param"))) {//json  @파라미터이름 -> @파라미터이름, 값
////            throw new RequestParamException("o_param 가 없습니다.");
////        } 
////        if (StringUtils.isEmpty(this.reqData.get("wset_cd"))) {
////            throw new RequestParamException("wset_cd 가 없습니다.");
////        }        
////        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
////            throw new RequestParamException("db_name 가 없습니다.");
////        }
////        if (StringUtils.isEmpty(this.reqData.get("u_info"))) {//json  <$파라미터이름> ->  이름 , 값 
////            throw new RequestParamException("u_info 가 없습니다.");
////        }        
//    }
//    
//    @Override
//    public void service() throws ServiceException {
//        List<Object> result = null;
//        SqlSession sqlSession = sqlSession_gw;
//        
//        if(sqlSession != null)
//        {
//            result = sqlSession.selectList("notices.noticeList", this.reqData);
//            
//            if (result != null) {
//                
//                JsonArray mappingList = new JsonArray();
//                JsonObject mapping = null;
//                //result
//                for(Object ro : result)
//                {
//                    Map<String, Object> data = (Map<String, Object>) ro;    
//                    Set<String> keys = data.keySet();
//                    mapping = new JsonObject();
//                    for(String key : keys)
//                    {                        
//                        mapping.addProperty(key, data.get(key).toString());                            
//                    }                      
//                    mappingList.add(mapping); 
//                }
//                                
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.add("mappings", mappingList);               
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
//
//}
