<template>
  <div class="d-flex justify-content-center">
        <div class="d-flex flex-column border border-black rounded-3 m-2 w-75">        
        

        <form method="post" action="">

            <div class="d-flex border-bottom border-black p-2">
                <label for="receiver" class="ms-2 my-1">TO:</label>
                <input name="receiver" id="receiver" type="text" required="required" placeholder="Type receipient name here" class="ms-2 my-1 w-50 form-control"/>
            </div>

            <div class="d-flex flex-column p-2">
                <div class="d-flex">
                  <label for="message" class="ms-2 my-1 form-label">MESSAGE:</label>
                  <textarea name="message" id="message" required="required" placeholder="Type message here" class="ms-2 my-1 form-control " ></textarea>
                </div>
                <input type="submit" class="my-1 btn btn-primary align-self-center"/> 
            </div>

            

         </form>
        
    </div> 


  </div>
</template>

<script>
export default {

  name:"new-message-form"

}
</script>

<style>

</style>