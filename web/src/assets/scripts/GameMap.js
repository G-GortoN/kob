import { AcGameObjects } from "./AcGameObject";

export class GameMap extends AcGameObjects {
    constructor(ctx, parent) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;

        this.rows = 13;
        this.cols = 14;
    }

    start() {

    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {
        this.ctx.fillStyle = 'lightblue';
        this.ctx.fillRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    }
}