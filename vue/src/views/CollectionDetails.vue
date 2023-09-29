<template>
  <div>
    <div  class="text-dark fs-1 text-center fw-bold text-decoration-underline title">
      {{this.collection.name}}
    </div>
    <div class="d-flex flex-wrap me-2">
      <card v-for="card in cards" v-bind:key="card.id" v-bind:card="card" />    
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import card from "../components/Card.vue";
export default {
  name: "collection-details",
  components: { card },
  data() {
    return {
      cards: [],
      collection: []
    };
  },

  created() {
    CollectionService.getAllCardsByCollection(this.$route.params.id).then(
      (response) => {
        this.cards = response.data;
      }
    );

    CollectionService.getCollectionById(this.$route.params.id).then(
      response =>{
        this.collection = response.data;
      }
    )
  },
};
</script>

<style>
.title{
  z-index: 1;
}

</style>