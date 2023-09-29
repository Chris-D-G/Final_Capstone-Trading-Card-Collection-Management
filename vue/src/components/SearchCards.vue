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
            <input
              type="text"
              id="cardTitleFilter"
              v-model="search.cardTitle"
            />
          </td>
          <td>
            <select id="statusFilter" v-model="search.gameType">
              <option value="">Magic: The Gathering</option>
              <option value="">Coming Soon!</option>
            </select>
          </td>
          <td>
              <select id="colorFilter" v-model="search.colors">
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
        <cardComponent v-for="card in cards" v-bind:key="card.id" v-bind:card="card"/>
    </div>
  </div>
</template>

<script>
import service from '../services/CardService.js';
import cardComponent from '../components/Card.vue';


export default {
  name: "card-list",
  components: { cardComponent },

  data() {
    return {
      cards: [],
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
    filteredCards() {
      let filteredCards = this.cards;
      if (this.search.cardTitle != "") {
        filteredCards = filteredCards.filter((card) =>
          card.cardTitle
            .toLowerCase()
            .includes(this.search.cardTitle.toLowerCase())
        );
      }
      if (this.search.gameType != "") {
        filteredCards = filteredCards.filter((card) =>
          card.gameType
            .toLowerCase()
            .includes(this.search.gameType.toLowerCase())
        );
      }
      if (this.search.gameType != "") {
        filteredCards = filteredCards.filter((card) =>
          card.gameType
            .toLowerCase()
            .includes(this.search.gameType.toLowerCase())
        );
      }
      return filteredCards;
    },
  },
  method: {
    getCardImgUrl(id) {
      this.cards.forEach((card) => {
        if (card.id == id) {
          return card.smallImgUrl;
        }
      });
    },
    }
}
</script>

<style>
</style>