package com.kob.botrunningsystem.service.impl;

import com.kob.botrunningsystem.service.BotRunningService;
import com.kob.botrunningsystem.service.impl.utils.BootPool;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {
    public final static BootPool botPoll = new BootPool();
    @Override
    public String addBot(Integer userId, String botCode, String input) {
        System.out.println("add bot: " + userId);
        System.out.println(botCode + " " + input);
        botPoll.addBot(userId, botCode, input);
        return "add bot success";
    }
}
