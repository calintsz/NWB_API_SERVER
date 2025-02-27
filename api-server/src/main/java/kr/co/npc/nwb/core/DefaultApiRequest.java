package kr.co.npc.nwb.core;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("notFound")
@Scope("prototype")
public class DefaultApiRequest extends ApiRequestTemplate {

    public DefaultApiRequest(Map<String, String> reqData) {
        super(reqData);
    }

    public void service() {
        this.apiResult.addProperty("resultCode", "404");
    }
}
