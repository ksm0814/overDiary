package overdiary.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overdiary.domain.Alarm;
import overdiary.domain.AlarmRepository;
import overdiary.domain.Article;
import overdiary.domain.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmService {
    private static final Logger log = LoggerFactory.getLogger(AlarmService.class);

    private AlarmRepository alarmRepository;

    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public Iterable<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public void create(Article article, Iterable<User> allUser) {
        for (User user : allUser) {
            Alarm alarm = new Alarm(article.getTitle(), user, article.getArticleKey());
            alarmRepository.save(alarm);
        }
    }

    @Transactional
    public List<Alarm> sendAlarm(User user) {
        List<Alarm> recentAlarms = alarmRepository.findByTargetUserAndIsOpened(user, false);
        for (Alarm alarm : recentAlarms) {
            alarm.setOpened(true);
            //opened 된거 확인 필요
        }
        return recentAlarms;
    }

    public void removeAlarm(long alarmKey) {
        alarmRepository.delete(alarmKey);

    }
}
