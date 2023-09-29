<template>
  <div class="d-flex flex-wrap me-2 justify-content-evenly ">
    <div
      class="collectionPreview d-flex flex-column align-items-center m-5 rounded-4 text-light py-2 border border-5 border-dark"
      v-for="collection in collectionList"
      v-bind:key="collection.id"
    >
   
      <h1>{{ collection.collectionName }}</h1>
      <img
        v-on:click="displayCards(collection.collectionId)"
        class="collection-image"
        v-bind:src="getCollectionImageURL(collection.tcgId)"
        width="200px"
        height="auto"
      />
      <p class="game fw-bolder fs-5" >{{ getGameName(collection.tcgId) }}</p>
      <div class="pb-1"><router-link :to="{name: 'Profile'}" class="username fs-4 p-2 m-0 ">{{collection.username}}</router-link></div>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import mtgImage from "@/assets/Updated MTG Collections Image Transparent.png";

export default {
  name: "collections",

  data() {
    return {
      collectionList: [],
      mtgImage
    };
  },

  created() {
    CollectionService.getAllCollections().then((response) => {
      this.collectionList = response.data;
      this.isLoading = false;
    });


    

  },
  methods: {
    getGameName(tcgId) {
      if (tcgId === 1) {
        return "Magic: The Gathering";
      }
    },
    getCollectionImageURL(tcgId) {
      if (tcgId == 1) {
        return mtgImage;
      }
    },

    displayCards(collectionId) {
      this.$router.push(`/allCollections/${collectionId}`);
    },    
  },
};
</script>

<style scoped>
.collectionPreview {
  width: 250px;
  background-color: #4c2c2eec;  
}

</style>