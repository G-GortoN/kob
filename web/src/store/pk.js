export default {
    state: {
        status: "matching", // matching表示正在匹配, playing表示正在对战
        socket: null,
        opponent_name: "",
        opponent_photo: "",
    },
    getters: {
    },
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        updateOpponent(state, opponent) {
            state.opponent_name = opponent.username;
            state.opponent_photo = opponent.photo;
        },
        updateStatus(state, status) {
            state.status = status;
        }
    },
    actions: {
    },
    modules: {
    }
}