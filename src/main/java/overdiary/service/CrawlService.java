package overdiary.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import overdiary.domain.Rank;
import overdiary.domain.RankRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlService {
    private static final Logger LOG = LoggerFactory.getLogger(CrawlService.class);

    @Resource(name = "rankRepository")
    RankRepository rankRepository;


    public int saveRankBoard() throws Exception {
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

        Iterable<Rank> DBRank = rankRepository.findAll();
        if(((List<Rank>) DBRank).isEmpty()){
            for (int i = 0; i < rankInfo.size(); i++) {
                String[] info = rankInfo.get(i).split(",");
                rank = new Rank(info[0], info[1], info[2], info[3], info[4], mostHeroes.get(i));
                rankRepository.save(rank);
            }
            return 0;
        }

        for (int i = 0; i < ((List<Rank>) DBRank).size(); i++) {
            Rank oldRank = ((List<Rank>) DBRank).get(i);
            String[] info = rankInfo.get(i).split(",");
            oldRank.update(info[0], info[1], info[2], info[3], info[4], mostHeroes.get(i));
        }
        return 0;
    }
}
