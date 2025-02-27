package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.util.Dictionary;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import redis.clients.jedis.Jedis;
//
//import com.google.gson.JsonObject;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//import kr.co.dstrust.dsl.core.JedisHelper;
//import kr.co.dstrust.dsl.core.KeyMaker;
//import kr.co.dstrust.dsl.service.dao.TokenKey;
//
//@Service("tokenIssue")
//@Scope("prototype")
//public class TokenIssue extends ApiRequestTemplate {
//    private static final JedisHelper helper = JedisHelper.getInstance();
//
//    //@Autowired
//    @Resource(name="sqlSession_gw")
//    private SqlSession sqlSession;
//
//    @Resource(name="sqlSession_erp")
//    private SqlSession sqlSession_erp;
//    @Resource(name="sqlSession_qms")
//    private SqlSession sqlSession_qms;
//    @Resource(name="sqlSession_mms")
//    private SqlSession sqlSession_mms;
//
//    public TokenIssue(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    public void requestParamValidation() throws RequestParamException {
//        if (StringUtils.isEmpty(this.reqData.get("userGwId"))) {
//            throw new RequestParamException("data:"+this.reqData.toString()+"\n userId가 없습니다.");
//        }
//
//        if (StringUtils.isEmpty(this.reqData.get("userPwd"))) {
//            throw new RequestParamException("data:"+this.reqData.toString()+"\n password가 없습니다.");
//        }
//        
//        if (StringUtils.isEmpty(this.reqData.get("userSysId"))) {
//            throw new RequestParamException("data:"+this.reqData.toString()+"\n userSysId가 없습니다.");
//        }
//    }
//
//    public void service() throws ServiceException {
//        Jedis jedis = null;
//        try {
//            Map<String, Object> result = sqlSession.selectOne("users.userInfoByPassword", this.reqData);
//
//            if (result != null) {
//                final long threeHour = 60 * 60 * 3;
//                long issueDate = System.currentTimeMillis() / 1000;
//                String userGwId = String.valueOf(result.get("userGwId"));
//                String userNm1 = String.valueOf(result.get("userNm1"));
//                String userSysId = String.valueOf(this.reqData.get("userSysId"));
//                
//                // token 만들기.
//                JsonObject token = new JsonObject();
//                token.addProperty("issueDate", issueDate);
//                token.addProperty("expireDate", issueDate + threeHour);
//                token.addProperty("userGwId", userGwId);
//                token.addProperty("userSysId", userSysId);
//                token.addProperty("userNm1", userNm1);
//                
//                // token 저장.
//                KeyMaker tokenKey = new TokenKey(userGwId, userSysId, issueDate);
//                jedis = helper.getConnection();
//                jedis.setex(tokenKey.getKey(), 60 * 60 * 3, token.toString());
//                
//                // changePwd 
//                HashMap<String,String> datas = new HashMap<String,String>();                
//                datas.put("userSysId",userSysId);
//                datas.put("userGwId",userGwId);
//                datas.put("token", tokenKey.getKey());
//
//                // ERP 
//                sqlSession_erp.update("users.updatePwd", datas);
//                // QMS 
//                sqlSession_qms.update("users.updatePwd", datas);
//                // MMS
//                sqlSession_mms.update("users.updatePwd", datas);
//
//                // helper.
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.addProperty("token", tokenKey.getKey());
//            }
//            else {
//                // 데이터 없음.
//                this.apiResult.addProperty("resultCode", "404");
//            }
//            
//            helper.returnResource(jedis);
//        }
//        catch (Exception e) {
//            helper.returnResource(jedis);
//        }
//    }
//}
