package com.kob.backend.consumer.utils;

import java.util.Random;

public class Game {
    private final Integer rows;
    private final Integer cols;
    private final Integer innerWallsCount;
    private final int[][] map;
    private final static int[] dx = {-1, 0, 1, 0};
    private final static int[] dy = {0, 1, 0, -1};

    public Game(Integer rows, Integer cols, Integer innerWallsCount) {
        this.rows = rows;
        this.cols = cols;
        this.innerWallsCount = innerWallsCount;
        this.map = new int[rows][cols];
    }

    public int[][] getMap() {
        return map;
    }

    private boolean checkConnectivity(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        map[sx][sy] = 1;
        for (int i = 0; i < 4; i++) {
            int x = sx + dx[i];
            int y = sy + dy[i];
            if (x >= 0 && x < this.rows && y >= 0 && y < this.cols && map[x][y] == 0) {
                if (checkConnectivity(x, y, tx, ty)) {
                    map[sx][sy] = 0;
                    return true;
                }
            }
        }
        map[sx][sy] = 0;
        return false;
    }


    private boolean draw() {  // 画地图
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                map[i][j] = 0;
            }
        }

        // 给四周加障碍物
        for (int i = 0; i < this.rows; i++) {
            map[i][0] = map[i][this.cols - 1] = 1;
        }
        for (int j = 0; j < this.cols; j++) {
            map[0][j] = map[this.rows - 1][j] = 1;
        }

        Random random = new Random();
        // 创建随机障碍物
        for (int i = 0; i < this.innerWallsCount / 2; i++) {
            for (int j = 0; j < 1000; j++) {
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);
                if (map[r][c] == 1 || map[this.rows - 1 - r][this.cols - 1 - c] == 1) {
                    continue;
                }
                if (r == this.rows - 2 && c == 1 || c == this.cols - 2 && r == 1) {
                    continue;
                }
                map[r][c] = map[this.rows - 1 - r][this.cols - 1 - c] = 1;
                break;
            }
        }
        return checkConnectivity(this.rows - 2, 1, 1, this.cols - 2);
    }

    public void createGameMap() {
        for (int i = 0; i < 1000; i++) {
            if (draw()) {
                break;
            }
        }
    }
}
