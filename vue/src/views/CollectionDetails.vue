<template>
  <div class="ms-5 me-5">
    <h1
      class="text-light fs-1 text-center fw-bold title mt-4 p-3 w-25 mx-auto bg-white rounded-5 border border-1 border-white shadow"
      style="--bs-bg-opacity: 0.15"
    >
      {{ this.collection.name }}
    </h1>
    <button
      class="btn btn-outline-dark btn-secondary mb-3 mt-2"
      v-on:click="deleteCollection(collection.name)"
      v-if="isLoggedIn"
    >
      Delete Collection
    </button>

    <div
      class="d-flex flex-wrap m-4 bg-white rounded-5 border border-1 border-white shadow"
      style="--bs-bg-opacity: 0.15"
    >
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="alphaDeck()"
      >
        {{ alpha ? "Reset" : "Alphabetical" }}
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="colorDeck()"
      >
        {{ color ? "Reset" : "Color" }}
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="identityDeck()"
      >
        {{ colorIden ? "Reset" : "Color Identity" }}
      </button>
      <button class="btn btn-dark m-4 flex-fill" v-on:click.prevent="setDeck()">
        {{ set ? "Reset" : "Sets" }}
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="lagalityDeck()"
      >
        {{ legality ? "Reset" : "Legality" }}
      </button>
      <button class="btn btn-dark m-4 flex-fill" v-on:click.prevent="CMCDeck()">
        {{ CMC ? "Reset" : "CMC" }}
      </button>
      <button
        class="btn btn-dark m-4 flex-fill"
        v-on:click.prevent="EDHRECDeck()"
      >
        {{ EDHREC ? "Reset" : "EDHREC Rank" }}
      </button>
    </div>

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="
        !this.alpha &&
        !this.color &&
        !this.colorIden &&
        !this.set &&
        !this.legality &&
        !this.EDHREC &&
        !this.CMC
      "
    >
      <card
        v-for="card in cards"
        v-bind:key="card.id"
        v-bind:card="card"
        class="d-flex flex-wrap gap-2 justify-content-evenly"
      />
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

    <div
      class="d-flex flex-wrap gap-2 justify-content-evenly"
      v-if="this.legality"
    >
      <card
        v-for="card in legalitycards"
        v-bind:key="card.id"
        v-bind:card="card"
      />
    </div>

    <div class="d-flex flex-wrap gap-2 justify-content-evenly" v-if="this.CMC">
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
  </div>
</template>

<script>
import CollectionService from "../services/CollectionService.js";
import card from "../components/Card.vue";
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
      legalitycards: [],
      CMCcards: [],
      EDHRECcards: [],
      collection: [],
      alpha: false,
      color: false,
      colorIden: false,
      set: false,
      legality: false,
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
      this.alpha = !this.alpha;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.legality = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    colorDeck() {
      this.color = !this.color;
      this.alpha = false;
      this.colorIden = false;
      this.set = false;
      this.legality = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    identityDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = !this.colorIden;
      this.set = false;
      this.legality = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    setDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = !this.set;
      this.legality = false;
      this.CMC = false;
      this.EDHREC = false;
    },
    lagalityDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.legality = !this.legality;
      this.CMC = false;
      this.EDHREC = false;
    },
    CMCDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.legality = false;
      this.CMC = !this.CMC;
      this.EDHREC = false;
    },
    EDHRECDeck() {
      this.alpha = false;
      this.color = false;
      this.colorIden = false;
      this.set = false;
      this.legality = false;
      this.CMC = false;
      this.EDHREC = !this.EDHREC;
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
      }
    );

    CollectionService.getCollectionById(this.$route.params.id).then(
      (response) => {
        this.collection = response.data;
      }
    );
    this.checkLoginStatus();

    CollectionService.getAllCardsByCollectionAlph(this.$route.params.id).then(
      (response) => {
        this.alphcards = response.data;
      }
    );

    CollectionService.getAllCardsByCollectionColor(this.$route.params.id).then(
      (response) => {
        this.colorcards = response.data;
      }
    );
    CollectionService.getAllCardsByCollectionColorIdentity(
      this.$route.params.id
    ).then((response) => {
      this.colorIdencards = response.data;
    });
    CollectionService.getAllCardsByCollectionSet(this.$route.params.id).then(
      (response) => {
        this.setcards = response.data;
      }
    );
    CollectionService.getAllCardsByCollectionLegality(
      this.$route.params.id
    ).then((response) => {
      this.legalitycards = response.data;
    });
    CollectionService.getAllCardsByCollectionCMC(this.$route.params.id).then(
      (response) => {
        this.CMCcards = response.data;
      }
    );
    CollectionService.getAllCardsByCollectionEDHREC(this.$route.params.id).then(
      (response) => {
        this.EDHRECcards = response.data;
      }
    );
  },
};
</script>

<style>
.title {
  z-index: 1;
  font-family: "Forzan", sans-serif;
}
</style>