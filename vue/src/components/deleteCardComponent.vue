<template>
<div class="mb-4">
  <div class="deleteCard-container mx-3 mt-3">
    <img
      class="rounded-2 shadow-lg"
      v-bind:src="this.deleteCard.smallImgUrl"
      width="146px"
      height="204px"
      v-if="!showLargeImg"
      v-on:click="displayLargerImage"
    />
    <img
      class="large-addCard rounded-5 shadow-lg bg-transparent"
      v-bind:src="this.deleteCard.imageUrl"
      width="488px"
      height="680px"
      v-if="showLargeImg"
      v-on:click="displayLargerImage"
    />
    <p class="mt-1 mb-1 fw-semibold">{{ deleteCard.name }}</p>
    <!-- To DO: add boolean check for the checkbox -->
    <label for="deleteFromCollection"  class="fs-6 text-start me-2" > DELETE </label>
    <input type="checkbox" class="form-check-input" name="deleteFromCollection" v-model="localChecked" @change="emitChecked" >

    <div id="cardInfo" v-if="displayInfo" class="bg-white rounded-5 border border-1 border-white w-50 mx-auto" style="--bs-bg-opacity: .15;">
      Card Name : {{this.deleteCard.name}}<br/>
      Colors : {{this.deleteCard.colors}}<br/>
      Color-Identities : {{this.deleteCard.colorIdentity}}<br/>
      Set Information : {{this.deleteCard.setCode}} / {{this.addCard.setName}}<br/>
      Collector # : {{this.deleteCard.collectorNumber}}<br/>
      Legalities : {{this.deleteCard.legalities}}<br/>
      Layout : {{this.deleteCard.layout}}<br/>
      CMC : {{this.deleteCard.cmc}}<br/>
      EDHREC Rank : {{this.deleteCard.edhrecRank}}<br/><br/>
      Want more stats or purchase information? Click 
      <a :href="this.deleteCard.scryfallUrl">Here!</a>
    </div>
</div>

  <!-- <button class="btn btn-dark p-1 btn-sm btn-outline-light ">Delete</button> -->
  </div>
</template>

<script>
export default {
  name: "delete-card-component",
  props: {
    deleteCard: Object,
    isChecked: Boolean // Receive the checkbox state as a prop
  },

  methods: {
    emitChecked() {
      console.log(`Checkbox state changed for ${this.deleteCard.cardTitle}: ${this.localChecked}`);
      this.$emit("update:checked", this.localChecked);
    },
    displayLargerImage() {
      this.showLargeImg = !this.showLargeImg;
      this.displayInfo=!this.displayInfo;
    },
  },
  data() {
    return {
      showLargeImg: false,
      localChecked: this.isChecked, // Initialize localChecked with the prop value
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
</style>