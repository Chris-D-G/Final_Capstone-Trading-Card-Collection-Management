import axios from 'axios';

export default {
    getMyCollections() {
        return axios.get('/myCollections');
    },
    getAllCollections() {
        return axios.get("/allCollections");
    },
    addCollection(collection) {
        return axios.post("myCollections/add", collection);
    },
    deleteCollection(collectionId){
        return axios.delete("/myCollections",collectionId);
    },
    getAllCardsByCollection(collectionId){
        return axios.get(`/collections/${collectionId}/cards`);
    },
    getCollectionById(collectionId){
        return axios.get(`collections/${collectionId}`)
    },
    getAllCardsByCollectionAlph(collectionId){
        return axios.get(`/collections/${collectionId}/cards/a`);
    },

    getUserByCollectionId(collectionId){
        return axios.get(`/collections/${collectionId}/user`);
    }
}