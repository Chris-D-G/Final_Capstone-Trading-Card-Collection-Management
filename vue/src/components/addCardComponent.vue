<template>
<div class="mb-4">
  <div class="addCard-container mx-3 mt-3">
    <img
      class="rounded-2 shadow-lg"
      v-bind:src="this.addCard.smallImgUrl"
      width="146px"
      height="204px"
      v-if="!showLargeImg"
      v-on:click="displayLargerImage"
    />
    <img
      class="large-addCard rounded-5 shadow-lg bg-transparent"
      v-bind:src="this.addCard.imageUrl"
      width="488px"
      height="680px"
      v-if="showLargeImg"
      v-on:click="displayLargerImage"
    />
    <p class="mt-1 mb-1 fw-semibold">{{ addCard.name }}</p>
    <!-- To DO: add boolean check for the checkbox -->
    <label for="addToCollection"  class="fs-6 text-start me-2" > ADD </label>
    <input type="checkbox" class="form-check-input" name="addToCollection" v-model="localChecked" @change="emitChecked" >

    <div id="cardInfo" v-if="displayInfo" class="bg-primary rounded-5 border border-2 border-danger-subtle w-50 mx-auto" style="--bs-bg-opacity: .5;">
      Card Name : {{this.addCard.name}}<br/>
      Colors : {{this.addCard.colors}}<br/>
      Color-Identities : {{this.addCard.colorIdentity}}<br/>
      Set Information : {{this.addCard.setCode}} / {{this.addCard.setName}}<br/>
      Collector # : {{this.addCard.collectorNumber}}<br/>
      Legalities : {{this.addCard.legalities}}<br/>
      Layout : {{this.addCard.layout}}<br/>
      CMC : {{this.addCard.cmc}}<br/>
      EDHREC Rank : {{this.addCard.edhrecRank}}<br/><br/>
      Want more stats or purchase information? Click 
      <a :href="this.addCard.scryfallUrl">Here!</a>
    </div>
</div>

  <!-- <button class="btn btn-dark p-1 btn-sm btn-outline-light ">Delete</button> -->
  </div>
</template>

<script>
export default {
  name: "add-card-component",
  props: {
    addCard: Object,
    isChecked: Boolean // Receive the checkbox state as a prop
  },

  methods: {
    emitChecked() {
      console.log(`Checkbox state changed for ${this.addCard.cardTitle}: ${this.localChecked}`);
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