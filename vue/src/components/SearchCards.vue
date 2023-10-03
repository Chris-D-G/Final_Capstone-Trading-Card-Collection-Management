<template>
  <div>   
    <!-- start alternative -->
    <div
      id="alternative-search-bar"
      class="d-flex flex-column flex-lg-row flex-lg-wrap justify-content-evenly text-white mx-5 my-2 p-2 rounded-2 shadow"
    >
      <div class="d-flex flex-column">
        Card Name
        <input type="text" id="cardTitleFilter" v-model="search.cardTitle" />
      </div>
      <div class="d-flex flex-column">
        Game Type
        <select id="statusFilter" v-model="search.gameType">
          <option selected disabled hidden value="">Choose TCG</option>
          <option value="1">Magic: The Gathering</option>
          <option value="">Coming Soon!</option>
        </select>
      </div>
      <div class="d-flex flex-column">
        Color
        <select id="colorFilter" v-model="search.colors">
          <option selected disabled hidden value="">Choose Color</option>
          <option value="R">Red</option>
          <option value="W">White</option>
          <option value="G">Green</option>
          <option value="U">Blue</option>
          <option value="B">Black</option>
          <option value="">Reset</option>
        </select>
      </div>
      <div class="d-flex flex-column">
        Color Identity
        <select id="colorIdenFilter" v-model="search.colorIdentity">
          <option selected disabled hidden value="">
            Choose Color Identity
          </option>
          <option value="R">Red</option>
          <option value="W">White</option>
          <option value="G">Green</option>
          <option value="U">Blue</option>
          <option value="B">Black</option>
          <option value="">Reset</option>
        </select>
      </div>
      <div class="d-flex flex-column">
        Set Code
        <input type="text" id="setCodeFilter" v-model="search.setCode" />
      </div>
      <div class="d-flex flex-column">
        Set Name
        <input type="text" id="setNameFilter" v-model="search.setName" />
      </div>
      <div class="d-flex flex-column">
        Collector Number
        <input
          type="number"
          id="collectorNumFilter"
          v-model="search.collectorNumber"
        />
      </div>
      <div class="d-flex flex-column">
        Legalities
        <select id="legalitiesFilter" v-model="search.legalities">
          <option selected disabled hidden value="">Legalities</option>
          <option value="R">Red</option>
          <option value="W">White</option>
          <option value="G">Green</option>
          <option value="U">Blue</option>
          <option value="B">Black</option>
        </select>
      </div>
      <div class="d-flex flex-column">
        CMC
        <input type="number" id="cmcFilter" v-model="search.cmc" />
      </div>
      <div class="d-flex flex-column">
        EDH Rank
        <input type="number" id="edhRankFilter" v-model="search.edhRank" />
      </div>
    </div>
    <!-- end alternative -->
    <div
      class="d-flex flex-wrap me-2 justify-content-between"
      v-if="isLoggedIn"
    >
      <addCard
        v-for="(addCard, index) in filteredCards.slice(
          findStartIndex,
          findEndIndex
        )"
        v-bind:key="index"
        v-bind:addCard="addCard"
        :isChecked="checkboxStates[index]"
        @update:checked="updateCheckboxState(index, $event)"
      />
    </div>

    <div class="d-flex flex-wrap me-2 justify-content-between" v-else>
      <card
        v-for="(card, index) in filteredCards.slice(
          findStartIndex,
          findEndIndex
        )"
        v-bind:key="index"
        v-bind:card="card"
      />
    </div>

    <div class="d-flex flex-row justify-content-center align-items-center">
      <button        
        class=" rounded-2"
        @click="currentPage--"
        :disabled="currentPage === 1"
      >
        Previous
      </button>
      
      <span id="pagination-page" class="text-white px-2 m-1 rounded-2">{{ currentPage }}</span>
      <button
        class="rounded-2"
        @click="currentPage++"
        :disabled="findEndIndex >= filteredCards.length"
      >
        Next Page
      </button>
    </div>
    <div v-for="card in this.checkedCards" v-bind:key="card.id" class="d-flex flex-column justify-content-start">
      <ul>
        <li>{{card.name}}</li>
        <li>{{card.collectorNumber}}</li>
      </ul>
    </div>
    <div v-if="isLoggedIn">
      <div class="m-1">
        <label class="me-1" for="choose-collection"
          >Enter Name of Collection</label
        >
        <input
          @change="setCollectionId()"
          name="choose-collection"
          v-model="collectionName"
          type="text"
          @change.prevent="setCollectionId()"
        />
      </div>
      <button class="btn btn-dark m-2" @click="addCheckedCards()">
        Add Checked Cards to Queue
      </button>
      <button class="btn btn-dark" @click="addCard()">
        Add Queued Cards To Collection
      </button>
    </div>
  </div>
</template>


<script>
import service from "../services/CardService.js";
import card from "../components/Card.vue";
import addCard from "../components/addCardComponent.vue";
import CollectionService from "../services/CollectionService.js";

export default {
  name: "card-list",
  components: { addCard, card },

  data() {
    return {
      cards: [],
      isLoggedIn: false,
      notLoggedIn: true,
      collectionId: 0,
      collectionName: "",
      availableCollections: [],
      checkboxStates: [], // Array to store checkbox states
      checkedCards: [], // Array to store checked cards
      currentPage: 1,
      cardsPerPage: 94,
      exactMatch: false,
      search: {
        cardTitle: "",
        gameType: "",
        colors: "",
        colorIdentity: "",
        setCode: "",
        setName: "",
        collectorNumber: "",
        legalities: "",
        cmc: "",
        edhRank: "",
      },
    };
  },

  created() {
    if (this.isLoggedIn) {
      service.getAllCards().then((response) => {
        this.cards = response.data;
      });
    } else {
      service.getAllCardsNotLoggedIn().then((response) => {
        this.cards = response.data;
      });
    }

    this.checkLoginStatus();

    CollectionService.getMyCollections().then((response) => {
      this.availableCollections = response.data;
      this.isLoading = false;
    });
  },

  methods: {
    checkLoginStatus() {
      let token = this.$store.state.token;

      if (token != "") {
        this.isLoggedIn = true;
        this.notLoggedIn = false;
      }
    },

    updateCheckboxState(index, value) {
      // Update the checkbox state in the array
      console.log(
        `Checkbox state updated for card at index ${index}: ${value}`
      );
      this.checkboxStates[index] = value;
    },
    addCheckedCards() {
      // Add checked cards to the checkedCards array
      console.log("addCheckedCards method called");
      console.log("Checkbox states:", this.checkboxStates);
      this.checkedCards = this.filteredCards.filter(
        (_, index) => this.checkboxStates[index]
      );
      console.log("Filtered checkedCards:", this.checkedCards);
    },

    addCard() {
      this.checkedCards.forEach((card) => {
        CollectionService.addCardToCollection(this.collectionId, card);
      });
      this.$router.push(`/myCollections`);
    },

    setCollectionId() {
      console.log("setCollectionId method called");
      console.log("Current Collection Id:", this.collectionId);
      if (this.collectionName != "") {
        this.availableCollections.forEach((collection) => {
          if (
            collection.name
              .toLowerCase()
              .includes(this.collectionName.toLowerCase())
          ) {
            this.collectionId = collection.id;
            this.collectionName = collection.name;
          }
        });
      }
    },
  },

  computed: {
    filteredCards: function () {
      let filteredCards = this.cards;
      if (this.search.cardTitle != "") {
        filteredCards = filteredCards.filter((card) =>
          card.name.toLowerCase().includes(this.search.cardTitle.toLowerCase())
        );
      }

      if (this.search.colors != "") {
        filteredCards = filteredCards.filter((card) =>
          card.colors.includes(this.search.colors)
        );
      }

      if (this.search.colorIdentity != "") {
        filteredCards = filteredCards.filter((card) =>
          card.colorIdentity.includes(this.search.colorIdentity)
        );
      }

      if (this.search.setCode != "") {
        filteredCards = filteredCards.filter((card) =>
          card.setCode.toLowerCase().includes(this.search.setCode.toLowerCase())
        );
      }
      if (this.search.setName != "") {
        filteredCards = filteredCards.filter((card) =>
          card.setName.toLowerCase().includes(this.search.setName.toLowerCase())
        );
      }

      if (this.search.cmc != "") {
        filteredCards = filteredCards.filter(
          (card) => card.cmc == this.search.cmc
        );
      }

      if (this.search.collectorNumber != "") {
        filteredCards = filteredCards.filter(
          (card) => card.collectorNumber == this.search.collectorNumber
        );
      }

      if (this.search.edhRank != "") {
        filteredCards = filteredCards.filter(
          (card) => card.edhrecRank == this.search.edhRank
        );
      }

      return filteredCards;
    },

    findStartIndex() {
      return (this.currentPage - 1) * this.cardsPerPage;
    },
    findEndIndex() {
      return this.currentPage * this.cardsPerPage - 1;
    },
  },
};
</script>


<style scoped>
#alternative-search-bar, #pagination-page {
  background-color: #4c2c2e;
}
</style>