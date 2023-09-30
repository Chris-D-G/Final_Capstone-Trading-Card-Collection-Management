import axios from 'axios';

export default {
    getAllCards() {
        return axios.get("/search-cards");
   },
   getMultipleCardsByTitle(search, exactMatch) {
       return axios.get("/cards/search/title", search, exactMatch);
   }

}