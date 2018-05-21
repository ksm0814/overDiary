package overdiary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import overdiary.domain.User;
import overdiary.helper.LoginUser;
import overdiary.service.RankService;
import overdiary.service.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/ranks")
public class RankController {
    private static final Logger log = LoggerFactory.getLogger(RankController.class);

    @Resource(name = "rankService")
    RankService rankService;

    @Resource(name = "userService")
    UserService userService;

    @GetMapping("")
    public String showHighRanker(Model model) throws Exception {
        model.addAttribute("Rank",rankService.createRankBoard());
        return "/rank/list";
    }

    @GetMapping("/show")
    public String showMyRank(@LoginUser User loginUser,  Model model) throws Exception{
        model.addAttribute("User", loginUser);
        return "/rank/charts";
    }

}
