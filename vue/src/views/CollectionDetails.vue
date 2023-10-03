<template>
  <div class="ms-5 me-5">
    <h1
      class="text-light fs-1 text-center fw-bold title mt-4 p-3 w-25 mx-auto bg-white rounded-5 border border-1 border-white shadow"
      style="--bs-bg-opacity: 0.15"
    >
      {{ this.collection.name }}
    </h1>
    <button
      class="btn btn-outline-dark btn-danger text-light fw-bold mb-3 mt-2"
      v-on:click="deleteCollection(collection.name)"
      v-if="isLoggedIn">
      Delete Collection
    </button>

    <div
      class="d-flex flex-wrap m-4 bg-white rounded-5 border border-1 border-white shadow"
      style="--bs-bg-opacity: 0.15"
    >
      <button
        class="btn m-4 flex-fill"
        v-on:click.prevent="alphaDeck()"
        v-bind:class="buttonClass(alpha)">
        Alphabetical
      </button>
      <button
        class="btn m-4 flex-fill"
        v-on:click.prevent="colorDeck()"
        v-bind:class="buttonClass(color)">
        Color
      </button>
      <button
        class="btn m-4 flex-fill"
        v-on:click.prevent="identityDeck()"
        v-bind:class="buttonClass(colorIden)">
        Color Identity
      </button>
      <button 
        class="btn m-4 flex-fill" 
        v-on:click.prevent="setDeck()"
        v-bind:class="buttonClass(set)">
        set
      </button>
      <button class="btn m-4 flex-fill" 
        v-on:click.prevent="CMCDeck()"
        v-bind:class="buttonClass(CMC)">
        CMC
      </button>
      <button
        class="btn m-4 flex-fill"
        v-on:click.prevent="EDHRECDeck()"
        v-bind:class="buttonClass(EDHREC)">
        EDHREC Rank
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="resetDeck()">
        Reset
      </button>
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="
        !this.alpha &&
        !this.color &&
        !this.colorIden &&
        !this.set &&
        !this.EDHREC &&
        !this.CMC
      "
    >
    <div class="d-flex flex-wrap me-2 justify-content-between" v-if="isLoggedIn">
      <deleteCard v-for="(deleteCard, index) in cards" 
      v-bind:key="index" v-bind:deleteCard="deleteCard" :isChecked="checkboxStates[index]"
      @update:checked="updateCheckboxState(index, $event)"/>
      </div>
    <div class="d-flex flex-wrap gap-2 justify-content-evenly" v-else>
      <card
        v-for="card in cards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
      </div>
       
    
    </div>
    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.alpha"
    >
      <card v-for="card in alphcards" v-bind:key="card.id" v-bind:card="card" />
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.color"
    >
      <card
        v-for="card in colorcards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.colorIden"
    >
      <card
        v-for="card in colorIdencards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.set"
    >
      <card v-for="card in setcards" v-bind:key="card.id" v-bind:card="card" />
    </div>
    <div class="d-flex flex-wrap me-2 justify-content-evenly" v-if="this.CMC">
      <card v-for="card in CMCcards" v-bind:key="card.id" v-bind:card="card" />
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.EDHREC"
    >
      <card
        v-for="card in EDHRECcards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>
    <button class="btn btn-dark m-2"  @click="addCheckedCards()">Add Checked Cards to Queue</button>
      <button class="btn btn-dark" @click="deleteCard()">Delete Queued Cards From Collection</button>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import card from "../components/Card.vue";
import CardSort from "../services/cardSort.js";
import deleteCard from "../components/deleteCardComponent.vue";
// import profileService from "../services/ProfileService.js";

export default {
  name: "collection-details",
  components: { card, deleteCard },
  data() {
    return {
      cards: [],
      alphcards: [],
      colorcards: [],
      colorIdencards: [],
      setcards: [],
      CMCcards: [],
      EDHRECcards: [],
      collection: [],
      alpha: false,
      color: false,
      colorIden: false,
      set: false,
      CMC: false,
      EDHREC: false,
      isOwner: false,
      isLoggedIn: false,
      checkboxStates: [], // Array to store checkbox states
      checkedCards: [], // Array to store checked cards
      user: [],
      loggedInUser: []
    };
  },

  methods: {
    buttonClass(isActive){
      return isActive? 'btn-light': 'btn-dark';
    },

    checkLoginStatus() {
      let token = this.$store.state.token;

      if (token != "") {
        this.isLoggedIn = true;
      }
    },
    alphaDeck() {
      this.alpha = true;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    colorDeck() {
      this.color = true;
      this.alpha = false;
      this.colorIden = false;
      this.set = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    identityDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = true;
      this.set = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    setDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = true;
      this.CMC = false;
      this.EDHREC = false;
    },
    CMCDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.CMC = true;
      this.EDHREC = false;
    },
    EDHRECDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.CMC = false;
      this.EDHREC = true;
    },

    resetDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.CMC = false;
      this.EDHREC = false;
    },

    deleteCollection(name) {
      if (
        confirm(
          "*****PERMENANT***** \nComfirmation will delete the Collection named: \n\n~ " +
            name +
            " ~ \n\nAll Cards associated, and your access to the Collection, will be revoked.\nThere will be no reccords, and it cannot be reanimated.\n\n\nARE YOU 'DEAD' SERIOUS?"
        )
      ) {
        CollectionService.deleteCollection(this.$route.params.id).then(
          (response) => {
            if (response.status == 204) {
              this.$router.push(`/myCollections`);
            }
          }
        );
      }
    },

    updateCheckboxState(index, value) {
      // Update the checkbox state in the array
      console.log(`Checkbox state updated for card at index ${index}: ${value}`);
      this.checkboxStates[index] = value;
    },

    addCheckedCards() {
      // Add checked cards to the checkedCards array
      console.log("addCheckedCards method called");
      console.log("Checkbox states:", this.checkboxStates);
      this.checkedCards = this.cards.filter((_, index) => this.checkboxStates[index]);
      console.log("Filtered checkedCards:", this.checkedCards);
    },

    deleteCard() {
      this.checkedCards.forEach((card) => {
        CollectionService.deleteCardFromCollection(card.id, this.collection.id)
      });
      this.$router.push(`/myCollections`)
    },
  },

  created() {
    CollectionService.getAllCardsByCollection(this.$route.params.id).then(
      (response) => {
        this.cards = response.data;
        for (let i = 0; i < this.cards.length; i++) {
          const element = this.cards[i];
          this.alphcards.push(element);
          this.CMCcards.push(element);
          this.colorcards.push(element);
          this.colorIdencards.push(element);
          this.setcards.push(element);
          this.EDHRECcards.push(element);
        }
        this.alphcards = CardSort.sortByName(this.alphcards);
        this.CMCcards = CardSort.sortByCmc(this.CMCcards);
        this.colorcards = CardSort.sortByColor(this.colorcards);
        this.colorIdencards = CardSort.sortByColorId(this.colorIdencards);
        this.setcards = CardSort.sortBySetId(this.setcards);
        this.EDHRECcards = CardSort.sortByEdhrec(this.EDHRECcards);
      }
    );

    CollectionService.getCollectionById(this.$route.params.id).then(
      (response) => {
        this.collection = response.data;
      }
    );
    this.checkLoginStatus();

    CollectionService.getUserByCollectionId(this.$route.params.id).then(
      (response) => {
        this.user = response.data;
      }
    );

    // profileService.getMyProfile().then(
    //   (response) => {
    //    let profile = response.data;
    //   }

    // );
  }
};
</script>

<style>
.title {
  z-index: 1;
  font-family: "Forzan", sans-serif;
}
</style>