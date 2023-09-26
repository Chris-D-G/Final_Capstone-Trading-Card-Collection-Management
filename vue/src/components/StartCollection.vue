<template>
  <form>
    <div class="field">
      <label for="collectionName">Collection name:</label>
      <input type="text" name="collectionName" v-model="collection.name" />
    </div>
    <div class="field">
      <label for="tcg">Game: </label>
      <select name="tcg" v-model="collection.tcgId">
        <option value="0">Choose a game!</option>
        <option value="1">Magic The Gathering</option>
      </select>
    </div>
    <button type="submit" @click.prevent="saveCollection">Create</button>
  </form>
</template>

<script>
import collectionService from "../services/CollectionService";

export default {
    name: "start-collection",
    data() {
        return {
            collection: {
                name: "",
                tcgId: ""
            }
        }
    },
    methods: {
        saveCollection() {
            if(this.collection.tcgId === 0){
                return;
            }
            collectionService.add(this.collection).then(response => { // fake method!!
                if (response.status === 201) {
                    // TODO: route user to their new collection
                }
            })
        }
    }
}
</script>

<style>
.field {
    display: flex;
    flex-direction: column;
}
.field label {
    font-weight: bold;
}
</style>