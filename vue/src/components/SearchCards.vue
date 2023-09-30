<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>Card Name</th>
          <th>Game Type</th>
          <th>Color</th>
          <th>Color Identity</th>
          <th>Set Code</th>
          <th>Collector Number</th>
          <th>legalities</th>
          <th>CMC</th>
          <th>EDH Rank</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="text" id="cardTitleFilter" v-model="search.cardTitle" @change="currentPage = 1"/>
          </td>
          <td>
            <select id="statusFilter" v-model="search.gameType">
              <option selected disabled hidden value="">Choose TCG</option>
              <option value="1">Magic: The Gathering</option>
              <option value="">Coming Soon!</option>
            </select>
          </td>
          <td>
              <select id="colorFilter" v-model="search.colors">
              <option selected disabled hidden value="">Choose Color</option>
              <option value="">Red</option>
              <option value="">White</option>
              <option value="">Green</option>
              <option value="">Blue</option>
              <option value="">Black</option>
            </select>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="d-flex flex-wrap me-2 justify-content-evenly">
          <card v-for="(card, index) in filteredCards.slice(findStartIndex, findEndIndex)" v-bind:key="index" v-bind:card="card"/>
    </div>
    <div>
      <button @click="currentPage--" :disabled="currentPage === 1">Previous</button>
      <span>{{currentPage}}</span>
      <button @click="currentPage++" :disabled="findEndIndex >= filteredCards.length">Next Page</button>
    </div>
  </div>
</template>

<script>
import service from '../services/CardService.js';
import card from '../components/Card.vue';


export default {
  name: "card-list",
  components: { card },

  data() {
    return {
      cards: [],
      currentPage: 1,
      pageCards: [],
      cardsPerPage: 94,
      exactMatch: false,
      search: { cardTitle: "", gameType: "", colors: "", colorIdentity: "", setCode: "", 
      collectorNumber: "", legalities: "", cmc: "", edhRank: ""},
    };
  },

  created() {
    service.getAllCards().then(
      (response) => {
        this.cards = response.data;
      }
    );
  },

  computed: {
    filteredCards: function() {
      let filteredCards = this.cards;
      if (this.search.cardTitle != "") {
        filteredCards = filteredCards.filter((card) =>
          card.name
            .toLowerCase()
            .includes(this.search.cardTitle.toLowerCase())
            );
      }
      return filteredCards;
    },
    findStartIndex() {
      return (this.currentPage - 1) * this.cardsPerPage;
    },
    findEndIndex() {
      return (this.currentPage * this.cardsPerPage) - 1;
    }
  },
    
  method: {
    getCardImgUrl(id) {
      this.cards.forEach((card) => {
        if (card.id == id) {
          return card.smallImgUrl;
        }
      });
    }
    }
}
</script>


<style>
</style>