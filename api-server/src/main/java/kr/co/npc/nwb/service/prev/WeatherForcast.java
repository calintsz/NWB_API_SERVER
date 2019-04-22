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
//import org.springframework.web.client.RestTemplate;
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
//@Service("weather")
//@Qualifier()
////@Qualifier("sqlSessionTemplate")
//@Scope("prototype")
//public class WeatherForcast extends ApiRequestTemplate {
//    //@Autowired
//
//    //@Resource(name="sqlSession")
//    //    @Resource(name="sqlSession_gw")    
//    //    private SqlSession sqlSession_gw;
//    //    //@Resource(name="sqlSession")
//    //    @Resource(name="sqlSession_erp")    
//    //    private SqlSession sqlSession_erp;
//    //    @Resource(name="sqlSession_qms")    
//    //    private SqlSession sqlSession_qms;
//    //    @Resource(name="sqlSession_mms")    
//    //    private SqlSession sqlSession_mms;
//    //    @Resource(name="sqlSession_eis")    
//    //    private SqlSession sqlSession_eis;
//
//    public WeatherForcast(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("operation"))) {
//            throw new RequestParamException("operation 이 없습니다.");
//        }
//        //        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
//        //            throw new RequestParamException("db_name 이 없습니다.");
//        //        }
//        //        if (StringUtils.isEmpty(this.reqData.get("userSysId"))) {
//        //            throw new RequestParamException("UserInfo 유저 아이디가 없습니다.");
//        //        }
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
//        JsonObject jo = null;
//
//        String operation = this.reqData.get("operation");
//        switch (operation)
//        {
//        case "GRIB":
//            jo = getWeatherGrib();
//            break;
//        case "TIMEDATA":
//            jo = getForcastTime();
//            break;
//        case "SPACEDATA":
//            jo = getForcastRegion();
//            break;
//        case "VERSIONCHECK":
//            jo = getVersionCheck();
//            break;
//        default:
//
//        }
//
//
//
//        if(jo==null)
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }
//        else if ( jo.toString().equals(""))
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }
//        else            
//        {
//            // helper.
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.add("weatherInfo", jo);
//        }
//
//
//    }
//
//    private JsonObject getVersionCheck() {
//        JsonObject jo = new JsonObject();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String uri = "\"http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey=9UBZEcmqZz%2B5UmsOQfhhojS4bVyBv8wgYcOuryWlLKhJFjlN1beOySWKW5xzSUqD386QJgBdS7ieF7SP0jr2Bw%3D%3D&base_time=1500&base_date=20190311&nx=97&ny=79&_type=json\"";
//
//        jo = restTemplate.getForObject(uri, JsonObject.class);
//
//        return jo;
//    }
//
//    private JsonObject getForcastRegion() {
//        JsonObject jo = new JsonObject();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String uri = "\"http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey=9UBZEcmqZz%2B5UmsOQfhhojS4bVyBv8wgYcOuryWlLKhJFjlN1beOySWKW5xzSUqD386QJgBdS7ieF7SP0jr2Bw%3D%3D&base_time=1500&base_date=20190311&nx=97&ny=79&_type=json\"";
//
//        jo = restTemplate.getForObject(uri, JsonObject.class);
//
//        return jo;
//    }
//
//    private JsonObject getForcastTime() {
//        JsonObject jo = new JsonObject();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String uri = "\"http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey=9UBZEcmqZz%2B5UmsOQfhhojS4bVyBv8wgYcOuryWlLKhJFjlN1beOySWKW5xzSUqD386QJgBdS7ieF7SP0jr2Bw%3D%3D&base_time=1500&base_date=20190311&nx=97&ny=79&_type=json\"";
//
//        jo = restTemplate.getForObject(uri, JsonObject.class);
//
//        return jo;
//    }
//
//    public JsonObject getWeatherGrib()
//    {
//        JsonObject jo = new JsonObject();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String uri = "\"http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?ServiceKey=9UBZEcmqZz%2B5UmsOQfhhojS4bVyBv8wgYcOuryWlLKhJFjlN1beOySWKW5xzSUqD386QJgBdS7ieF7SP0jr2Bw%3D%3D&base_time=1500&base_date=20190311&nx=97&ny=79&_type=json\"";
//
//        jo = restTemplate.getForObject(uri, JsonObject.class);
//
//        return jo;
//    }
//
//    public void userInfo(SqlSession sqlSession)
//    {
//        Map<String, Object> result = sqlSession.selectOne("users.userInfoById", this.reqData);
//
//        if (result != null) 
//        {
//            JsonObject workset = null;
//            //result
//
//            Map<String, Object> data = result; 
//            Set<String> keys = data.keySet();
//            workset = new JsonObject();
//            for(String key : keys)
//            {                        
//                String value = "";
//                if (data.get(key) != null)
//                    value = data.get(key).toString();
//
//                workset.addProperty(key, value);                            
//            }                                      
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.add("userInfos", workset);               
//        }
//        else 
//        {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }    
//    }
//
//    public void gwUserInfo(SqlSession sqlSession)
//    {
//        Map<String, Object> result = sqlSession.selectOne("users.userInfoById", this.reqData);
//
//        if (result != null) {
//            String userNm1 = String.valueOf(result.get("userNm1"));
//
//            // helper.
//            this.apiResult.addProperty("resultCode", "200");
//            this.apiResult.addProperty("message", "Success");
//            this.apiResult.addProperty("userNm1", userNm1);
//        }
//        else {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }
//    }
//}
