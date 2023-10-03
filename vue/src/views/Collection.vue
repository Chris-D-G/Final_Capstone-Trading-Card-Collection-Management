<template>
  <div class="d-flex flex-wrap me-2 justify-content-evenly align-items-stretch">
    <div
      class="collectionPreview d-flex flex-column align-items-center justify-content-between m-5 rounded-4 text-light py-2 px-3 border border-3 border-dark"
      v-for="collection in collectionList"
      v-bind:key="collection.id"
    >
   
      <p class="fs-2 fw-bolder px-2 ">{{ collection.collectionName }}</p>
      <img
        v-on:click="displayCards(collection.collectionId)"
        class="collection-image"
        v-bind:src="getCollectionImageURL(collection.tcgId)"
        width="200px"
        height="auto"
      />
      <p class="game fs-5" >{{ collection.quantity }}</p>
      <div class="pb-1"><router-link :to="{name: 'Profile'}" class="username fs-4 text-uppercase fw-semibold p-2 m-0 text-white ">{{collection.username}}</router-link></div>
      <div
        id="collectionInfo"
        class=" bg-white rounded-5 border border-1 border-white fw-bold d-flex flex-column justify-content-center align-items-start mx-1 p-4 h-auto"
        style="--bs-bg-opacity: 0.15"
      >
        <p class="my-0 py-0">Total Cards: {{  }}</p>
        
      </div>
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
      mtgImage,
      cardCount: 0
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
      this.$router.push(`/collections/${collectionId}`);
    },    
  },

};
</script>

<style scoped>
.collectionPreview {
  width: 275px;
  background-color: #4c2c2eec;  
}
</style>