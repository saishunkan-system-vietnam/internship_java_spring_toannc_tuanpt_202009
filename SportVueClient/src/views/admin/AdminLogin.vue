<template>
  <v-card>
    <v-container>
      <v-card-title>
        <span class="headline">Admin Login</span>
      </v-card-title>
      <v-card-text>
        <h6
          style="color: red"
          align="center"
          justify="center"
          v-if="checkAccount"
        >
          Wrong Username or Password
        </h6>
        <form @submit.prevent="login">
          <v-text-field
            v-model="user.username"
            label="Username"
            required
          ></v-text-field>

          <v-text-field
            v-model="user.password"
            label="Password"
            type="password"
            required
          ></v-text-field>

          <v-btn type="submit" color="blue darken-1" text>Login</v-btn>
        </form>
      </v-card-text>
    </v-container>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      user: {
        username: "admin",
        password: "admin",
      },
    };
  },
  computed: {
    checkAccount: function () {
      return this.$store.state.auth.checkAccount;
    },
  },
  methods: {
    login: function () {
      this.$store
        .dispatch("auth/login", this.user)
        .then((res) => {
          const status = localStorage.getItem("token");
          var role = res.data.payload.account.role;
          if (status === null || status === undefined) {
           self.$store.state.auth.checkAccount()
          } else if (role === "ROLE_ADMIN") {
            this.$router.push("/admin/home");
          } else {
            self.$store.state.auth.checkAccount()
          }
        })
        .catch((err) => console.log(err));
    },

    getMembers: function () {
      this.$store.dispatch("auth/getMembers");
    },
  },
};
</script>