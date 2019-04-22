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
//@Service("loginInfo")
//@Scope("prototype")
//public class UserLoginInfo extends ApiRequestTemplate {
//    //@Autowired
//
//    @Resource(name="sqlSession_dsl")    
//    private SqlSession sqlSession_dsl;
//
//    public UserLoginInfo(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException 
//    {           
//        if (StringUtils.isEmpty(this.reqData.get("ip"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("ip 가 없습니다.");
//        }       
//        if (StringUtils.isEmpty(this.reqData.get("mac"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("mac 가 없습니다.");
//        }     
//        if (StringUtils.isEmpty(this.reqData.get("host_name"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("host_name 가 없습니다.");
//        }        
//        if (StringUtils.isEmpty(this.reqData.get("is_success"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("is_success 가 없습니다.");
//        }        
//        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("userGwId 가 없습니다.");
//        }        
//        if (StringUtils.isEmpty(this.reqData.get("userSysId"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("userSysId 가 없습니다.");
//        }     
//        if (StringUtils.isEmpty(this.reqData.get("userPwd"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("userPwd 가 없습니다.");
//        }
//    }
//
//    @Override
//    public void service() throws ServiceException {
//
//        int result_count = -1; 
//        
//        result_count = sqlSession_dsl.insert("loginLogs.insertUserLoginInfo",this.reqData);
//        
//        if(result_count != -1)
//        {   
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            //this.apiResult.add("infos", workset);               
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }  
//              
//    }
//}