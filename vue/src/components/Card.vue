<template>
<div class="mb-4">
  <div class="card-container mx-3 mt-3 d-flex flex-column">
    
    <img
      class="rounded-2 shadow-lg"
      v-bind:src="this.card.smallImgUrl"
      width="146px"
      height="204px"
      v-if="!showLargeImg"
      v-on:click="displayLargerImage"
    />
    <img
      class="large-card rounded-5 shadow-lg bg-transparent"
      v-bind:src="this.card.imageUrl"
      width="488px"
      height="680px"
      v-if="showLargeImg"
      v-on:click="displayLargerImage" 
    />
    <p class="mt-1 mb-1 fw-semibold text-wrap">{{ card.name }}</p>

    <div id="cardInfo" v-if="displayInfo" class="bg-white rounded-5 border border-1 border-white w-25 ms-1 d-inline" style="--bs-bg-opacity: .15;" >
      Card Name : {{this.card.name}}<br/>
      Colors : {{this.card.colors}}<br/>
      Color-Identities : {{this.card.colorIdentity}}<br/>
      Set Information : {{this.card.setCode}} / {{this.card.setName}}<br/>
      Collector # : {{this.card.collectorNumber}}<br/>
      Legalities : {{this.card.legalities}}<br/>
      Layout : {{this.card.layout}}<br/>
      CMC : {{this.card.cmc}}<br/>
      EDHREC Rank : {{this.card.edhrecRank}}<br/>
      Quantity : {{this.card.qty}}<br/><br/>
      Want more stats or purchase information? Click 
      <a :href="this.card.scryfallUrl">Here!</a>
    </div>

  </div>

  <!-- <button class="btn btn-dark p-1 btn-sm btn-outline-light ">Delete</button> -->
  </div>
</template>

<script>
export default {
  name: "card-component",
  props: ["card"],

  methods: {
    displayLargerImage() {
      this.showLargeImg = !this.showLargeImg;
      this.displayInfo=!this.displayInfo;
    },
  },
  data() {
    return {
      showLargeImg: false,
      displayInfo:false
    };
  },

};
</script>

<style scoped>
.large-card {
  z-index: 3;

}
p{
 color:#360a0c
}
#cardInfo{
  color:#360a0c;
  font-weight: 500;
  
}
</style>
