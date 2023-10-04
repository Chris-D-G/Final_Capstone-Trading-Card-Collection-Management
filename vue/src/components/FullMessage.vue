<template>
  <div class="d-flex justify-content-center">
        <div class="bg-secondary-subtle m-1 p-2 rounded-3 d-flex flex-column border border-dark w-75">
            <p class="ms-2 my-1 fs-4">From: {{oneMessage.messageSender}}</p>
            <hr>
            <p class="ms-2 my-1 fs-4">To: {{oneMessage.messageReceiver}}</p>
            <hr>
            <p class="ms-2 my-1 fs-4">Date: {{oneMessage.messageTimestamp}}</p>
            <hr>
            <p class="ms-2 my-1 fs-4">Message: {{oneMessage.messageText}}</p>
        </div>
    </div> 
</template>

<script>
import MessageService from '../services/MessageService'



export default {
    
    name: "message",
    data(){
        return{
            oneMessage:{}
        }
        
    },
    created(){
        MessageService.getSingleMessageByID(this.$route.params.id)
        .then((response)=>{
            this.oneMessage = response.data
        })
        .catch((error)=>{
            console.log(error)
        })
        
    }


}
</script>

<style>

</style>