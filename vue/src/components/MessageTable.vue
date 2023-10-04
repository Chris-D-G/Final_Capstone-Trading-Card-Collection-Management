<template>
          <div>
            <div class="d-flex">
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Message ID </p>
                    <p>{{messageID}}</p>
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Sender Name</p>
                    <p>{{messageSender}}</p>
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Receiver Name</p>
                    <p>{{messageReceiver}}</p> 
                </div>
        
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">TimeStamp</p>
                    <p>{{messageTimestamp}}</p>
                </div>
                
                <div class="d-flex flex-column border border-primary">
                    <p class="border-bottom border-danger">Read Status</p>
                    <p>{{read}}</p>
                </div>  
            </div>
        </div>
</template>

<script>
export default {
    name: "message-table"

}
</script>

<style>

</style>