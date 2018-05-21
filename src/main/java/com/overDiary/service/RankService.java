package com.overDiary.service;

import com.overDiary.domain.Rank;
import com.overDiary.domain.RankRepository;
import com.overDiary.domain.User;
import com.overDiary.domain.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        String url = "https://overlog.gg/leaderboards";
        Document doc = Jsoup.connect(url).get();


        String[] ranks = doc.select("tbody").select("td").text().split("PC");

        List<String> mostHeroes = new ArrayList<>();
        for (Element img : doc.select("tbody").select("td.ContentCell-MostHeros")) {
            mostHeroes.add(img.select("img").eachAttr("alt").toString());
        }

        List<String> rankInfo = new ArrayList<>();
        for (String rank : ranks) {
            String list = "";
            for (String info : rank.split(" |Lv. |: 1 |/ |%")) {
                if (info.equals("") || info.equals(" "))
                    continue;
                list += info + ",";
            }
            rankInfo.add(list);
        }
        rankInfo.remove(0);


        Rank rank;
        if (rankRepository.count() == 0) {
            for (int i = 0; i < rankInfo.size(); i++) {
                String[] info = rankInfo.get(i).split(",");
                rank = new Rank(info[0], info[1], info[2], info[3], info[4], mostHeroes.get(i));
                rankRepository.save(rank);
            }
            return rankRepository.findAll();
        }


        Iterable<Rank> DBRank = rankRepository.findAll();
        for (int i = 0; i < ((List<Rank>) DBRank).size(); i++) {
            Rank oldRank = ((List<Rank>) DBRank).get(i);
            String[] info = rankInfo.get(i).split(",");
            oldRank.update(info[0], info[1], info[2], info[3], info[4], mostHeroes.get(i));
        }
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
