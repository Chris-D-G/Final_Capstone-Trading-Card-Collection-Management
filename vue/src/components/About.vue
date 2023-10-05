<template>
   <div class="d-lg-flex border border-1 border-info m-2 bg-white bg-opacity-75 rounded-4 align-items-lg-center">
            <div id="pic-name-container" class="d-flex flex-column align-items-center px-3">
                <img v-bind:src="imgSrc" alt="Profile Picture" class="rounded-circle border border-1 border-dark m-2" width="auto" height="250px">
                <h2>{{name}}</h2>
            </div>
            <div id="self-description" class="">
                <p class="border border-warning border-1 px-3 pt-2 mt-lg-4 me-lg-4 text-break">
                    {{aboutMe}}
                </p>
                <div id="profileLinks" class="d-flex flex-row px-3 justify-content-center justify-content-lg-start">
                    <a href="#" class="me-3 fs-5 text-break"><i class="bi bi-linkedin fs-1 me-2"></i><span class="d-none d-lg-inline">{{linkedin}}/</span></a>
                    <a href="#" class="me-3 fs-5 text-break" ><i class="bi bi-github fs-1 me-2"></i><span class="d-none d-lg-inline">{{github}}</span> </a>
                </div>                
            </div>                
        </div>
</template>

<script>
export default {
  name :"about",
  props:['name','aboutMe', 'linkedin','github', 'imgSrc' ]

}
</script>

<style>

</style>