package kr.co.npc.nwb.core;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Service class dispatcher by uri
 * 
 * @author kris
 */
@Component
public class ServiceDispatcher {
    private static ApplicationContext springContext;

    @Autowired
    public void init(ApplicationContext springContext) 
    {
        ServiceDispatcher.springContext = springContext;
    }

    protected Logger logger = LogManager.getLogger(this.getClass());

    public static ApiRequest dispatch(Map<String, String> requestMap) 
    {
        String serviceUri = requestMap.get("REQUEST_URI");
        String beanName = null;

        System.out.println("serviceUri:"+serviceUri);
        
        if (serviceUri == null) 
        {
            beanName = "notFound";
        }

        if (serviceUri.startsWith("/tokens")) 
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");

            switch (httpMethod) 
            {
            case "POST":
                beanName = "tokenIssue";
                break;
            case "DELETE":
                beanName = "tokenExpire";
                break;
            case "GET":
                beanName = "tokenVerify";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else 
            if (serviceUri.startsWith("/users")) 
        {           
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "userAdd";
                break;
            case "PUT":
                beanName = "userUpdate";
                break;
            case "DELETE":
                beanName = "userDelete";
                break;
            case "GET":
                beanName = "userInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else if (serviceUri.startsWith("/checks")) 
        {           
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "checkAdd";
                break;
            case "PUT":
                beanName = "checkUpdate";
                break;
            case "DELETE":
                beanName = "checkRemove";
                break;
            case "GET":
                beanName = "checkInfos";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else if (serviceUri.startsWith("/worksets"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "worksetInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
            
        }
        else if (serviceUri.startsWith("/mappings"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "mappingInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else if (serviceUri.startsWith("/popups"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "popupInfo";
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "popupInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else if (serviceUri.startsWith("/dynamics"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "dynamicQuery";
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "dynamicQuery";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/notices"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "getNotice";
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "getNotice";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/jumps"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "jumpIssue";
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "jumpInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/inits"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "initFormChange";
                break;
            case "DELETE":
                break;
            case "GET":
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/menus"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "menuInfo";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/managements"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "managementInfo";
                break;
            case "DELETE":
                break;
            case "GET":
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/userLoginLogs"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "loginInfo";
                break;
            case "DELETE":
                break;
            case "GET":

                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/accountValidities"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                //beanName = "loginFailCount";
                beanName = "accountValidity";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/loginFailChecks"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "loginFailCount";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else if (serviceUri.startsWith("/userSecurityPolicy"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "userSecurityPolicy";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }else if (serviceUri.startsWith("/installs"))
        {
            String httpMethod = requestMap.get("REQUEST_METHOD");
            
            switch (httpMethod) 
            {
            case "POST":
                beanName = "installAuth";
                break;
            case "DELETE":
                break;
            case "GET":
                beanName = "InstallAuthStatus";
                break;
            default:
                beanName = "notFound";
                break;
            }
        }
        else 
        {
            beanName = "notFound";
        }

        ApiRequest service = null;
        try 
        {
            service = (ApiRequest) springContext.getBean(beanName, requestMap);
        }
        catch (Exception e) {
            e.printStackTrace();
            service = (ApiRequest) springContext.getBean("notFound", requestMap);
        }

        return service;
    }
}
