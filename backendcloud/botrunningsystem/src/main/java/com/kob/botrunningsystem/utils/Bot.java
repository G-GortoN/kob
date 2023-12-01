package com.kob.botrunningsystem.utils;

public class Bot implements com.kob.botrunningsystem.utils.BotInterface {
    static class Cell {
        public int x, y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    @Override
    public Integer nextMove(String input) {
        String[] strs = input.split("#");
        int[][] map = new int[13][14];
        for (int i = 0, k = 0; i < 13; i++) {
            for (int j = 0; j < 14; j++) {
                if (strs[0].charAt(k)  == '1') {

                }
            }
        }
    }
}
