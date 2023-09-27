<template>
    <div class="card-container mx-3 mt-3" >
        <img class="rounded-2 " v-bind:src="this.card.smallImgUrl" width="146px" height="204px" v-if="!showLargeImg" v-on:click="displayLargerImage">
        <img class="large-card rounded-5" v-bind:src="this.card.imageUrl" width="488px" height="680px" v-if="showLargeImg" v-on:click="displayLargerImage" >
        <div class="card-title">
            <h2>{{this.card.title}}</h2>
        </div>
    </div>
</template>

<script>
export default {
    name: "card-component",
    props: ['card'],
   
    methods:{
        displayLargerImage(){
            this.showLargeImg = !this.showLargeImg;
        }
    },
    data(){
        return{
            showLargeImg: false,          
        }
    }
}
</script>

<style scoped>
.large-card{    
    z-index: 3;
}


</style>