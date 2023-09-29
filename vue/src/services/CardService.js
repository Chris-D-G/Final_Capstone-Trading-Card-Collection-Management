import axios from 'axios';

export default {
    getAllCards() {
        return axios.get("/allCards");
   },
   addCard(id) {
       return axios.get("/collections/:id/add", id);
   }
}