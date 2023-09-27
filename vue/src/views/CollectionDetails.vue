<template>
  <div class ="d-flex flex-wrap me-2">
    
      <card v-for="card in cards" v-bind:key="card.id" v-bind:card="card" />

  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js"
import card from "../components/Card.vue"
export default {
    name: 'collection-details',
    components:{card},
    data(){
        return{
            cards:[]
        }
    },

    created(){
        CollectionService.getAllCardsByCollection(this.$route.params.id).then(
            response =>{
                this.cards = response.data;
            }
        )
    }
}
</script>

<style>

</style>