<template>
    <div class="matchpanel">
    <div class="row">
        <div class="col-6">
            <div class="user-photo">
                <img :src="$store.state.user.photo" alt="">
            </div>
        </div>
        <div class="col-6">
            <div class="user-username">
                {{ $store.state.user.username }}
            </div>
        </div>
    </div>
    </div>
</template>

<script>


export default {
    components: {
        
    }
}
</script>

<style scoped>
div.matchpanel {
    width: 60vw;
    height: 70vh;
    margin: 40px auto;
    background-color: lightblue;
}
</style>
