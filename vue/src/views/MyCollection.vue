<template>
  <div>
    <div
      class="collectionPreview d-flex flex-column align-items-center m-5 rounded-5 text-dark py-5"
      v-for="collection in collectionList"
      v-bind:key="collection.id"
    >
      <h2 class="">{{ collection.name }}</h2>
      <img
        v-on:click="displayCards(collection.id)"
        class="collection-image"
        v-bind:src="getCollectionImageURL(collection.tcgId)"
        width="300px"
        height="auto"
      />
      <p class="">{{ getGameName(collection.tcgId) }}</p>
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import mtgImage from "@/assets/MTG-Collection-Icon.png";

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
      this.$router.push(`/allCollections/${collectionId}`);
    },
  },
};
</script>

<style scoped>
.collectionPreview {
  /* padding: 5px;
  margin: 5px;
  border: 1px;  
  border-radius: 20px; */
  width: 250px;
  background-color: antiquewhite;
  
}
</style>