<template> 
    <PlayGround v-if="$store.state.pk.status === 'playing'" />
    <MatchPanel v-if="$store.state.pk.status === 'matching'" />
    <ResultBoard v-if="$store.state.pk.loser != 'none'" />
    <div class="user-color" v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.a_id)">左下角</div>
    <div class="user-color" v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.b_id)">右上角</div>
</template>

<script>
import PlayGround from '../../components/PlayGround.vue'
import MatchPanel from '../../components/MatchPanel.vue'
import ResultBoard from '../../components/ResultBoard.vue'
import { onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'

export default {
    components: {
        PlayGround,
        MatchPanel,
        ResultBoard,
    },
    setup() { 
        const store = useStore();
        const socketUrl = `wss://app6506.acapp.acwing.com.cn/websocket/${store.state.user.token}/`;

        store.commit("updateIsRecord", false);

        let socket = null; 
        onMounted(()=> {
            store.commit("updateOpponent", {
                username: "未知的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            socket = new WebSocket(socketUrl); 

            socket.onopen = () => {
                console.log("connected !");
                store.commit("updateSocket", socket)
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if (data.event === "start-matching") {  // 匹配成功 
                    store.commit("updateOpponent", {
                        username: data.opponent_name,
                        photo: data.opponent_photo,
                    });
                    setTimeout(() => {
                        store.commit("updateStatus", "playing");
                    }, 200);
                    store.commit("updateGameMap", data.game);
                } else if (data.event === "move") {
                    console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;
                    snake0.set_direction(data.a_direction);
                    snake1.set_direction(data.b_direction);
                } else if (data.event === "result") {
                    console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;

                    if (data.loser === "all" || data.loser === "A") {
                        snake0.status = "die";
                    }
                    if (data.loser === "all" || data.loser === "B")  {
                        snake1.status = "die";
                    }
                    store.commit("updateLoser", data.loser);
                }
            }

            socket.onclose = () => {
                console.log("disconnected !");
            }
        });
        onUnmounted(() => {
            socket.close();
            store.commit("updateStatus", "matching");
            store.commit("updateLoser", "none");
        })
    }
}
</script>

<style scoped>
div.user-color {
    text-align: center;
    color: white;
    font-size: 30px;
    font-weight: 600;
}
</style>