<template>
  <div class="d-flex flex-column w-75 mx-auto bg-white w-25 p-5 mx-auto rounded-5 border border-1 border-white shadow"
          style="--bs-bg-opacity: 0.4">
    <h1>Frequently Asked Questions</h1>

<h3>What is Cardomancy?</h3>
<ul class="d-flex text-start">
    <li>
        Cardomancy is a versitile virtual card manegement system. The intent is for users to have the at-hand
         ability to view, add to, and sort collections of popular TCGs modeling their own collection irl.
    </li>
</ul >
<h3>What is a TCG?</h3>
<ul class="d-flex text-start">
    <li>
        TCG is an acronym meaning "Trading Card Game"
    </li>
</ul>
<h3>How do I make a collection?</h3>
<ul class="d-flex text-start">
    <li>
        Registered users can create a collection by logging in, navigating to the 'Manage Collections' tab, and selecting 'Start New Collection'. Enter in a title, and choose a TCG. Then, submit! Cardomancy will redirect you to your new collection.
    </li>
</ul>
<h3>How do I add cards to my collection?</h3>
<ul class="d-flex text-start">
    <li>
        Registered users can create a collection, and add cards to that collection. Once logged in, 
        users can navigate to the 'Search' tab to view an exauhstiv list of your chosen TCG. 
        Select any number of cards from any page or view, and click the button entitled 'Add Checked Cards to Queue'.
        For your convinience, a list of chosen cards will be available for you. Un-checking any box will de-select the chosen cards.
        Finaly, choose which collection to add your cards to, and click 'Add Queued Cards to Collection'.
         Cardomancy will redirect you to your collection.
    </li>
</ul>
<h3>How do I delete cards from my collection?</h3>
<ul class="d-flex text-start">
    <li>
        From inside your collection, select any number of cards, and then select 'Add Checked Cards to Queue'.
         For your convinience, a list of chosen cards will be available for you. Selecting 'Delete Queued Cards from Collection'
         will permenantly remove selected cards.
    </li>
</ul>
<h3>How do I send messages?</h3>
<ul class="d-flex text-start ">
    <li>
        Once logged in users may select the 'My Messages' tab, and then select 'Create a new message' to bring up a message form.
        Enter in the username of the user you are sending to, and your message. Then, submit!
    </li>
</ul>
<h3 >How do I make trades?</h3>
<ul class="d-flex text-start" >
    <li>
        Users can view other users profile by selecting their username from a published collection.
    </li>
</ul>
<h3>How do I add friends/followers?</h3>
<ul class="d-flex text-start">
    <li>
        Users can view other users profile by selecting their username from a published collection. From there, they may toggle between
        'Add' or 'Delete' friends to suit their needs.
    </li>
</ul>
<h3 >What is Magic: The Gathering?</h3>
<ul class="d-flex text-start">
    <li>
        Magic: The Gathering (colloquially known as Magic or MTG) is a tabletop and digital collectible card game created by Richard Garfield. Released in 1993 by Wizards of the Coast (now a subsidiary of Hasbro), Magic was the first trading card game and had approximately thirty-five million players as of December 2018.
        A player in Magic takes the role of a Planeswalker, a powerful wizard who can travel ("walk") between dimensions ("planes") of the Multiverse, doing battle with other players as Planeswalkers by casting spells, using artifacts, and summoning creatures as depicted on individual cards drawn from their individual decks. A player defeats their opponent typically (but not always) by casting spells and attacking with creatures to deal damage to the opponent's "life total", with the objective being to reduce it from 20 to 0, or 40 to 0 in some group formats. Although the original concept of the game drew heavily from the motifs of traditional fantasy role-playing games such as Dungeons & Dragons, the gameplay bears little similarity to pen-and-paper games, while simultaneously having substantially more cards and more complex rules than many other card games.
        Magic can be played by two or more players, either in person with printed cards or on a computer, smartphone or tablet with virtual cards through the Internet-based software Magic: The Gathering Online or other video games such as Magic: The Gathering Arena and Magic Duels. It can be played in various rule formats, which fall into two categories: constructed and limited. Limited formats involve players building a deck spontaneously out of a pool of random cards with a minimum deck size of 40 cards; in constructed formats, players create decks from cards they own, usually with a minimum of 60 cards per deck.
    </li>
</ul>
<h3>How do I sort?</h3>
<ul class="d-flex" >
    <li>
        At the top of a users collection are a row of sorting options. Merely select one, and the sorting is done!
    </li>
</ul>
<h3 >How do I search?</h3>
<ul class="d-flex mb-5 pb-5">
    <li>
        At the top of the 'Search' page are search options! Searches are combined, so you could search for color, cmc, AND collector number, or just by name!
    </li>
</ul>
    <img/>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
ul{
    list-style-type:square ;
}
h3{
    display: flex;
    text-decoration: underline;
}
li{
    margin-left: 2vh;
    font-weight: 600;
    font-size: 14pt;
    
}

</style>