<template>
  <div id="login" class="text-center mt-5 pt-5">
    <form @submit.prevent="login">
      <h1 class="mb-5 " >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" class="form-control w-25 text-center mx-auto" id="username" placeholder="type username here" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group mb-5">
        <label for="password">Password</label>
        <input type="password" class="form-control w-25 text-center mx-auto" id="password" placeholder="type password here" v-model="user.password" required />
      </div>
      <div class="mb-2" >
      <button class="btn btn-dark" type="submit">Sign in</button>
      </div>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
  font-family: 'Forzan', sans-serif;
  font-weight: 800;
  font-size: 14pt;
}
h1{
  font-family: 'Forzan', sans-serif;
  font-weight: 900;
}
a{
  color:rgb(88, 25, 25);
  font-weight: 500;
}
button{
  font-family: 'Forzan', sans-serif;
}
</style>