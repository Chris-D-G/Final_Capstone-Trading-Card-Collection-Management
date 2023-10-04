<template>
  <div class="d-flex justify-content-center">
    <div class="d-flex flex-column border border-black rounded-3 m-2 w-75">
      <form method="post" action="">
        <div class="d-flex border-bottom border-black p-2">
          <label for="receiver" class="ms-2 my-1">TO:</label>
          <p
            name="receiver"
            id="receiver"
            required="required"
            class="ms-2 my-1 w-50"
          >{{message.messageReceiver}}</p>
        </div>
        <div class="d-flex flex-column p-2">
          <div class="d-flex">
            <label for="message" class="ms-2 my-1 form-label">Trade Request:</label>
            <div class="d-flex flex-column p-2">
            <select id="receiverCollections" v-model="receiverCollectionName" @change="setReceiverCollectionId()">
          <option selected disabled hidden value="">{{message.messageReceiver}} Collections</option>
          <option :value="collection.name" v-for="collection in receiverCollectionList" v-bind:key="collection.id" v-bind:collection="collection">{{collection.name}}</option>
        </select>
            <select id="receiverCards" v-model="desiredCardName">
          <option selected disabled hidden value="">{{message.messageReceiver}} Cards</option>
          <option :value="card.name" v-for="card in receiverCards" v-bind:key="card.id" v-bind:card="card">{{card.name}}</option>
        </select>
            </div>
            <div class="d-flex flex-column p-2">
            <select id="senderCollections" v-model="senderCollectionName" @change="setSenderCollectionId()">
          <option selected disabled hidden value="">{{message.messageSender}} Collections</option>
          <option :value="collection.name" v-for="collection in senderCollections" v-bind:key="collection.id" v-bind:collection="collection">{{collection.name}}</option>
        </select>
            <select id="senderCards" v-model="tradeOfferName">
          <option selected disabled hidden value="">{{message.messageSender}} Cards</option>
          <option :value="card.name" v-for="card in senderCards" v-bind:key="card.id" v-bind:card="card">{{card.name}}</option>
        </select>
            </div>
          </div>
          <input type="submit" class="my-1 btn btn-primary align-self-center" v-on:click.prevent="submitNewMessage(message)" />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import MessageService from '../services/MessageService';
import CollectionService from "../services/CollectionService.js";
import ProfileService from "../services/ProfileService.js";


export default {
  name: "request-trade-form",
  data() {
    return {
      message: {        
        messageSender: this.$store.state.user.username,
        messageReceiver: "",
        messageText: "",        
        read: false,
      },
      receiverCollectionList: [],
      senderCollections: [],
      receiverCards: [],
      senderCards: [],
      receiverCollectionName: "",
      senderCollectionName: "",
      receiverCollectionId: 0,
      senderCollectionId: 0,
      desiredCardName: "",
      tradeOfferName: ""
    };
  },
  methods:{
    submitNewMessage(message){
      MessageService.sendNewMessage(message)
      this.$router.go(this.$router.push("/messages"))
    },
    setReceiverCollectionId() {
    console.log("setCollectionId method called");
    console.log("Current Receiver Collection Id:", this.receiverCollectionId);
      if (this.receiverCollectionName != "") {
        this.receiverCollectionList.forEach((collection) => {
          if (collection.name == this.receiverCollectionName) {
            this.receiverCollectionId = collection.id;
          }
        });
      }
      CollectionService.getAllCardsByCollection(this.receiverCollectionId).then(
      (response) => {
        this.receiverCards = response.data;
        });
    },
    setSenderCollectionId() {
    console.log("setCollectionId method called");
    console.log("Current Sender Collection Id:", this.senderCollectionId);
      if (this.senderCollectionName != "") {
        this.senderCollections.forEach((collection) => {
          if (
            collection.name
              .toLowerCase().trim()
              .includes(this.senderCollectionName.toLowerCase().trim())
          ) {
            this.senderCollectionId = collection.id;
          }
        });
      }
      CollectionService.getAllCardsByCollection(this.senderCollectionId).then(
      (response) => {
        this.senderCards = response.data;
        });
    },
    
  },
  created() {
    ProfileService.getAProfile(this.$route.params.username).then((response) => {
      if (response.status == 200) {
        let profile = response.data;
        this.message.messageReceiver = profile.username;
      }
    }),
    CollectionService.getUserCollections(this.$route.params.username).then((response) => {
      if (response.status == 200) {
        this.receiverCollectionList = response.data;
      }
    });
    CollectionService.getMyCollections().then((response) => {
        this.senderCollections = response.data;
      });
    
  },

//   computed: {
//       checkForReceiverCollection() {
//           if(this.receiverCollectionId != "") {
              
//           }
//       },
//       checkForSenderCollection(){
//           if(this.senderCollectionId !=)
//       }
//   }
};
</script>

<style>
</style>