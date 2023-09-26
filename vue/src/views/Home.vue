<template>
  <div class="home">
    <body>
    <h1>Welcome to Cardomancy</h1>
    <p>Build, Create, and Showcase</p>
    <div>
    <img :src="registerBubble" v-if="loggedin">
    <h3 v-if="!loggedin"></h3>
    </div>
    </body>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService';


export default {
  name: "home",
  loggedin: false,
  isLoading: true,

  window:onload = function() {
      this.checkLoginStatus();
  },

  data() {
    return {
      topCollections: [],
      isLoading: true
      
    }
  },

  created() {
    CollectionService.getFavCollections().then(
      (response) => {
        this.topCollections = response.data;
        this.isLoading = false;
      }
    ) 
      
    
  },

  method: {
    checkLoginStatus() {
      let token = this.$state.state.token;
      if(token != '') {
        this.loggedin = true;
      } else {
        this.loggedin = false;
      }
    }
  }
};
</script>


<style scoped>
html, body {
  width: 100vw;
  height:100vh;
}

body {
    background: linear-gradient(-45deg, #AE9890, #A888A0, #E8B287, #4C2C2E);
    background-size: 400% 400%;
    animation: gradient 15s ease infinite;
    text-align: center;
}
h1 {
  padding-top: 50px;
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