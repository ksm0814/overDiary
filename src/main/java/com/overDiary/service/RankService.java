package com.overDiary.service;

import com.overDiary.domain.Rank;
import com.overDiary.domain.RankRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankService {
    private static final Logger log = LoggerFactory.getLogger(RankService.class);

    @Resource(name = "rankRepository")
    RankRepository rankRepository;

    Iterable<Rank> findAll() {
        return rankRepository.findAll();
    }


    @Transactional
    public Rank createRankBoard() throws Exception {
        String url = "https://overlog.gg/leaderboards";
        Document doc = Jsoup.connect(url).get();

        String names = doc.select("td.ContentCell-Player b").text();
        String levels = doc.select("td.ContentCell-Player em").text();
        String kds = doc.select("td.ContentCell-KD b").text();
        String ratios = doc.select("td.ContentCell-WinRatio b").text();
        List<String> mostHeroes = doc.select("td.ContentCell-MostHeros img").eachAttr("alt");
        String heroes = mostHeroes.stream ().map (i -> i.toString ()).collect (Collectors.joining(","));


        Rank rank;
        if(rankRepository.count() == 0) {
            rank= new Rank(names, levels, kds, ratios, heroes);
            return rankRepository.save(rank);
        }

        return rankRepository.findOne(1L).update(names, levels, kds, ratios, heroes);
    }

}
