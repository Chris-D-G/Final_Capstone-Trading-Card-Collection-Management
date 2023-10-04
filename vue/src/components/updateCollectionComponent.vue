<template>
  <div>
      <form class="pt-5">
    <div class="form-input-group pb-3">
      <label class="form-label pb-1" for="CN">Declare your new Collection below</label>
      <input class="form-control w-25 p-2 mx-auto text-center" type="text" name="collectionName" id="CN" placeholder="~my new favorite collection~" v-model="collection.name" required autofocus/>
    </div>
    <div class="form-input-group pt-3 pb-3">
      <label class="form-label pb-1"  for="tcg">Choose your TCG</label>
      <select name="tcg" v-model="collection.tcgId" class="form-control w-25 p-2 mx-auto text-center" required >
        <option value="0">Choose a game!</option>
        <option value="1" class="">Magic The Gathering</option>
      </select>
    </div>
    <button class="btn btn-dark" type="submit" @click.prevent="saveCollection">Create</button>
  </form>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>