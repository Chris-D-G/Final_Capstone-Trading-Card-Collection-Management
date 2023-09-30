<template>
  <div>
    <div>
    <table class="search-table">
      <thead class="search-head">
        <tr>
          <th class="first-header">Card Name</th>
          <th>Game Type</th>
          <th>Color</th>
          <th>Color Identity</th>
          <th>Set Code</th>
          <th>Set Name</th>
          <th>Collector Number</th>
          <th>legalities</th>
          <th>CMC</th>
          <th class="last-header">EDH Rank</th>
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
              <option value="R">Red</option>
              <option value="W">White</option>
              <option value="G">Green</option>
              <option value="U">Blue</option>
              <option value="B">Black</option>
            </select>
          </td>
          <td>
              <select id="colorFilter" v-model="search.colorIdentity">
              <option selected disabled hidden value="">Choose Color Identity</option>
              <option value="R">Red</option>
              <option value="W">White</option>
              <option value="G">Green</option>
              <option value="U">Blue</option>
              <option value="B">Black</option>
            </select>
          </td>
          <td>
            <input type="text" id="setCodeFilter" v-model="search.setCode"/>
          </td>
          <td>
            <input type="text" id="setNameFilter" v-model="search.setName"/>
          </td>
          <td>
            <input type="number" id="collectorNumFilter" v-model="search.collectorNumber"/>
          </td>
          <td>
              <select id="legalitiesFilter" v-model="search.legalities">
              <option selected disabled hidden value="">Legalities</option>
              <option value="R">Red</option>
              <option value="W">White</option>
              <option value="G">Green</option>
              <option value="U">Blue</option>
              <option value="B">Black</option>
            </select>
          </td>
          <td>
            <input type="number" id="cmcFilter" v-model="search.cmc"/>
          </td>
          <td>
            <input type="number" id="edhRankFilter" v-model="search.edhRank"/>
          </td>
        </tr>
      </tbody>
    </table>
    </div>
    <div class="d-flex flex-wrap me-2 justify-content-evenly">
          <card v-for="(card, index) in filteredCards.slice(findStartIndex, findEndIndex)" v-bind:key="index" v-bind:card="card"/>
    </div>
    <div>
      <button class="pagination-button" @click="currentPage--" :disabled="currentPage === 1">Previous</button>
      <span class="pagination-page">{{currentPage}}</span>
      <button class="pagination-button" @click="currentPage++" :disabled="findEndIndex >= filteredCards.length">Next Page</button>
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
      cardsPerPage: 94,
      exactMatch: false,
      search: { cardTitle: "", gameType: "", colors: "", colorIdentity: "", setCode: "", setName: "",
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
 .search-table {
    border-collapse: collapse;
    margin: 25px; 
    font-size: 0.9em;
    font-family: 'Forzan', sans-serif;
    min-width: 400px;
    box-shadow: 5px 5px 30px rgba(0, 0, 0, 0.4); 
    background-color: #4c2c2e;
    border-radius: 10px;
} 
 .search-head {
    background-color: #a888a0;
    color: white;
    text-align: center;
} 
  .first-header {
    border-top-left-radius: 10px;
  }
  .last-header {
    border-top-right-radius: 10px;
  }

.search-table td {
  padding: 12px 15px;
}

 */
.pagination-button {
  border-radius: 5px;
}
.pagination-page {
  border-radius: 5px;
  background-color: #4c2c2e;
  padding-left: 10px;
  padding-right: 10px;
  color: white;
  margin: 5px;
}
</style>