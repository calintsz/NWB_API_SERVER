package kr.co.npc.nwb;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

//import com.github.nettybook.ch9.service.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {
    
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectUserList(Map<String,Object> map) {
        
        return (List<Map<String,Object>>) selectList("users.userInfoById", map);
    }
    
    
}
