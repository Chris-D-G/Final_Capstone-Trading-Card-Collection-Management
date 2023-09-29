<template>
  <div class="d-flex flex-wrap me-2 justify-content-evenly" > 
    <div
      v-for="collection in collectionList"
      v-bind:key="collection.id"
    >
    <div  class="collectionPreview d-flex flex-column align-items-center m-4 rounded-4 text-light py-2 border border-5 border-dark">
      <h2>{{ collection.name }}</h2>
      <img
        v-on:click="displayCards(collection.id)"
        class="collection-image "
        v-bind:src="getCollectionImageURL(collection.tcgId)"
        width="200px"
        height="auto"
      />
      <p class="game fw-bolder fs-5">{{ getGameName(collection.tcgId) }}</p>
      <button class="btn" v-on:click="addToCollection(collection.id)" >Add To My Collection</button>
    </div>
    <button class="btn btn-outline-danger btn-dark" v-on:click="deleteCollection(collection.id)" >Delete Collection</button>
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
    };
  },

  created() {
    CollectionService.getMyCollections().then((response) => {
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

    addToCollection(collectionId){
      this.$router.push(`/collections/${collectionId}/add`)
    },

    deleteCollection(collectionId){
      confirm("Are you sure?");
      CollectionService.deleteCollection(collectionId);
      this.$router.push(`/myCollections`)
    }
  },
};
</script>

<style scoped>
.collectionPreview {
  width: 250px;
  background-color: #4c2c2eec;  
}
button{
  color:rgb(202, 114, 114);
}


</style>