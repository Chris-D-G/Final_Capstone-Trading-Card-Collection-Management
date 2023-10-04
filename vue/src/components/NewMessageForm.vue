<template>
  <div>
        <div class="d-flex flex-column border border-black rounded-3 m-2 w-75">
        
        <div class="d-flex border-bottom border-black justify-content-center">
            <h4 class="ms-2 my-1">Send Message</h4>
        </div>

        <form method="post" action="">

            <div class="d-flex border-bottom border-black">
                <label for="receiver" class="ms-2 my-1">TO:</label>
                <input name="receiver" id="receiver" type="text" required="required" class="ms-2 my-1"/>
            </div>

            <div class="d-flex border-bottom border-black">
                <label for="message" class="ms-2 my-1 form-label">MESSAGE:</label>
                <textarea name="message" id="message" required="required" class="ms-2 my-1 form-control " ></textarea> 
            </div>

            <div class="d-flex justify-content-center">
                <input type="submit" class="my-1"/>
            </div>

         </form>
        
    </div> 


  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>