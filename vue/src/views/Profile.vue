<template>
  <!-- profile -->
  <div class="d-flex flex-column flex-lg-row justify-content-center">
    <div class="w-lg-50 rounded-5 mx-1 mx-lg-5 mt-lg-5" id="outer-shell">
      <div
        id="pic-username"
        class="d-flex flex-column flex-lg-row align-items-center rounded-5 m-lg-5"
      >
        <img
          v-bind:src="profile.profilePic"
          alt="Profile Image"
          class="rounded-circle border border-2 border-dark m-4"
          width="300px"
          height="auto"
        />
        <h1 class="fw-bold text-center mb-3 mb-lg-0 ms-lg-5 ps-lg-3">
          {{ profile.username.toUpperCase() }}
        </h1>
      </div>
      <div id="addFriend"
      class="d-flex flex-column"
      v-show="isFriended !== 'cannotFriend'">
      <button v-show="isFriended == 'true'"
      v-on:click.prevent="unFriend()">
        Remove Friend
      </button>
      <button v-show="isFriended == 'false'"
      v-on:click.prevent="addFriend()">
        Add Friend
      </button>
      </div>
      <div
        id="aboutme"
        class="d-flex flex-column align-items-start ms-lg-5 me-lg-5 text-wrap"
      >
        <p class="fs-2 fw-semibold mx-auto mx-lg-0 my-0">About Me:</p>
        <p class="fs-4 px-5 px-lg-0 mx-lg-0 text-center text-lg-start">
          {{ profile.aboutMe }}
        </p>
      </div>
      <div id="friendslist" class="">
        <p class="fw-semibold fs-2 text-lg-start ms-lg-5">
          Username is friends with:
        </p>
        <ul class="d-flex flex-column align-items-center d-lg-block ms-lg-3">
          <li
            class="fs-4 text-capitalize ms-lg-5 text-lg-start"
            v-for="friend in profile.friends"
            v-bind:key="friend"
          >
            {{ friend }}
          </li>
        </ul>
      </div>
    </div>
    <!-- MyCollections -->
    <aside
      id="myCollections"
      class="w-sm-100 w-lg-50 align-self-lg-stretch mx-1 mx-lg-5 mt-2 mt-lg-5 rounded-5"
    >
      <p class="fs-2 fw-semibold pt-lg-5 px-lg-5">My Collections</p>
      <ul class="ps-lg-5 d-flex flex-column align-items-center d-lg-block">
        <li><router-link
          class="fs-4 text-capitalize text-lg-start text-dark"
          v-for="collection in collectionList"
          v-bind:collection="collection"
          v-bind:key="collection.id"
          v-bind:to ="{name: 'CollectionCards', params:{id:collection.id}}"
          >{{ collection.name }}</router-link></li>                    
      </ul>
    </aside>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import ProfileService from "../services/ProfileService.js";
import FriendService from "../services/FriendService.js";

export default {
  created() {
    ProfileService.getAProfile(this.$route.params.username).then((response) => {
      if (response.status == 200) {
        this.profile = response.data;
        this.profile.profilePic = this.getPfp();
      }
    }),
    CollectionService.getMyCollections().then((response) => {
      if (response.status == 200) {
        this.collectionList = response.data;
      }
    });
    const loggedIn = this.$store.state.token != "";
    if(loggedIn && this.$store.state.user.username === this.profile.username){
      this.isFriended = FriendService.isFriended(this.profile.username);
    }
  },
  data() {
    return {
      profile: {
        username: "",
        profilePic: "",
        aboutMe: "",
        friends: [],
      },
      collectionList: [],
      isFriended: 'cannotFriend' //either 'cannotFriend', 'true' or 'false'
    };
  },
  methods: {
    getPfp() {
      switch(this.profile.profilePic) {
        case 'Logo-Default-Icon':
          return require('@/assets/Profile-Icons/Logo-Default-Icon.png');
        case 'MTG-Symbols':
          return require('@/assets/Profile-Icons/MTG-Symbols.png');
        case 'Plains-Default-Icon':
          return require('@/assets/Profile-Icons/Plains-Default-Icon.png');
        case 'Blue-Default-Icon':
          return require('@/assets/Profile-Icons/Blue-Default-Icon.png');
        case 'Swamp-Default-Icon':
          return require('@/assets/Profile-Icons/Swamp-Default-Icon.png');
        case 'Red-Default-Icon':
          return require('@/assets/Profile-Icons/Red-Default-Icon.png');
        case 'Forest-Default-Icon':
          return require('@/assets/Profile-Icons/Forest-Default-Icon.png');
      }
    },
    addFriend() {
      FriendService.addFriend(this.profile.username);
    },
    unFriend() {
      FriendService.unFriend(this.profile.username);
    }
  }
};
</script>

<style scoped>
#outer-shell,
#myCollections {
  background-color: #ffffffcc;
}
li {
  list-style: square;
}
</style>
