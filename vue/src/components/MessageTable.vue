<template>
          <div>
            <div class="d-flex" id="messageTable">
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Message ID </p>
                    <p
                    v-for="(message,index) in messageList"
                    v-bind:key="index"
                    >{{message.messageID}}</p>
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Sender Name</p>
                    <p
                    v-for="(message,index) in messageList"
                    v-bind:key="index"
                    >{{message.messageSender}}</p>
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Receiver Name</p>
                    <p
                    v-for="(message,index) in messageList"
                    v-bind:key="index"
                    >{{message.messageReceiver}}</p> 
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">TimeStamp</p>
                    <p
                    v-for="(message,index) in messageList"
                    v-bind:key="index"
                    >{{message.messageTimestamp}}</p>
                </div>
                
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Read Status</p>
                    <p
                    v-for="(message,index) in messageList"
                    v-bind:key="index"
                    >{{message.read}}</p>
                </div>  
            </div>
        </div>
</template>

<script>
import MessageService from "../services/MessageService.js"

export default {
    name: "message-table",
    data(){
        return{
            messageList: []
        }
    },
    created(){
        MessageService.getMessagesForUser()
        .then((response)=>{
            this.messageList = response.data;

        })
    }



}
</script>

<style scoped>
#messageTable{
    background-color: #ffffffcc;
}

</style>