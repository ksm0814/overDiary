package overdiary.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import overdiary.domain.Alarm;
import overdiary.domain.AlarmRepository;
import overdiary.domain.Article;
import overdiary.domain.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlarmService {
    private static final Logger log = LoggerFactory.getLogger(AlarmService.class);

    @Resource(name = "alarmRepository")
    private AlarmRepository alarmRepository;


    public Iterable<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public void create(Article article, Iterable<User> allUser) {
        for (User user : allUser) {
            Alarm alarm = new Alarm(article.getTitle(), user, article.getArticleKey());
            alarmRepository.save(alarm);
        }
    }

    public List<Alarm> sendAlarm(User user) {
        List<Alarm> recentAlarms = alarmRepository.findByTargetUser(user);
        return recentAlarms;
    }

    public void removeAlarm(long alarmKey) {
        alarmRepository.delete(alarmKey);

    }
}
