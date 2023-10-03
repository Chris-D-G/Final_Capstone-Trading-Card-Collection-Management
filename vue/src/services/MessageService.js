import axios from "axios";

export default{
    getMessagesForUser (){
        return axios.get('/messages/myMessages');
    },

    sendNewMessage(){
        return axios.post('/messages/new-message');
    },

    deleteMessage(messageID){
        return axios.delete(`/messages/${messageID}`);
    },

    updateReadStatus(messageID){
        return axios.put(`/messages/${messageID}`)
    }
    
}