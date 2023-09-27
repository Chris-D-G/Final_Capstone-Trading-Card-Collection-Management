<template>
  <div class="home">
    <body>
    <h1>Welcome to Cardomancy</h1>
    <p>Build, Create, and Showcase</p>
    <div>
    <h2 v-if="loggedin"></h2>
    <h3 v-if="!loggedin"></h3>
    </div>
    <!-- bootstrap carousel -->
    <div id="MTG-carousel" class="carousel slide carousel-fade ">
      <div class="carousel-inner ">
        <div class="carousel-item active">
          <img src="../assets/wayne-low-OvN4OkhkTLo-unsplash.jpg" class="d-block w-75 m-auto border border-dark border-1 rounded-5" alt="...">
        </div>
        <div class="carousel-item">
          <img src="../assets/ryan-quintal-Vp8RnXSnJ6I-unsplash.jpg" class="d-block w-75 m-auto border border-dark border-1 rounded-5" alt="...">
        </div>
        <div class="carousel-item">
          <img src="../assets/depositphotos_469022238-stock-photo-bangkok-thailand-may-2021-magic.jpg" class="d-block w-75 m-auto border border-dark border-1 rounded-5" alt="...">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#MTG-carousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#MTG-carousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <!-- end carousel -->
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
    );

      
    
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
h1 {
  padding-top: 50px;
}
</style>