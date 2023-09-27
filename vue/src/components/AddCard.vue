<template>
  <div>
      <table>
        <thead>
          <tr>
            <th>Card Name</th>
            <th>Game Type</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><input type="text" id="cardTitleFilter" v-model="search.cardTitle"/></td>
            <td>
              <select id="statusFilter" v-model="search.gameType">
              <option value="">Magic: The Gathering</option>
            </select>
            </td>
          <tr v-for="card in filteredCards" v-bind:key="card.id" >
            <td>{{card.cardTitle}}</td>
            <td><img src="{{getCardImgUrl(this.card.id)}}" alt="card-art"></td>
            <td>{{user.username}}</td>
            <td>{{user.emailAddress}}</td>
            <td>{{user.status}}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
export default {
    name: 'card-list',
    data() {
        return {
        cards: [],
        search:
        {cardTitle: "", gameType: ""}
        }
    },


    computed: {
    filteredCards() {
      let filteredCards = this.cards;
      if(this.search.cardTitle != "") {
        filteredCards = filteredCards.filter((card) => 
        card.cardTitle
        .toLowerCase()
        .includes(this.search.cardTitle.toLowerCase()));
      }
      if(this.search.gameType != "") {
        filteredCards = filteredCards.filter((card) => 
        card.gameType
        .toLowerCase()
        .includes(this.search.gameType.toLowerCase()));
      }
      return filteredCards;
    }
  },
  method: {
      getCardImgUrl(id) {
          this.cards.forEach(card => {
              if(card.id == id) {
                  return card.smallImgUrl;
              }
          });
      }
  }
}
</script>

<style>

</style>