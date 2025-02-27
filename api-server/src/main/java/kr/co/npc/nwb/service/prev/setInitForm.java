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
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//
//@Service("setInitForm")
//@Scope("prototype")
//public class setInitForm extends ApiRequestTemplate {
//
//    @Resource(name="sqlSession_erp")    
//    private SqlSession sqlSession_erp;
//    @Resource(name="sqlSession_qms")    
//    private SqlSession sqlSession_qms;
//    @Resource(name="sqlSession_mms")    
//    private SqlSession sqlSession_mms;
//    @Resource(name="sqlSession_gw")    
//    private SqlSession sqlSession_gw;
//    @Resource(name="sqlSession_eis")    
//    private SqlSession sqlSession_eis;
//
//    @Resource(name="dataSource_gw")
//    private DriverManagerDataSource  dataSource_gw;
//    @Resource(name="dataSource_erp")
//    private DriverManagerDataSource  dataSource_erp;
//    @Resource(name="dataSource_qms")
//    private DriverManagerDataSource  dataSource_qms;
//    @Resource(name="dataSource_mms")
//    private DriverManagerDataSource  dataSource_mms;
//    @Resource(name="dataSource_eis")
//    private DriverManagerDataSource  dataSource_eis;
//    
//    public setInitForm(Map<String, String> reqData) {
//        super(reqData);
//
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {        
//        if (StringUtils.isEmpty(this.reqData.get("userID"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("ID가 없습니다.");
//        } 
//        /*if (StringUtils.isEmpty(this.reqData.get("init_form"))) {
//            throw new RequestParamException("init_form 가 없습니다.");
//        } */       
//        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
//            throw new RequestParamException("db_name 가 없습니다.");
//        }
//    
//    }
//    
//    @Override
//    public void service() throws ServiceException {
//        List<Object> result = null;
//        String db_name = this.reqData.get("db_name");
//        
//        SqlSession sqlSession = null;
//        DriverManagerDataSource datasource = null;
//        
//        switch(db_name)
//        {
//        case "ERP":
//            sqlSession = sqlSession_erp;
//            datasource = dataSource_erp;
//            break;
//        case "QMS":
//            sqlSession = sqlSession_qms;
//            datasource = dataSource_qms;
//            break;
//        case "MMS":
//            sqlSession = sqlSession_mms;
//            datasource = dataSource_mms;
//            break;
//        case "GW":
//            sqlSession = sqlSession_gw;
//            datasource = dataSource_gw;
//            break;
//        case "EIS":
//            sqlSession = sqlSession_eis;
//            datasource = dataSource_eis;
//            break;
//        default :
//            break;
//        }
//        
//        
//        if(sqlSession != null)
//        {
//            Object initForm = null;
//            initForm = sqlSession.selectOne("initForm.getInitForm", this.reqData);
//            
//            //sqlSession.selectList("notices.noticeList", this.reqData);
//                               
//            if (initForm != null) {                    
//                
//                JsonObject vo = null;
//                //result                    
//                
//                    Map<String, Object> data = (Map<String, Object>) initForm;    
//                    Set<String> keys = data.keySet();
//                    vo = new JsonObject();
//                    for(String key : keys)
//                    {                        
//                        vo.addProperty(key, data.get(key).toString());                            
//                    }                     
//                    this.apiResult.add("resultVO", vo);               
//     
//            } else {
//               //this.apiResult.addProperty("resultCode", "404");
//            }
//            int cnt =sqlSession.insert("initForm.setInitForm", this.reqData); 
//            
//            if (cnt > 0) {
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                   
//            }else {
//                // 초기 form insert 실패
//                this.apiResult.addProperty("resultCode", "404");
//            }
//        
//        }
//
//    }
//}
