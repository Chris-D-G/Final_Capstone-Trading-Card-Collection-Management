<template>
  <div id="register" class="text-center mt-5 pt-5">
    <form @submit.prevent="register">
      <h1 class="mb-5 pb-4">Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username" >Username</label>
        <input type="text" class="form-control w-25 text-center mx-auto" id="username" v-model="user.username" required autofocus />
        <div class="form-text">Your username will be final. Choose wisely.</div>
      </div>
      <div class="form-input-group pb-3">
        <label for="password">Password</label>
        <input type="password" class="form-control w-25 text-center mx-auto" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group pb-3">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control w-25 text-center mx-auto" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div class="mb-2 pt-5">
      <button class="btn btn-dark" type="submit">Create Account</button>
      </div>
      <router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
  font-family: 'Forzan', sans-serif;
  font-weight: bold;
  font-size: 14pt;
}

h1{
  font-family: 'Forzan', sans-serif;
  font-weight: 900;
}
a{
  color: black;
}
button{
  font-family: 'Forzan', sans-serif;
}

</style>
