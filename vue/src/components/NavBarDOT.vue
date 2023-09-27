<template>
  <div id="nav" class="border-bottom border-dark border-3">
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080/">
          <img :src="image" alt="..." height="150" />
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto align-items-center">
            <li class="nav-item text-">
              <a
                class="nav-link active text-light fs-4"
                aria-current="page"
                href="http://localhost:8080/"
                >Home</a
              >
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle text-light fs-4"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Manage Collections
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a
                    class="dropdown-item text-dark"
                    href="http://localhost:8080/myCollections"
                    >View My Collections</a
                  >
                </li>
                <li>
                  <a class="dropdown-item text-dark" href="http://localhost:8080/collections/add"
                    >Start New Collection</a
                  >
                </li>
                <li>
                  <a class="dropdown-item text-dark" href="#"
                    >View Favorited Collections</a
                  >
                </li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a
                    class="dropdown-item text-dark"
                    href="http://localhost:8080/allCollections"
                    >View All Collections</a
                  >
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a
                class="nav-link text-light fs-4"
                href="http://localhost:8080/login"
                >Login</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link text-light fs-4"
                href="http://localhost:8080/logout"
                >Logout</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link text-light fs-4"
                href="http://localhost:8080/register"
                >Register</a
              >
            </li>
            <li class="d-none d-lg-inline">
              <img
                class="nav-img"
                :src="registerBubble"
                alt="register-now"
                height="200"
              />
            </li>
          </ul>
          <form class="d-flex" role="search">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button class="btn btn-outline-light" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
    <body></body>
  </div>
</template>

<script>
import image from "@/assets/DOTLogo.png";
import registerBubble from "@/assets/RegisterSuggestionBubble.png";

export default {
  data() {
    return {
      image,
      registerBubble,
    };
  },
};
</script>
<style scoped>
.navbar {
  background-color: #201F41;
}
.nav-img {
  padding-right: 100px;
  position: relative;
  top: -30px;
  left: -80px;
}

div ul li {
  padding-right: 40px;
}

div > ul > li > a:hover {
  background-color: #01788F;
  border-radius: 5px;
}
div > ul > li > ul > li > a:hover {
  background-color: #912E54;
}
.navbar-toggler {
  background-color: #01788F;
  border-color: #201F41;
}
.navbar-toggler-icon {
  color: #422053;
}
body {
  background: linear-gradient(-45deg, #422053, #01788F, #912E54, #201F41);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  text-align: center;
  background-repeat: repeat-y; 
}

 @keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
</style>