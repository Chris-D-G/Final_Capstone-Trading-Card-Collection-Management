<template>
  <div id="nav">
    <nav class="navbar navbar-expand-xl border-bottom border-2 border-black">
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
          <ul class="navbar-nav me-auto align-items-center flex-grow-1">
            <li class="nav-item text-">
              <router-link :to="{name: 'home'}">
                <a
                class="nav-link active text-light fs-4"
                aria-current="page"
                >Home</a
              >
              </router-link>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle text-light fs-4 z-3"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Manage Collections
              </a>
              <ul class="dropdown-menu">
                <li>
                  <router-link :to="{name: 'myCollections'}">
                  <a
                    class="dropdown-item text-dark"
                    >View My Collections</a
                  >
                  </router-link>
                </li>
                <li>
                  <router-link :to="{name: 'collectionForm'}">
                  <a
                    class="dropdown-item text-dark"
                    >Start New Collection</a
                  >
                  </router-link>
                </li>
                <li>
                  <a class="dropdown-item text-dark" href="#"
                    >View Favorited Collections</a
                  >
                </li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <router-link :to="{name: 'allCollections'}">
                  <a
                    class="dropdown-item text-dark"
                    >View All Collections</a
                  >
                  </router-link>
                </li>
              </ul>
            </li>
            <li class="nav-item" >
              <router-link :to="{name: 'login'}">
              <a
                class="nav-link text-light fs-4"
                >Login</a
              >
              </router-link>
            </li>
            <li class="nav-item" >
              <router-link :to="{name: 'logout'}">
              <a
                class="nav-link text-light fs-4"
                >Logout</a
              >
              </router-link>
            </li>
            <li class="nav-item" >
              <router-link :to="{name: 'register'}">
              <a
                class="nav-link text-light fs-4"
                >Register</a
              >
              </router-link>
            </li>
            <li class="d-none d-xl-inline">
              <img
                class="nav-img"
                :src="registerBubble"
                alt="register-now"
                height="200"
              />
            </li>
          </ul>
          <form class="flex-grow-3 d-flex" role="search">
            <input
              class="form-control me-2 fs-10"
              type="search"
              placeholder="Search by Card Name or ID(scryfall)"
              aria-label="Search"
            />
            <button class="btn btn-outline-light" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import image from "@/assets/Logo.jpg";
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
<style >
.navbar {
  background-color: #4c2c2e;
}
.nav-img {
  padding-right: 100px;
  position: relative;
  top: -30px;
  left: -80px;
}

div > ul > li {
  padding-right: 40px;
}

div > ul > li > a:hover {
  background-color: #e8b287;
  border-radius: 5px;
}
div > ul > li > ul > li > a:hover {
  background-color: #ae9890;
}
.navbar-toggler {
  background-color: #e8b287;
  border-color: #4c2c2e;
}
.navbar-toggler-icon {
  color: #4c2c2e;
}
#nav {
  height: 160px;
}

a{
  text-decoration: none;
}

</style>
