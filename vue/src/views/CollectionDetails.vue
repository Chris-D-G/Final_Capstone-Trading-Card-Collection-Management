<template>
  <div class="ms-5 me-5">
    <h1 class="text-dark fs-1 text-center fw-bold title mt-4 text center p-3 w-25 mx-auto bg-primary  rounded-5 border border-2 border-danger-subtle" style="--bs-bg-opacity: .25;">
      {{ this.collection.name }}
    </h1>
    <button
      class="btn btn-outline-dark btn-secondary mb-3 mt-2"
      v-on:click="deleteCollection(collection.name)"
      v-if="isLoggedIn"
    >
      Delete Collection
    </button>

    <div class="d-flex flex-wrap m-4 bg-primary rounded-5 border border-2 border-danger-subtle" style="--bs-bg-opacity: .25;">
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="alphaDeck()"
      >
        Alphabetical
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="colorDeck()"
      >
        Color
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="identityDeck()"
      >
        Color Identity
      </button>
      <button class="btn btn-dark m-4 flex-fill" v-on:click.prevent="setDeck()">
        set
      </button>
      <button class="btn btn-dark m-4 flex-fill" v-on:click.prevent="CMCDeck()">
        CMC
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="EDHRECDeck()"
      >
        EDHREC Rank
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="resetDeck()"
      >
        Reset
      </button>
    </div>

    <div
      class="d-flex flex-wrap me-2 justify-content-evenly"
      v-if="
        !this.alpha &&
        !this.color &&
        !this.colorIden &&
        !this.set &&
        !this.EDHREC &&
        !this.CMC
      "
    >
      <card v-for="card in cards" v-bind:key="card.id" v-bind:card="card" />
    </div>
    <div class="d-flex flex-wrap me-2 justify-content-center" v-if="this.alpha">
      <card v-for="card in alphcards" v-bind:key="card.id" v-bind:card="card" />
    </div>

    <div class="d-flex flex-wrap me-2 justify-content-evenly" v-if="this.color">
      <card
        v-for="card in colorcards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>

    <div
      class="d-flex flex-wrap me-2 justify-content-evenly"
      v-if="this.colorIden"
    >
      <card
        v-for="card in colorIdencards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>

    <div class="d-flex flex-wrap me-2 justify-content-evenly" v-if="this.set">
      <card v-for="card in setcards" v-bind:key="card.id" v-bind:card="card" />
    </div>

    <div class="d-flex flex-wrap me-2 justify-content-evenly" v-if="this.CMC">
      <card v-for="card in CMCcards" v-bind:key="card.id" v-bind:card="card" />
    </div>

    <div
      class="d-flex flex-wrap me-2 justify-content-evenly"
      v-if="this.EDHREC"
    >
      <card
        v-for="card in EDHRECcards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import card from "../components/Card.vue";
import CardSort from "../services/cardSort.js";

export default {
  name: "collection-details",
  components: { card },
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
    };
  },

  methods: {
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
  },
};
</script>

<style>
.title {
  z-index: 1;
  font-family: 'Forzan', sans-serif;
}

</style>