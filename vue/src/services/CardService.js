import axios from 'axios';

export default {
    getAllCards() {
        return axios.get("/search-cards");
   },

}