package overdiary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overdiary.domain.Alarm;
import overdiary.domain.User;
import overdiary.helper.LoginUser;
import overdiary.service.AlarmService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class ApiAlarmController {

    @Resource(name = "alarmService")
    AlarmService alarmService;

    @GetMapping("/send")
    public Object sendAlarm(@LoginUser User loginUser) {
        Map<String, Object> sendValue = new HashMap<String, Object>();

        sendValue.put("alarmList", alarmService.sendAlarm(loginUser)); //bookList란
        return sendValue;
    }
}
