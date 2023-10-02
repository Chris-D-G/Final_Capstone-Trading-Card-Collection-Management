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
        return axios.delete(`/myCollections/${collectionId}`);
    },
    getAllCardsByCollection(collectionId){
        return axios.get(`/collections/${collectionId}/cards`);
    },
    getCollectionById(collectionId){
        return axios.get(`collections/${collectionId}`)
    },
    getUserByCollectionId(collectionId){
        return axios.get(`/collections/${collectionId}/user`);
    },
    addCardToCollection(collectionId, card){
        return axios.get(`/collections/${collectionId}/add`, card);
    },
    deleteCardFromCollection(card, collectionId){
        return axios.delete(`/collections/${collectionId}/delete`,card);
    },

// sorted lists for collections display

    getAllCardsByCollectionAlph(collectionId){
        return axios.get(`/collections/${collectionId}/cards/a`);
    },
    getAllCardsByCollectionColor(collectionId){
        return axios.get(`/collections/${collectionId}/cards/c`);
    },
    getAllCardsByCollectionColorIdentity(collectionId){
        return axios.get(`/collections/${collectionId}/cards/ci`);
    },
    getAllCardsByCollectionSet(collectionId){
        return axios.get(`/collections/${collectionId}/cards/s`);
    },
    getAllCardsByCollectionLegality(collectionId){
        return axios.get(`/collections/${collectionId}/cards/l`);
    },
    getAllCardsByCollectionCMC(collectionId){
        return axios.get(`/collections/${collectionId}/cards/cmc`);
    },
    getAllCardsByCollectionEDHREC(collectionId){
        return axios.get(`/collections/${collectionId}/cards/ed`);
    }
}