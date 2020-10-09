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
  watch: {},
  methods: {
    login: function () {
      let self = this;
      // let userInfo = this.$store.state.user.userInfo;
      this.$store.commit("auth/auth_overlay");
      this.$store
        .dispatch("auth/login", this.user)
        .then((res) => {
          this.$store.commit("auth/auth_overlay");
          self.overlay = false;
          let userInfo = res.data.payload;
          if (userInfo.role === null || userInfo.role === undefined) {
             this.$router.push('/');
          } else if (
            userInfo.role === "ROLE_USER" ||
            userInfo.role === "ROLE_MEMBER" ||
            userInfo.role === "ROLE_ADMIN"
          ) {
            self.$store.commit("user/user_info", res.data.payload);
            self.checkProfile();
            self.closeLoginDialog();
            self.username = "";
            self.password = "";
          }
        })
        .catch((err) => console.log(err));
    },
    getMembers: function () {},
  },
};
</script>
