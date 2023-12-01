package com.kob.backend.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Integer id;
    private Integer botId;  // botId = -1表示玩家亲自操作，否则表示用AI对战
    private String botCode;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;

    private boolean checkTailIncreasing(int step) {   // 检查当前回合蛇是否变长
        if (step <= 10) {
            return true;
        }
        if (step % 3 == 1) {
            return true;
        }
        return false;

    }

    public List<Cell> getCells() {
        List<Cell> res = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;

        res.add(new Cell(x, y));
        for (int d: steps) {
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!checkTailIncreasing(++step)) {
                res.remove(0);
            }
        }
        return res;
    }

    public String getStepString() {
        StringBuilder res = new StringBuilder();
        for (int step: steps) {
            res.append(step);
        }
        return res.toString();
    }
}
