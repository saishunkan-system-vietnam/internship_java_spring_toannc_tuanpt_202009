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
    checkProfile: {
      type: Function,
    },
  },
  data() {
    return {
      user: {
        username: "Create1",
        password: "123",
      },
    };
  },
  mounted() {
    console.log(this.$store.state.auth.checkAccount);
  },
  computed: {
    checkAccount: function () {
      return this.$store.state.auth.checkAccount;
    },
    isLoggedIn: function () {
      return this.$store.getters.isLoggedIn;
    },
  },
  methods: {
    login: function () {
      let self = this;
      this.$store
        .dispatch("auth/login", this.user)
        .then((res) => {
          // console.log(res.data);

          const status = localStorage.getItem("secure");

          var decrypted = CryptoJS.AES.decrypt(status, "secure");
          var role = decrypted.toString(CryptoJS.enc.Utf8);

          if (role === null || role === undefined) {
            this.$router.push("/");
          } else if (role === "ROLE_USER" || role === "ROLE_MEMBER") {
            self.checkProfile();
            self.username = "";
            self.password = "";
          } else {
            this.$router.push("/");
          }
        })
        .catch((err) => console.log(err));
    },
    // getMembers: function () {
    //   this.$store.dispatch("auth/getMembers");
    // },
  },
};
</script>
