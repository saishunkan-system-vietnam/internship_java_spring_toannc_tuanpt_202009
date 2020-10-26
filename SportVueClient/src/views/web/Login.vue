<template>
  <v-card>
    <v-card-title>
      <span class="headline">Login</span>
    </v-card-title>

    <v-card-text>
      <v-container>
        <p>Defaut Account:</p>
        <p>1. admin - admin (Role_admin)</p>
        <p>2. manuelneuer522100 - 123456789(Role Member)</p>
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
            required
            type="password"
          ></v-text-field>
          <v-row>
            <v-col>
              <v-btn type="submit" color="blue darken-1" text>Confirm</v-btn>
            </v-col>
            <v-spacer></v-spacer>
          </v-row>
          <v-alert type="success" v-if="success"> Login Success! </v-alert>
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
      success: false,
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
  watch: {},
  methods: {
    login: function () {
      let self = this;
      // let userInfo = this.$store.state.user.userInfo;
      // this.$store.commit("auth/auth_overlay");
      this.$store
        .dispatch("auth/login", this.user)
        .then((res) => {
          // this.$store.commit("auth/auth_overlay");
          self.overlay = false;
          let userInfo = res.data.payload.account;
          console.log(userInfo.role);
          if (userInfo.role === null || userInfo.role === undefined) {
            self.checkProfile();
          } else if (userInfo.role === "ROLE_ADMIN") {
            self.success = !self.success;
            setTimeout(function () {
              self.success = !self.success;
              self.$store.commit("user/user_info", userInfo);
              self.$store.commit("user/admin_profile");
              self.commonLogin(userInfo);
            }, 1100);
          } else if (
            userInfo.role === "ROLE_USER" ||
            userInfo.role === "ROLE_MEMBER"
          ) {
            self.success = true;
            setTimeout(function () {
              self.success = false;
              self.$store.commit("user/user_info", userInfo);
              self.$store.commit("user/user_profile");
              self.commonLogin(userInfo);
            }, 1100);
          }
        })
        .catch((err) => console.log(err));
    },
    commonLogin(userInfo) {
      this.closeLoginDialog();
      this.username = "";
      this.password = "";
    },
  },
};
</script>