<template>
<div class="mb-4">
  <div class="card-container mx-3 mt-3 shadow-lg rounded-5">
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
  </div>
  <div class="card-title">
      <p class="mt-1 mb-1">{{ card.name }}</p>
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
    },
  },
  data() {
    return {
      showLargeImg: false,
    };
  },
};
</script>

<style scoped>
.large-card {
  z-index: 3;

}
p{
 font-weight: 500;
 color:#360a0c
}
</style>
