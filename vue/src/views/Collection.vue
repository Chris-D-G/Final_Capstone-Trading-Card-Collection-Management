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
      <p class="game fs-5" >{{ getGameName(collection.tcgId) }}</p>
      <div class="pb-1"><router-link :to="{name: 'Profile'}" class="username fs-4 text-uppercase fw-semibold p-2 m-0 text-white ">{{collection.username}}</router-link></div>
    </div>
    <!-- <div
        id="collectionInfo"
        class=" bg-white rounded-5 border border-1 border-white fw-bold d-flex flex-column justify-content-center align-items-start mx-1 p-4 h-auto"
        style="--bs-bg-opacity: 0.15"
      >
        <p class="my-0 py-0">Card Name : {{ this.card.name }}</p>
        <p class="my-0 py-0">Colors : {{ this.card.colors }}</p>
        <p class="my-0 py-0">
          Color-Identities : {{ this.card.colorIdentity }}
        </p>
        <p class="my-0 py-0">
          Set Information : {{ this.card.setCode }} / {{ this.card.setName }}
        </p>
        <p class="my-0 py-0">Collector # : {{ this.card.collectorNumber }}</p>
        <p class="my-0 py-0">Play Formats:</p>
        <p
          v-bind:key="legality"
          v-for="(legality, index) in this.card.legalities"
          v-bind:legality="legality"
          class="mb-1 ms-5 px-2 fw-semibold text-white"
          v-bind:class="
            String(legality) == 'legal'
              ? 'bg-success rounded-pill'
              : 'bg-danger rounded-pill'
          "
        >
          {{ index }}:
          {{ String(legality) == "not_legal" ? "not legal" : "legal" }}
        </p>
        <p class="my-0 py-0">Layout : {{ this.card.layout }}</p>
        <p class="my-0 py-0">CMC : {{ this.card.cmc }}</p>
        <p class="my-0 py-0">EDHREC Rank : {{ this.card.edhrecRank }}</p>
        <p class="my-0 py-0" v-show="this.card.qty!=0 || this.card.qty != null || this.card.qty !=''"> Quantity : {{ this.card.qty }}</p>
        <p class="my-0 py-3">
          Want more stats or purchase information? Click
          <a :href="this.card.scryfallUrl">Here!</a>
        </p>
      </div> -->
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