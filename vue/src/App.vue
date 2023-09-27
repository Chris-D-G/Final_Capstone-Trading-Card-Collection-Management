<template>
  <div id="app">
    <div id="nav">
      <nav-bar/>
    </div>
    <router-view />
  </div>
</template>
<script>
import NavBar from './components/NavBar.vue';

export default {
  name: 'app',
  components: {
    NavBar
  }
}
</script>

<style>
html, body {
  width: 100vw;
  height:100vh;
}

body {
    background: linear-gradient(-45deg, #AE9890, #A888A0, #E8B287, #4C2C2E);
    background-size: 400% 400%;
    animation: gradient 15s ease infinite;
    text-align: center;
    background-repeat: repeat-y;
}


@keyframes gradient {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}
</style>

