<template>
  <div id="nav">
      <nav class="navbar navbar-expand-lg" >
  <div class="container-fluid">
    <a class="navbar-brand" href="http://localhost:8080/">
      <img :src="image" alt="..." height="150">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="http://localhost:8080/">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Manage Collections
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="http://localhost:8080/myCollections">View My Collections</a></li>
            <li><a class="dropdown-item" href="#">Start New Collection</a></li>
            <li><a class="dropdown-item" href="#">View Favorited Collections</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="http://localhost:8080/allCollections">View All Collections</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/logout">LogOut</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/register">Register</a>
        </li>
      </ul>
      <img class="nav-img" :src="registerBubble" alt="register-now" height="200">
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
    </div>
</template>

<script>
import image from "@/assets/Logo.jpg";
import registerBubble from '@/assets/RegisterSuggestionBubble.png';

export default {
  data() {
    return {
      image,
      registerBubble
    }
  }
}
</script>
<style scoped>
.navbar{
  background-color: #4C2C2E;
}
.navbar-nav.me-auto.mb-2.mb-lg-0 li a {
  color: white;
}
.nav-img {
  padding-right: 100px;
}
</style>
