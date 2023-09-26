<template>
<div>
  <div class="collectionPreview" 
  v-for="collection in collectionList" 
  v-bind:key="collection.id">
    <h2>{{collection.name}}</h2>
    <img v-on:click="displayCards(collection.id)" class="collection-image" v-bind:src="getCollectionImageURL(collection.tcgId)">
    <p>{{getGameName(collection.tcgId)}}</p>
  </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js';

export default {
    name: "collections",

    data() {
        return {
            collectionList : []
        }
    },

    created() {
        CollectionService.getAllCollections().then(
            (response) => {
                this.collectionList = response.data;
                this.isLoading = false;
            }
        );
    },
    methods: {
        getGameName(tcgId) {
            if(tcgId === 1) {
                return "Magic: The Gathering";
            }
        },
        getCollectionImageURL(tcgId) {
             if(tcgId == 1) {
                 return "MTG-Collection-Icon.png";
             }
        },

        displayCards(collectionId){
            this.$router.push(`/allCollections/${collectionId}`)
        }
    }
}
</script>

<style scoped>
.collectionPreview {

    padding: 5px;
    margin: 5px;
    border: 1px;
    width: 250px;
    background-color: antiquewhite;
    border-radius: 20px;
  
}
</style>