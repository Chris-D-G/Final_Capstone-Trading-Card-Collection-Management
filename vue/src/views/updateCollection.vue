<template>
  <div>
      <div>
      <form class="pt-5">
    <div class="form-input-group pb-3">
      <label class="form-label pb-1" for="CN">Enter New Collection Name Below</label>
      <input class="form-control w-25 p-2 mx-auto text-center" type="text" name="collectionName" id="CN" placeholder="~the coolest name ever~" v-model="collection.name" required autofocus/>
    </div>
    
    <button class="btn btn-dark" type="submit" @click.prevent="updateCollection">Save Changes</button>
  </form>
  </div>
  </div>
</template>

<script>

import collectionService from "../services/CollectionService";


export default {
    name : "edit-collection",
  

  data() {
    return {
      collection: {
        name: "",
        tcgId: "",
      },
    };
  },

  methods: {
      updateCollection() {
          collectionService.updateCollection(this.collection.id, this.collection).then(
              (response) => {
                  if (response.status === 200) {
          this.$router.push('/MyCollections/')
        }
              }
          )
      }
  },

  created() {
      collectionService.getCollectionById(this.$route.params.id).then(
          (response) => {
              this.collection = response.data
          }
      );
  }

}
</script>

<style>

</style>