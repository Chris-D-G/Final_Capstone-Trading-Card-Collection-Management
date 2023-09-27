<template>
  <div id="app">
    <div id="nav" class="position-fixed top-0 start-0 z-3 w-100">
      <nav-bar />
    </div>
    <router-view class="page-view" />
  </div>
</template>
<script>
import NavBar from "./components/NavBar.vue";

export default {
  name: "app",
  components: {
    NavBar,
  },
};
</script>

<style>
html,
body {
  width: 100vw;
  height: 100vh;
}
.page-view {
  position: relative;
  top: 220px;
}
@media screen and (max-width: 992px;) {
  .nav {
    display: relative;
  }
}

body {
  /* background: linear-gradient(-45deg, #ae9890, #a888a0, #e8b287, #4c2c2e);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  text-align: center;
  background-repeat: repeat-y; */
  background-color: lightgray;
}

/* @keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
} */
</style>

