package com.overDiary.controller;

import com.overDiary.service.RankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/ranks")
public class RankController {
    private static final Logger log = LoggerFactory.getLogger(RankController.class);

    @Resource(name = "rankService")
    RankService rankService;

    @GetMapping("")
    public String showHighRanker(Model model) throws Exception {
        model.addAttribute("Rank", rankService.createRankBoard());
        return "/rank/list";
    }

}
