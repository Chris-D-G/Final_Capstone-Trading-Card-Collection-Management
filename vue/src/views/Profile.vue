<template>
   <!-- profile -->
    <div class="d-flex flex-column  flex-lg-row mt-2 ms-3 me-3 rounded-5 justify-content-around" id="outer-shell">
        <div class="" >
            <div id="pic-username" class="d-flex flex-column  flex-lg-row align-items-center rounded-5 m-2  ">
                <img v-bind:src="profile.profilePic" alt="Profile Image"
                    class="rounded-circle m-4 m-lg-5 border border-2 border-dark" width="300px" height="auto" />
                <h1 class="me-sm-0 me-lg-5 fw-bold text-center">{{profile.username.toUpperCase()}}</h1>
            </div>
            <div id="aboutme" class="ms-5 pe-3">
                <p class=" fs-2 fw-semibold">About Me:</p>
                <p class="fs-4">{{profile.aboutMe}}</p>
            </div>
            <div id="friendslist" class="">
                <p class=" mb-4 fw-semibold fs-2 ms-5">Username is friends with:</p>
                <ul class="ms-5">
                    <li class="my-3 ms-2 fs-4 text-capitalize" v-for="friend in profile.friends" v-bind:key="friend" >{{friend}}</li>                    
                </ul>
            </div>
        </div>

        <aside id="myCollections" class="pt-lg-5 w-25">
            <p class=" fs-2 fw-semibold ms-5 ms-lg-2 ">My Collections</p>
            <ul class="ms-5">
                <li class="my-3 ms-2 fs-4 text-capitalize">collection 1</li>                
            </ul>
        </aside>
    
    </div>
</template>

<script>
import ProfileService from "../services/ProfileService.js"

export default {
    created(){
        ProfileService.getMyProfile()
        .then((response)=>
            this.profile= response.data)
    },
    data(){
        return{
            profile:{
                username: "",
                profilePic: "",
                aboutMe: "",
                friends:[]
            }
        }
    }


}
</script>

<style>
#outer-shell{
    background-color: #ffffffcc
}


</style>
