package kr.co.npc.nwb.service.prev;
//package kr.co.dstrust.dsl.service.prev;
//
//import java.math.BigInteger;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
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
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
////import com.sun.org.apache.xml.internal.security.utils.Base64;
//
//import kr.co.dstrust.dsl.core.ApiRequestTemplate;
//import kr.co.dstrust.dsl.util.Util;
//
//
//@Service("dynamicQuery")
//@Scope("prototype")
//public class DynamicQuery extends ApiRequestTemplate {
//    //@Autowired
//
//    //@Resource(name="sqlSession")
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
//    public DynamicQuery(Map<String, String> reqData) {
//        super(reqData);
//    }
//
//    @Override
//    public void requestParamValidation() throws RequestParamException 
//    {        
//        if (StringUtils.isEmpty(this.reqData.get("o_param"))) {//json  @파라미터이름 -> @파라미터이름, 값
//            throw new RequestParamException("o_param 가 없습니다.");
//        }   
//        if (StringUtils.isEmpty(this.reqData.get("db_name"))) {
//            throw new RequestParamException("db_name 가 없습니다.");
//        }
//        if (StringUtils.isEmpty(this.reqData.get("u_info"))) {//json  <$파라미터이름> ->  이름 , 값 
//            throw new RequestParamException("u_info 가 없습니다.");
//        }        
//        if (StringUtils.isEmpty(this.reqData.get("sql_type"))) {
//            throw new RequestParamException("sql_type 이 없습니다.");
//        }
//        else
//        {
//            if(this.reqData.get("sql_type").startsWith("SQL"))
//            {
//                if(StringUtils.isEmpty(this.reqData.get("query")))
//                {
//                    throw new RequestParamException("query 가 없습니다.");
//                }
//            }
//            else if (this.reqData.get("sql_type").startsWith("WORKSET"))
//            {
//                if(StringUtils.isEmpty(this.reqData.get("wset_cd")))
//                {
//                    throw new RequestParamException("wset_cd 가 없습니다.");
//                }
//            }
//        }
//    }
//
//    @Override
//    public void service() throws ServiceException {
//
//        int result_count = -1; 
//        List<Map<String, Object>> result = null;
//        Map<String, Object> one_result = null;
//
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
//        if(sqlSession != null)
//        {            
//            String udoc = this.reqData.get("u_info"); 
//            String odoc = this.reqData.get("o_param");
//            String sql_type = this.reqData.get("sql_type");
//            String sql_query = this.reqData.get("query");
//
//            JsonParser jp = new JsonParser();
//
//            JsonObject ju = (JsonObject) jp.parse(udoc);
//            JsonObject jo = (JsonObject) jp.parse(odoc);            
//
//            String _sql = null;
//
//            if(sql_type.startsWith("SQL"))
//            {
//                _sql = sql_query;
//            }
//            else if(sql_type.startsWith("WORKSET"))
//            {
//                Map<String, Object> wset_result = null;
//
//                wset_result = sqlSession.selectOne("worksets.workset", this.reqData);
//
//                if (wset_result != null ) {                   
//                    _sql = wset_result.get("sql").toString();                     
//                }
//                else {
//                    // 데이터 없음.
//                    this.apiResult.addProperty("resultCode", "404");
//                }
//            }     
//            
//            _sql = Util.convertAndIfCode(_sql,jo);
//
//            _sql = Util.convertSystemCode(_sql, ju);                
//            _sql = Util.convertOpenParameter(_sql,jo);
//            //                System.out.println("sql:\n"+_sql);
//            SqlRunner sqlRunner = null;
//            try {
//                sqlRunner = new SqlRunner(datasource.getConnection());
//            }
//            catch (SQLException e) {               
//                System.out.println("udoc:"+udoc);
//                System.out.println("odoc:"+odoc);
//                System.out.println("sql:\n"+_sql);
//                System.out.println("SqlRunner err:"+e.toString());
//                e.printStackTrace();
//            }            
//
//            try {
//                if(sqlRunner != null && _sql != null)
//                {
//                    if(StringUtils.isEmpty(sql_type))
//                    {
//                        result = sqlRunner.selectAll(_sql);
//                    }
//                    else
//                    {
//                        String mode_str = sql_type.split("_")[1];
//                        //                            System.out.println("mode_str:"+mode_str);
//
//                        switch(mode_str)
//                        {
//                        case "SELECT":
//                            one_result = sqlRunner.selectOne(_sql);
//                            break;
//                        case "SELECTALL":
//                            result = sqlRunner.selectAll(_sql);
//                            break;
//                        case "INSERT":
//                            result_count = sqlRunner.insert(_sql);
//                            break;
//                        case "UPDATE":
//                            result_count = sqlRunner.update(_sql);
//                            break;
//                        case "DELETE":
//                            result_count = sqlRunner.delete(_sql);
//                            break;
//                        case "RUN":
//                            sqlRunner.run(_sql);
//                            result_count = 1;
//                            break;                            
//                        }
//                    }
//                }
//            }
//            catch (SQLException e) {                
//                System.out.println("udoc:"+udoc);
//                System.out.println("odoc:"+odoc);
//                System.out.println("sql:\n"+_sql);
//                System.out.println("sqlRunner.selectAll err:"+e.toString());
//                e.printStackTrace();
//            }
//            finally
//            {
//                sqlRunner.closeConnection();
//            }
//
//
//            if (result != null) {
//                JsonArray dataList = new JsonArray();
//                JsonObject rowData = null;
//                //result
//                for(Map<String, Object> ro : result)
//                {
//                    Map<String, Object> data = ro;    
//                    Set<String> keys = data.keySet();
//                    rowData = new JsonObject();
//                    String dataStr = null;
//
//                    for(String key : keys)
//                    {   
//                        try {
//                            String class_str = "";
//                            if(data.get(key) !=null)
//                            {
//                                class_str = data.get(key).getClass().toString();
//                                //                            System.out.println("class string:["+(data.get(key)).getClass().toString()+"]");
//                            }
//                            Gson gson = new Gson();
//
//                            switch(class_str)
//                            {
//                            case "class java.lang.String":
//                                dataStr = data.get(key).toString();
//                                rowData.addProperty(key, dataStr);
//                                break;
//                            case "class java.lang.Integer":
//                                dataStr = data.get(key).toString();
//                                rowData.addProperty(key, dataStr);
//                                break;
//                            case "class java.sql.Timestamp":
//                                java.sql.Timestamp ts = (java.sql.Timestamp) data.get(key);                            
//                                dataStr = Util.getDBFormatDate(new Date(ts.getTime()));  
//                                rowData.addProperty(key, dataStr);
//                                break;
//                            case "class [B":           
//                                //                            System.out.println("image data:"+data.get(key));
//                                dataStr = Base64.encodeBase64String( (byte[]) data.get(key));                            
//                                rowData.addProperty(key,dataStr);
//                                break;
//                            case "":
//                                dataStr = "";
//                                rowData.addProperty(key, dataStr);
//                                break;
//                            default:
//                                dataStr = data.get(key).toString();
//                                rowData.addProperty(key, dataStr);
//                                break;                        
//                            }
//                        }catch(Exception ex)
//                        {
//                            System.out.println("Converting json ex:"+ex.toString());
//                        }
//
//                    }                      
//                    dataList.add(rowData); 
//                }
//
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.add("datas", dataList);               
//            }
//            else if(one_result != null){                
//                JsonObject rowData = null;
//                //result
//                Map<String, Object> data = one_result;    
//                Set<String> keys = data.keySet();
//                rowData = new JsonObject();
//                for(String key : keys)
//                {                        
//                    Gson gson = new Gson();
//                    String json = gson.toJson(data.get(key));
//                    rowData.addProperty(key, json);                              
//                }
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.add("datas", rowData);   
//            }
//            else if(result_count != -1){
//                JsonObject jdata =new JsonObject();
//                jdata.addProperty("datas", result_count);
//
//                this.apiResult.addProperty("resultCode", "200");
//                this.apiResult.addProperty("message", "Success");
//                this.apiResult.add("datas", jdata); 
//            }
//            else {
//                // 데이터 없음.
//                this.apiResult.addProperty("resultCode", "404");
//            }
//
//
//        } else {
//            // 데이터 없음.
//            this.apiResult.addProperty("resultCode", "404");
//        }      
//    }
//}