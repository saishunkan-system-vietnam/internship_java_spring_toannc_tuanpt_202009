<template>
  <v-card>
    <v-card-title>
      <span class="headline">Login</span>
    </v-card-title>

    <v-card-text>
      <v-container>
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
            label="Email"
            required
          ></v-text-field>

          <v-text-field
            v-model="user.password"
            label="Password"
            required
            type="password"
          ></v-text-field>

          <v-row>
            <v-col>
              <v-btn type="submit" color="blue darken-1" text>Confirm</v-btn>
            </v-col>

            <v-spacer></v-spacer>

            <v-col>
              <v-btn @click.prevent="getMembers" color="blue darken-1" text
                >Get Members</v-btn
              >
            </v-col>
          </v-row>
        </form>
      </v-container>
    </v-card-text>
    
  </v-card>
</template>
<script>
export default {
  props: {
    closeLoginDialog: {
      type: Function,
    },
    checkProfile: {
      type: Function,
    },
  },
  data() {
    return {
      user: {
        username: "admin",
        password: "admin",
      },
    
    };
  },
  mounted() {
    // console.log(this.$store.state.auth.checkAccount);
  },
  computed: {
    checkAccount: function () {
      return this.$store.state.auth.checkAccount;
    },
    isLoggedIn: function () {
      return this.$store.getters.isLoggedIn;
    },
  },
  watch: {
    // overlay(val) {
    //   val &&
    //     setTimeout(() => {
    //       this.overlay = false;
    //     }, 3000);
    // },
  },
  methods: {
    login: function () {
      let self = this;
      // let userInfo = this.$store.state.user.userInfo;\
      self.overlay = !self.overlay;
      this.$store
        .dispatch("auth/login", this.user)
        .then((res) => {
          self.overlay = false;
          let userInfo = res.data.payload.account;
          if (userInfo.role === null || userInfo.role === undefined) {
            console.log("Failed");
          } else if (
            userInfo.role === "ROLE_USER" ||
            userInfo.role === "ROLE_MEMBER" ||
            userInfo.role === "ROLE_ADMIN"
          ) {
            self.checkProfile();
            self.closeLoginDialog();
            self.username = "";
            self.password = "";
          } else {
            console.log("Failed");
          }
        })
        .catch((err) => console.log(err));
    },
    getMembers: function () {
      this.overlay = !this.overlay;
    },
  },
};
</script>
