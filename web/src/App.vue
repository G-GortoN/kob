<template>
<NavBar />
<router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import $ from 'jquery'

export default {
  components: {
    NavBar
  },
  setup() {
    $.ajax ({
      url: "http://127.0.0.1:3000/user/account/token/",
      type: "post",
      data: {
        username: 'a',
        password: 'pa',
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });

    $.ajax ({
      url: "http://127.0.0.1:3000/user/account/info/",
      type: "get",
      headers: {
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiODNkYTU2NjhmODU0YjNkOTZhMmQ3YmUwZTgxYjA0MCIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcwMDIxODY5OCwiZXhwIjoxNzAxNDI4Mjk4fQ.RjJHfrFo9iV6uE3JtCBJ6r5BXlEwTfOWchMS5eF0pc4",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    });

    $.ajax ({
      url: "http://127.0.0.1:3000/user/account/register/",
      type: "post",
      data: {
        username: "root2",
        password: "654321",
        confirmedPassword: "654321",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    })
  }
}
</script>

<style>
body {
  background-image: url("./assets/images/background.png");
  background-size: cover;
}
</style>
