package kr.co.npc.nwb.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;


import java.lang.reflect.Type;


public class Util {

    public Util uinstance = null;



    public Util GetInstance() 
    {
        try {
            if(uinstance == null)
                return uinstance = new Util();           
        }
        catch(Exception ex)
        {
            System.out.println("Util GetInstance ex:"+ex.toString());
        }
        return uinstance;
    }

    public static String getDBFormatDate(Date dateTime)
    {
        String date_str = null;

        try
        {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);

            date_str = mSimpleDateFormat.format(dateTime);            
        }
        catch(Exception ex)
        {
            System.out.println("ex:"+ex.toString());
        }

        return date_str;
    }

    public static String convertSystemCode(String _sql, Map<String, String> user)
    {
        try
        {            
            Date currentTime = new Date(System.currentTimeMillis());            

            String now_date = getDBFormatDate(currentTime);

            if(_sql.matches("<[$]co_cd>"))
                _sql = _sql.replaceAll("<[$]co_cd>", user.get("co_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]bs_cd>"))
                _sql = _sql.replaceAll("<[$]bs_cd>", user.get("bs_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]sys_cd>"))
                _sql = _sql.replaceAll("<[$]sys_cd>", user.get("system_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]div_cd>"))
                _sql = _sql.replaceAll("<[$]div_cd>", user.get("div_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]fac_cd>"))
                _sql = _sql.replaceAll("<[$]fac_cd>", user.get("fac_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]dept_cd>"))
                _sql = _sql.replaceAll("<[$]dept_cd>", user.get("dept_cd").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]emp_no>"))
                _sql = _sql.replaceAll("<[$]emp_no>", user.get("emp_no").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]reg_id>"))
                _sql = _sql.replaceAll("<[$]reg_id>", user.get("reg_id").toString().replaceAll("\"", "'"));
            if(_sql.matches("<[$]uesr_id>"))
                _sql = _sql.replaceAll("<[$]uesr_id>", user.get("id").toString().replaceAll("\"", "'"));

            if(_sql.matches("<[$]today>"))
                _sql = _sql.replaceAll("<[$]today>", now_date.replace("\"", "'"));       
        }
        catch(Exception ex)
        {
            System.out.println("convertSystemCode ex:"+ex.toString());
        }


        return _sql;       
    }
    public static String convertSystemCode(String _sql, JsonObject user)
    {
        try
        {            
            Date currentTime = new Date(System.currentTimeMillis());            

            String now_date = getDBFormatDate(currentTime);

            //            System.out.println("id:"+user.get("id").toString().replaceAll("\"", "'"));
            //            System.out.println("emp_no:"+user.get("emp_no").toString().replaceAll("\"", "'"));

            Pattern p = Pattern.compile("<[$]co_cd>");
            java.util.regex.Matcher m = p.matcher(_sql);

            if(m.find())
            {
//                System.out.println("matches co_cd");
            _sql = _sql.replaceAll("<[$]co_cd>", user.get("co_cd").toString().replaceAll("\"", "'"));}

            p = Pattern.compile("<[$]bs_cd>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches bs_cd");
            _sql = _sql.replaceAll("<[$]bs_cd>", user.get("bs_cd").toString().replaceAll("\"", "'"));}

            p = Pattern.compile("<[$]sys_cd>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches sys_cd");
            _sql = _sql.replaceAll("<[$]sys_cd>", user.get("system_cd").toString().replaceAll("\"", "'"));}

            p = Pattern.compile("<[$]div_cd>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches div_cd");
            _sql = _sql.replaceAll("<[$]div_cd>", user.get("div_cd").toString().replaceAll("\"", "'"));}

            p = Pattern.compile("<[$]fac_cd>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches fac_cd");
            _sql = _sql.replaceAll("<[$]fac_cd>", user.get("fac_cd").toString().replaceAll("\"", "'"));}
            
            p = Pattern.compile("<[$]dept_cd>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches dept_cd");
            _sql = _sql.replaceAll("<[$]dept_cd>", user.get("dept_cd").toString().replaceAll("\"", "'"));}
            
            p = Pattern.compile("<[$]emp_no>");
            m = p.matcher(_sql);            
            if(m.find())
            {   
//                System.out.println("matches emp_no");
            _sql = _sql.replaceAll("<[$]emp_no>", user.get("emp_no").toString().replaceAll("\"", "'"));}
            
            p = Pattern.compile("<[$]reg_id>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches reg_id");
                _sql = _sql.replaceAll("<[$]reg_id>", user.get("reg_id").toString().replaceAll("\"", "'"));
            }

            p = Pattern.compile("<[$]uesr_id>");
            m = p.matcher(_sql);            
            if(m.find())
            {
//                System.out.println("matches uesr_id");
                _sql = _sql.replaceAll("<[$]uesr_id>", user.get("id").toString().replaceAll("\"", "'"));
            }


            p = Pattern.compile("<[$]today>");
            m = p.matcher(_sql);            
            if(m.find())
                _sql = _sql.replaceAll("<[$]today>", now_date.replace("\"", "'"));             

            _sql = convertTodayPlusNdate(currentTime,_sql);
        }
        catch(Exception ex)
        {
            System.out.println("convertSystemCode ex:"+ex.toString());
        }


        return _sql;       
    }

    public static String convertTodayPlusNdate(Date currentTime, String _sql)
    {
        try
        {
            Pattern p = Pattern.compile("[<][$][t][o][d][a][y][+-][0-9]+[ymd][>]");
            java.util.regex.Matcher m = p.matcher(_sql);

            if(m.find())
            {
                StringBuilder sb =  new StringBuilder();

                String[] strs =  _sql.split("<");
                for(int i = 0; i < strs.length; i++)
                {
                    if(i!=0)
                        strs[i] = "<"+strs[i];
                }

                for(int i = 0; i < strs.length; i++)
                {
                    String str = strs[i];
                    if(str.startsWith("<$today"))
                    {
                        String ndate = "";
                        String[] sa = str.split("[<][$]today[+-]");

                        int count = Integer.parseInt( sa[1].subSequence( 0, sa[1].length()-2 )+"" );
                        String ymd = sa[1].subSequence(sa[1].length()-2,sa[1].length()-1)+"";

                        if(str.subSequence(7, 8).equals("-"))
                            count = count * -1;

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(currentTime);

                        switch(ymd)
                        {
                        case "y":                               
                            cal.add(Calendar.YEAR, count);             
                            break;
                        case "m":
                            cal.add(Calendar.MONTH, count);           
                            break;
                        case "d":
                            cal.add(Calendar.DATE, count); 
                            break;                          
                        }
                        ndate = getDBFormatDate(cal.getTime());

                        strs[i] = strs[i].replaceAll("[<][$][t][o][d][a][y][+-][0-9]+[ymd][>]", ndate.replace("\"", "'"));
                    }                               
                    sb.append(strs[i]);
                }      
                _sql = sb.toString();
            }              
        }
        catch(Exception e)
        {
            System.out.println("convertTodayPlusNdate e:"+e.toString());
            e.printStackTrace();            
        }

        return _sql;
    }
    
    public static JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
    }

    public static String convertOpenParameter(String _sql, Map<String, String> jo) {
        try        
        {
            Set<String> keis = jo.keySet();

            for(String key : keis)
            {                
                if(key.startsWith("@"))
                {
                    String value = jo.get(key).toString().replaceAll("'","").replaceAll("\"", "'");                    
                    _sql = _sql.replaceAll(key, value);
                }                
            }

            return _sql;
        }
        catch(Exception ex)
        {
            System.out.println("convertOpenParameter ex:"+ex.toString());
        }

        return null;
    }

    public static String convertOpenParameter(String _sql, JsonObject jo) {
        try        
        {
            Set<Entry<String,JsonElement>> entries = jo.entrySet();

            for(Entry<String,JsonElement> entry : entries)
            {                
                String key = entry.getKey();
                String value = entry.getValue().toString().replaceAll("'","").replaceAll("\"", "'");

                _sql = _sql.replaceAll(key, value);
            }

            return _sql;
        }
        catch(Exception ex)
        {
            System.out.println("convertOpenParameter ex:"+ex.toString());
        }

        return null;
    }

    public static String convertAndIfCode(String _sql, JsonObject jo) {
        try        
        {
            Set<Entry<String,JsonElement>> entries = jo.entrySet();
            ArrayList<String> str_list = new ArrayList<String>();
            String[] spt_strs = _sql.split("andif");

            for(String str : spt_strs)
            {   
                int index = str.indexOf("endif");
                str = index!=-1?"andif"+str:str;
                
                String[] cstrs = str.split("endif");
//                for(String cs : cstrs)
//                    System.out.println("cs:" + cs);
                
                index = -99;
                for(String cs : cstrs)
                {
                    index = cs.indexOf("andif");
                   cs = index!=-1?cs+"endif":cs;
                   
                   str_list.add(cs);
                }
            }  
            

            for(Entry<String,JsonElement> entry : entries)
            {            

                String key = entry.getKey();
                String value = entry.getValue().toString().replaceAll("'","").replaceAll("\"", "'");

                StringBuilder sb = new StringBuilder();
                for(String str : str_list)
                {
                    //                    System.out.println("str:" + str);
                    if(str.indexOf("andif")==-1)
                    {
                        sb.append(str+"\n");   
                    }
                    else
                    {
                        if(str.indexOf(key)==-1)
                        {
//                            System.out.println("not Found Key:"+key);
//                            System.out.println("str:"+str);
                            sb.append("\n");                           
                        }
                        else {

//                            System.out.println("Found Key:"+key);
//                            System.out.println("Found value:"+value.replaceAll("'",""));
//                            System.out.println("str:"+str);
                            if(value.replaceAll("'","").isEmpty())
                            {
                                sb.append("\n");
                            }
                            else
                            {
                                sb.append(str.replace("andif", "and ").replace("endif","")+"\n");
                            }
                        }
                    }

                }
                
                _sql = sb.toString();
                
                
                _sql = _sql.replaceAll(key, value);
                
//                System.out.println("_sql:" + _sql);
            }

            return _sql;
        }
        catch(Exception ex)
        {
            System.out.println("convertOpenParameter ex:"+ex.toString());
        }

        return null;
    }
}
