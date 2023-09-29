<template>
  <div class="ms-5 me-5">
    <h1  class="text-dark fs-1 text-center fw-bold title mt-3">
      {{this.collection.name}}
    </h1>
    <div>
    <button class="btn btn-dark mt-4 mb-4" >Alphabetize</button>
    </div>
    <div class="d-flex flex-wrap me-2 justify-content-evenly">
      <card v-for="card in cards" v-bind:key="card.id" v-bind:card="card"/>
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
      alphcards:[],
      collection: [],
      // search: "",
      // searched: false,
      methods:{
        changeDeck(){
          this.cards = this.cards.toSorted();
        }

      }
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
    );

    // CollectionService.getAllCardsByCollectionAlph(this.$route.params.id).then(
    //    (response) => {
    //      this.alphcards =response.data;
    //    }

    // )
  },
};
</script>

<style>
.title{
  z-index: 1;
}

</style>