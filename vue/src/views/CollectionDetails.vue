<template>
  <div>
    
      <card v-for="card in cards" v-bind:key="card.id"  />

  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js"
import card from "../components/Card.vue"
export default {
    name: 'collection-details',
    components:{card},
    props:{
        'collection.id':Number
    },
    data(){
        return{
            cards:[]
        }
    },

    created(){
        CollectionService.getAllCollections().then(
            response =>{
                this.cards = response.data;
            }
        )
    }
}
</script>

<style>

</style>