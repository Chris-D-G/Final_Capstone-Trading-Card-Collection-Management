<template>
    <div class="card-container">
        <img v-bind:src="thumbnailImageURL" width="146px" height="204px" v-on:click="displayLargerImage">
        <img class="large-card" v-bind:src="normalImageURL" width="488px" height="680px" v-show="showLargeImg" v-on:click="displayLargerImage">
        <div class="card-title">
            <h2>{{card.title}}</h2>
        </div>
    </div>
</template>

<script>
export default {
    name: "card-component",
    props: ['card'],
    computed:{
        thumbnailImageURL(){
            return require(this.card.thumbnailImageURL);
        },
        normalImageURL(){
            return require (this.card.normalImageURL)
        }
    },
    methods:{
        displayLargerImage(){
            this.showLargeImg = !this.showLargeImg;
        }
    },
    data(){
        return{
            showLargeImg: "false",          
        }
    }
}
</script>

<style scoped>
.large-card{    
    z-index: 3;
}

</style>