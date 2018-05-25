package overdiary.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import overdiary.domain.Rank;
import overdiary.domain.RankRepository;
import overdiary.domain.User;
import overdiary.domain.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RankService {
    private static final Logger log = LoggerFactory.getLogger(RankService.class);

    @Resource(name = "rankRepository")
    RankRepository rankRepository;

    @Resource(name = "userRepository")
    UserRepository userRepository;

    Iterable<Rank> findAll() {
        return rankRepository.findAll();
    }


    @Transactional
    public Iterable<Rank> createRankBoard() throws Exception {
        return rankRepository.findAll();
    }

    public User showMyRank(User user) throws Exception {
        String url = "https://playoverwatch.com/ko-kr/career/pc/" + user.getGameId() + "-" + user.getGameBattletag();
        Document doc = Jsoup.connect(url).get();
        String temp = doc.select("div.bar-text").text().replaceAll(" ", ",");
        String[] mostPlayTime = temp.split(",", 15);

        String heroNames = "";
        String playTimes = "";

        for (int i = 0; i < mostPlayTime.length;) {
            heroNames += mostPlayTime[i] + " ";
            playTimes += mostPlayTime[i + 1] + " ";
            i+=3;
        }

        log.info("heroNames : {}", heroNames);

        user.setChartHeroes(heroNames);
        user.setChartPlayTime(playTimes);
        return userRepository.save(user);
    }
}
